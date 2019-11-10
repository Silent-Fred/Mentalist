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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.kuehweg.education.mentalist.domain.Answer;
import de.kuehweg.education.mentalist.domain.Celebrity;
import de.kuehweg.education.mentalist.domain.DefinedAnswers;
import de.kuehweg.education.mentalist.domain.Game;
import de.kuehweg.education.mentalist.domain.KnowledgeBase;
import de.kuehweg.education.mentalist.domain.KnowledgeBaseRepository;

@Service
public class KnowledgeBaseEvaluator {

	@Autowired
	private KnowledgeBaseRepository knowledgeBaseRepository;

	public KnowledgeBaseEvaluator() {
	}

	public long numberOfMostCommonAnswer(final KnowledgeBase knowledgeBase) {
		long max = knowledgeBase.getYes();
		if (knowledgeBase.getNo() > max) {
			max = knowledgeBase.getNo();
		}
		if (knowledgeBase.getDunno() > max) {
			max = knowledgeBase.getDunno();
		}
		if (knowledgeBase.getProbablyYes() > max) {
			max = knowledgeBase.getProbablyYes();
		}
		if (knowledgeBase.getProbablyNo() > max) {
			max = knowledgeBase.getProbablyNo();
		}
		return max;
	}

	public long overallNumberOfGivenAnswers(final KnowledgeBase knowledgeBase) {
		return knowledgeBase.getYes() + knowledgeBase.getNo() + knowledgeBase.getDunno()
				+ knowledgeBase.getProbablyYes() + knowledgeBase.getProbablyNo();
	}

	public long numberOfTimesTheAnswerWas(final KnowledgeBase knowledgeBase,
			final DefinedAnswers answer) {
		switch (answer) {
		case YES:
			return knowledgeBase.getYes();
		case NO:
			return knowledgeBase.getNo();
		case DUNNO:
			return knowledgeBase.getDunno();
		case PROBABLY_YES:
			return knowledgeBase.getProbablyYes();
		case PROBABLY_NO:
			return knowledgeBase.getProbablyNo();
		default:
			return 0L;
		}
	}

	public DefinedAnswers mostCommonAnswer(final KnowledgeBase knowledgeBase) {
		long max = knowledgeBase.getDunno();
		DefinedAnswers answer = DefinedAnswers.DUNNO;
		if (knowledgeBase.getYes() > max) {
			max = knowledgeBase.getYes();
			answer = DefinedAnswers.YES;
		}
		if (knowledgeBase.getNo() > max) {
			max = knowledgeBase.getNo();
			answer = DefinedAnswers.NO;
		}
		if (knowledgeBase.getProbablyYes() > max) {
			max = knowledgeBase.getProbablyYes();
			answer = DefinedAnswers.PROBABLY_YES;
		}
		if (knowledgeBase.getProbablyNo() > max) {
			max = knowledgeBase.getProbablyNo();
			answer = DefinedAnswers.PROBABLY_NO;
		}
		return answer;
	}

	public void extendKnowledgeFromAnswer(final KnowledgeBase knowledgeBase,
			final DefinedAnswers answer) {
		switch (answer) {
		case YES:
			knowledgeBase.setYes(knowledgeBase.getYes() + 1L);
			break;
		case NO:
			knowledgeBase.setNo(knowledgeBase.getNo() + 1L);
			break;
		case DUNNO:
			knowledgeBase.setDunno(knowledgeBase.getDunno() + 1L);
			break;
		case PROBABLY_YES:
			knowledgeBase.setProbablyYes(knowledgeBase.getProbablyYes() + 1L);
			break;
		case PROBABLY_NO:
			knowledgeBase.setProbablyNo(knowledgeBase.getProbablyNo() + 1L);
			break;
		default:
		}
	}

	public void updateKnowledgeBase(final Game game, final Celebrity confirmedCelebrity) {
		final List<KnowledgeBase> persistentKnowledgeBaseForCelebrity = knowledgeBaseRepository
				.findByCelebrity(confirmedCelebrity);
		final List<KnowledgeBase> knowledgeBaseForUpdate = new ArrayList<>(
				game.getAnswers().size());
		for (final Answer answer : game.getAnswers()) {
			knowledgeBaseForUpdate.add(updatedKnowledgeBase(persistentKnowledgeBaseForCelebrity,
					confirmedCelebrity, answer));
		}
		knowledgeBaseRepository.saveAll(knowledgeBaseForUpdate);
	}

	private KnowledgeBase updatedKnowledgeBase(
			final Collection<KnowledgeBase> existingKnowledgeBase, final Celebrity celebrity,
			final Answer answer) {
		KnowledgeBase knowledgeBaseToUpdate = null;
		for (final KnowledgeBase entry : existingKnowledgeBase) {
			if (entry.getQuestion().getId().equals(answer.getQuestion().getId())) {
				knowledgeBaseToUpdate = entry;
				break;
			}
		}
		if (knowledgeBaseToUpdate == null) {
			knowledgeBaseToUpdate = new KnowledgeBase(celebrity, answer.getQuestion());
		}
		extendKnowledgeFromAnswer(knowledgeBaseToUpdate, answer.getAnswer());
		return knowledgeBaseToUpdate;
	}

}
