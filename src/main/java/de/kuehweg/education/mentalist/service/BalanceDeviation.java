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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.kuehweg.education.mentalist.domain.KnowledgeBase;
import de.kuehweg.education.mentalist.domain.Question;

@Service
public class BalanceDeviation {

	private static final int OPTIMAL_BALANCE = 50;

	@Autowired
	private KnowledgeBaseEvaluator knowledgeBaseEvaluator;

	public BalanceDeviation() {
	}

	public Question questionWithLeastDeviationFromOptimalBalance(Collection<KnowledgeBase> knowledgeBase,
			Collection<Question> questions) {
		Question whatToAskNext = null;
		long leastDeviationFromOptimalBalance = 0L;
		for (Question question : questions) {
			long deviation = calculateDeviationFromOptimalBalance(knowledgeBase, question);
			if (whatToAskNext == null || deviation <= leastDeviationFromOptimalBalance) {
				leastDeviationFromOptimalBalance = deviation;
				whatToAskNext = question;
			}
		}
		return whatToAskNext;
	}

	private long calculateDeviationFromOptimalBalance(Collection<KnowledgeBase> relevantKnowledgeBase,
			Question question) {
		KnowledgeBase mostCommonAnswerDistribution = distributionOfMostCommonAnswers(relevantKnowledgeBase, question);
		if (knowledgeBaseEvaluator.overallNumberOfGivenAnswers(mostCommonAnswerDistribution) == 0) {
			// TODO Is this a good approach? It makes questions that have never
			// been asked before a high priority. Could be a good approach to
			// broaden the knowledge, but it doesn't quite "balance", as the
			// name suggests.
			return OPTIMAL_BALANCE;
		}
		return Math.abs(OPTIMAL_BALANCE - (knowledgeBaseEvaluator.numberOfMostCommonAnswer(mostCommonAnswerDistribution)
				* 100 / knowledgeBaseEvaluator.overallNumberOfGivenAnswers(mostCommonAnswerDistribution)));
	}

	private KnowledgeBase distributionOfMostCommonAnswers(Collection<KnowledgeBase> availableKnowledgeBase,
			Question question) {
		KnowledgeBase balanceBase = new KnowledgeBase(null, question);
		for (KnowledgeBase knowledgeBase : availableKnowledgeBase) {
			if (!knowledgeBase.getQuestion().equals(question)) {
				continue;
			}
			switch (knowledgeBaseEvaluator.mostCommonAnswer(knowledgeBase)) {
			case YES:
				balanceBase.setYes(balanceBase.getYes() + 1);
				break;
			case NO:
				balanceBase.setNo(balanceBase.getNo() + 1);
				break;
			case DUNNO:
				balanceBase.setDunno(balanceBase.getDunno() + 1);
				break;
			case PROBABLY_YES:
				balanceBase.setProbablyYes(balanceBase.getProbablyYes() + 1);
				break;
			case PROBABLY_NO:
				balanceBase.setProbablyNo(balanceBase.getProbablyNo() + 1);
				break;
			default:
				throw new IllegalStateException();
			}
		}
		return balanceBase;
	}
}
