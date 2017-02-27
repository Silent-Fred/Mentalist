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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.kuehweg.education.mentalist.domain.Answer;
import de.kuehweg.education.mentalist.domain.Celebrity;
import de.kuehweg.education.mentalist.domain.Game;
import de.kuehweg.education.mentalist.domain.KnowledgeBase;
import de.kuehweg.education.mentalist.domain.KnowledgeBaseRepository;
import de.kuehweg.education.mentalist.domain.Question;
import de.kuehweg.education.mentalist.domain.QuestionRepository;
import de.kuehweg.education.mentalist.domain.Rating;

@Component("balanced")
public class BalancedSplitQuestionnaire implements Questionnaire {

	@Autowired
	private KnowledgeBaseRepository knowledgeBaseRepository;

	@Autowired
	private BalanceDeviation balanceDeviation;

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question nextQuestion(Game game) {
		Collection<KnowledgeBase> relevantKnowledgeBase = knowledgeBaseFocusedOnSelectedCelebrities(
				(List<KnowledgeBase>) knowledgeBaseRepository.findAll(), celebritiesToBeFurtherPursued(game));
		return balanceDeviation.questionWithLeastDeviationFromOptimalBalance(relevantKnowledgeBase,
				unaskedQuestions(game));
	}

	private Collection<Question> unaskedQuestions(Game game) {
		Set<Long> alreadyAskedQuestions = new HashSet<>();
		for (Answer answer : game.getAnswers()) {
			alreadyAskedQuestions.add(answer.getQuestion().getId());
		}
		List<Question> questions = new ArrayList<>();
		for (Question question : questionRepository.findAll()) {
			if (!alreadyAskedQuestions.contains(question.getId())) {
				questions.add(question);
			}
		}
		questions.sort((question1, question2) -> Long.compare(question1.getId(), question2.getId()));
		return questions;
	}

	private Collection<KnowledgeBase> knowledgeBaseFocusedOnSelectedCelebrities(Collection<KnowledgeBase> knowledgeBase,
			Collection<Celebrity> celebrities) {
		return knowledgeBase.stream().filter(e -> celebrities.contains(e.getCelebrity())).collect(Collectors.toSet());
	}

	private Set<Celebrity> celebritiesToBeFurtherPursued(Game game) {
		List<Rating> ratingsInDescendingOrder = game.getRatings().stream()
				.sorted((rating1, rating2) -> Long.compare(rating2.getRating(), rating1.getRating()))
				.collect(Collectors.toList());
		Set<Celebrity> promising = new HashSet<>();
		// TODO maybe allow a certain amount of deviation?
		long requiredRating = ratingsInDescendingOrder.get(0).getRating();
		for (Rating rating : ratingsInDescendingOrder) {
			if (rating.getRating() < requiredRating) {
				break;
			}
			promising.add(rating.getCelebrity());
		}
		return promising;
	}
}
