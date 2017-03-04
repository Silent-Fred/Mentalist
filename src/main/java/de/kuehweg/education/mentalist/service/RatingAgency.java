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

package de.kuehweg.education.mentalist.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.kuehweg.education.mentalist.domain.Answer;
import de.kuehweg.education.mentalist.domain.Celebrity;
import de.kuehweg.education.mentalist.domain.CelebrityRepository;
import de.kuehweg.education.mentalist.domain.DefinedAnswers;
import de.kuehweg.education.mentalist.domain.Game;
import de.kuehweg.education.mentalist.domain.KnowledgeBase;
import de.kuehweg.education.mentalist.domain.KnowledgeBaseRepository;
import de.kuehweg.education.mentalist.domain.Question;
import de.kuehweg.education.mentalist.domain.Rating;

@Service
public class RatingAgency {

	private static final long INCREASE_RATING_FOR_MOST_COMMON_ANSWER = 100L;
	private static final long INCREASE_RATING_FOR_PROBABLE_GUESSES = 70L;
	private static final long INCREASE_RATING_FOR_MISSING_KNOWLEDGE = 50L;

	@Autowired
	private CelebrityRepository celebrityRepository;
	@Autowired
	private KnowledgeBaseEvaluator knowledgeBaseEvaluator;
	@Autowired
	private KnowledgeBaseRepository knowledgeBaseRepository;

	public void prepareRatingForGame(Game game) {
		game.getRatings().clear();
		for (Celebrity celebrity : celebrityRepository.findAll()) {
			game.getRatings().add(new Rating(celebrity));
		}
	}

	public void updateRatingsAccordingToGivenAnswer(Game game, Answer answer) {
		Map<Celebrity, KnowledgeBase> knowledgeBaseByCelebrity = knowledgeBaseByCelebrity(answer.getQuestion());
		for (Rating rating : game.getRatings()) {
			KnowledgeBase knowledgeBase = knowledgeBaseByCelebrity.get(rating.getCelebrity());
			if (knowledgeBase == null) {
				rateBasedOnFirstAnswerEver(answer, rating);
			} else {
				rateBasedOnPreviousKnowledge(answer, rating, knowledgeBase);
			}
		}
	}

	private void rateBasedOnPreviousKnowledge(Answer answer, Rating rating, KnowledgeBase knowledgeBase) {
		DefinedAnswers mostCommonAnswer = knowledgeBaseEvaluator.mostCommonAnswer(knowledgeBase);
		if (answer.getAnswer() == mostCommonAnswer) {
			rating.setRating(rating.getRating() + INCREASE_RATING_FOR_MOST_COMMON_ANSWER);
		} else if (atLeastCloseToTheMostCommonAnswer(answer.getAnswer(), mostCommonAnswer)) {
			rating.setRating(rating.getRating() + INCREASE_RATING_FOR_PROBABLE_GUESSES);
		} else {
			long overallNumberOfGivenAnswers = knowledgeBaseEvaluator.overallNumberOfGivenAnswers(knowledgeBase);
			if (overallNumberOfGivenAnswers == 0L) {
				rateBasedOnFirstAnswerEver(answer, rating);
			} else {
				Double percentage = (double) INCREASE_RATING_FOR_MOST_COMMON_ANSWER
						* (double) knowledgeBaseEvaluator.numberOfTimesTheAnswerWas(knowledgeBase, answer.getAnswer())
						/ (double) knowledgeBaseEvaluator.overallNumberOfGivenAnswers(knowledgeBase);
				rating.setRating(rating.getRating() + percentage.longValue());
			}
		}
	}

	private void rateBasedOnFirstAnswerEver(Answer answer, Rating rating) {
		rating.setRating(rating.getRating() + INCREASE_RATING_FOR_MISSING_KNOWLEDGE);
	}

	public long bestRatingPossibleUpToNow(Game game) {
		return game.getAnswers().size() * INCREASE_RATING_FOR_MOST_COMMON_ANSWER;
	}

	private Map<Celebrity, KnowledgeBase> knowledgeBaseByCelebrity(Question question) {
		Map<Celebrity, KnowledgeBase> knowledgeBaseByQuestion = new HashMap<>();
		for (KnowledgeBase knowledgeBase : knowledgeBaseRepository.findByQuestion(question)) {
			knowledgeBaseByQuestion.put(knowledgeBase.getCelebrity(), knowledgeBase);
		}
		return knowledgeBaseByQuestion;
	}

	private boolean atLeastCloseToTheMostCommonAnswer(DefinedAnswers answer, DefinedAnswers mostCommonAnswer) {
		boolean basicallyTheSameIdea = answer == DefinedAnswers.YES && mostCommonAnswer == DefinedAnswers.PROBABLY_YES;
		basicallyTheSameIdea = basicallyTheSameIdea
				|| answer == DefinedAnswers.PROBABLY_YES && mostCommonAnswer == DefinedAnswers.YES;
		basicallyTheSameIdea = basicallyTheSameIdea
				|| answer == DefinedAnswers.NO && mostCommonAnswer == DefinedAnswers.PROBABLY_NO;
		basicallyTheSameIdea = basicallyTheSameIdea
				|| answer == DefinedAnswers.PROBABLY_NO && mostCommonAnswer == DefinedAnswers.NO;
		return basicallyTheSameIdea;
	}
}