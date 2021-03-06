/*
 * Copyright (c) 2017, Michael Kühweg
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package de.kuehweg.education.mentalist.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.kuehweg.education.mentalist.domain.Answer;
import de.kuehweg.education.mentalist.domain.Celebrity;
import de.kuehweg.education.mentalist.domain.CelebrityRepository;
import de.kuehweg.education.mentalist.domain.DefinedAnswers;
import de.kuehweg.education.mentalist.domain.Game;
import de.kuehweg.education.mentalist.domain.GameRepository;
import de.kuehweg.education.mentalist.domain.Question;
import de.kuehweg.education.mentalist.domain.QuestionRepository;
import de.kuehweg.education.mentalist.domain.Rating;
import de.kuehweg.education.mentalist.service.KnowledgeBaseEvaluator;
import de.kuehweg.education.mentalist.service.Questionnaire;
import de.kuehweg.education.mentalist.service.RatingAgency;

@RestController
public class GameApiController {

	private static long TIMEOUT_MINUTES = 2L;

	private static final String REL_CONFIRM = "confirm";

	private static final String REL_RECTIFY = "rectify";

	private static int NUMBER_OF_QUESTIONS_PER_GAME = 20;

	@Autowired
	private CelebrityRepository celebrityRepository;
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private KnowledgeBaseEvaluator knowledgeBaseEvaluator;

	@Autowired
	private RatingAgency ratingAgency;

	// TODO you can choose @Qualifier to be one of {"roundRobin", "random",
	// "balanced"} to change the questionnaire strategy
	@Autowired
	@Qualifier("balanced")
	private Questionnaire questionnaire;

	@RequestMapping(value = "/celebrity/{id}", method = RequestMethod.GET)
	public Celebrity celebrity(@PathVariable("id") final Long id) {
		return celebrityRepository.findById(id).orElse(null);
	}

	@RequestMapping(value = "/game/start", method = RequestMethod.GET)
	public NextMove gameStart() {
		final Game game = new Game();
		ratingAgency.prepareRatingForGame(game);
		gameRepository.save(game);
		final NextMove nextMove = createBasicNextMove(game);
		nextMove.setCurrentRatings(currentRatings(game));

		return nextMove;
	}

	@RequestMapping(value = "/game/{game}/question/{question}/answer/{answer}", method = RequestMethod.GET)
	public NextMove nextMove(@PathVariable("game") final Long gameId,
			@PathVariable("question") final Long questionId,
			@PathVariable("answer") final DefinedAnswers answer) {
		final Game game = gameRepository.findById(gameId).orElse(null);
		final Question question = questionRepository.findById(questionId).orElse(null);
		final Answer givenAnswer = new Answer(question, answer);
		game.getAnswers().add(givenAnswer);
		ratingAgency.updateRatingsAccordingToGivenAnswer(game, givenAnswer);
		gameRepository.save(game);

		final NextMove nextMove = endOfGameIsReached(game) ? createFinalMove(game)
				: createBasicNextMove(game);
		nextMove.setCurrentRatings(currentRatings(game));

		return nextMove;
	}

	private NextMove createBasicNextMove(final Game game) {
		final Question question = questionnaire.nextQuestion(game);
		final NextMove nextMove = new NextMove();
		nextMove.setMyQuestion(question);
		nextMove.setQuestionNumber(game.getAnswers().size() + 1);
		addAnswers(nextMove, game.getId(), question.getId());
		return nextMove;
	}

	private NextMove createFinalMove(final Game game) {
		final NextMove nextMove = new NextMove();
		final EducatedGuess myGuess = new EducatedGuess();
		myGuess.setMyGuess(new MakeAGuess(game).bestGuessSoFar());
		myGuess.add(
				ControllerLinkBuilder
						.linkTo(ControllerLinkBuilder.methodOn(GameApiController.class)
								.confirm(game.getId(), myGuess.getMyGuess().getId()))
						.withRel(REL_CONFIRM));
		myGuess.add(
				ControllerLinkBuilder
						.linkTo(ControllerLinkBuilder.methodOn(GameApiController.class)
								.correctWronglyGuessedCelebrity(game.getId(), null))
						.withRel(REL_RECTIFY));
		nextMove.setMyGuess(myGuess);
		return nextMove;
	}

	private List<CurrentRating> currentRatings(final Game game) {
		final Map<Long, CurrentRating> currentRatingsByRating = new HashMap<>();
		final long bestRating = ratingAgency.bestRatingPossibleUpToNow(game);
		for (final Rating rating : game.getRatings()) {
			CurrentRating currentRating = currentRatingsByRating.get(rating.getRating());
			if (currentRating == null) {
				currentRating = new CurrentRating();
				currentRating.add(ControllerLinkBuilder
						.linkTo(ControllerLinkBuilder.methodOn(GameApiController.class)
								.celebritiesWithRating(game.getId(), rating.getRating()))
						.withRel("celebs"));
				currentRating.setRatingPoints(rating.getRating());
				currentRating.setPercentageOfMaximallyPossibleRating(bestRating == 0L ? 0
						: (int) (currentRating.getRatingPoints() * 100 / bestRating));
				currentRatingsByRating.put(currentRating.getRatingPoints(), currentRating);
			}
			currentRating.setNumberOfCelebritiesSharingThisRating(
					currentRating.getNumberOfCelebritiesSharingThisRating() + 1);
		}
		final List<CurrentRating> currentRatings = currentRatingsByRating.values().stream()
				.sorted((currentRating1, currentRating2) -> Long.compare(
						currentRating2.getRatingPoints(), currentRating1.getRatingPoints()))
				.collect(Collectors.toList());
		return currentRatings.subList(0, Math.min(currentRatings.size(), 10));
	}

	@RequestMapping(value = "/game/{game}/celebrities/with/rating/{rating}", method = RequestMethod.GET)
	public List<Celebrity> celebritiesWithRating(@PathVariable("game") final Long gameId,
			@PathVariable("rating") final Long queriedRating) {
		final List<Celebrity> celebrities = new ArrayList<>();
		final Game game = gameRepository.findById(gameId).orElse(null);
		for (final Rating rating : game.getRatings()) {
			if (rating.getRating() == queriedRating) {
				celebrities.add(rating.getCelebrity());
			}
		}
		celebrities.sort(
				(celebrity1, celebrity2) -> celebrity1.getName().compareTo(celebrity2.getName()));
		return celebrities;
	}

	@RequestMapping(value = "/game/{game}/confirm/celebrity/{celebrity}", method = RequestMethod.GET)
	public ResponseEntity<?> confirm(@PathVariable("game") final Long gameId,
			@PathVariable("celebrity") final Long celebrityId) {
		final Game game = gameRepository.findById(gameId).orElse(null);
		final Celebrity celebrity = celebrityRepository.findById(celebrityId).orElse(null);
		knowledgeBaseEvaluator.updateKnowledgeBase(game, celebrity);
		gameRepository.deleteById(gameId);
		gameRepository
				.deleteByLastActivityLessThan(LocalDateTime.now().minusMinutes(TIMEOUT_MINUTES));
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/game/{game}/correct}", method = RequestMethod.POST)
	public ResponseEntity<?> correctWronglyGuessedCelebrity(@PathVariable("game") final Long gameId,
			@RequestBody final Celebrity celebrity) {
		Celebrity persistentCelebrityWithSameName = celebrityRepository
				.findByName(celebrity.getName());
		if (persistentCelebrityWithSameName == null) {
			persistentCelebrityWithSameName = createCelebrity(celebrity);
		}
		confirm(gameId, persistentCelebrityWithSameName.getId());
		return ResponseEntity.noContent().build();
	}

	private void addAnswers(final NextMove nextMove, final Long gameId, final Long questionId) {
		for (final DefinedAnswers answer : DefinedAnswers.values()) {
			nextMove.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder
					.methodOn(GameApiController.class).nextMove(gameId, questionId, answer))
					.withRel(answer.name()));
		}
	}

	private Celebrity createCelebrity(final Celebrity celebrity) {
		return celebrityRepository.save(celebrity);
	}

	private boolean endOfGameIsReached(final Game game) {
		return game.getAnswers().size() >= NUMBER_OF_QUESTIONS_PER_GAME;
	}
}
