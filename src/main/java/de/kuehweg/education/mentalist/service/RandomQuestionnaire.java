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
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.kuehweg.education.mentalist.domain.Answer;
import de.kuehweg.education.mentalist.domain.Game;
import de.kuehweg.education.mentalist.domain.Question;
import de.kuehweg.education.mentalist.domain.QuestionRepository;

@Component("random")
public class RandomQuestionnaire implements Questionnaire {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question nextQuestion(Game game) {
		Collection<Answer> preceedingConversation = game.getAnswers();
		List<Question> questions = (List<Question>) questionRepository.findAll();
		Collections.shuffle(questions, new Random(game.getId() == null ? 0L : game.getId()));
		long numberOfPreceedingQuestions = preceedingConversation == null ? 0 : preceedingConversation.size();
		int index = (int) (numberOfPreceedingQuestions % questions.size());
		return questions.get(index);
	}

}
