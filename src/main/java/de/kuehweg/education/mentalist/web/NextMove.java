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

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import de.kuehweg.education.mentalist.domain.Question;

public class NextMove extends ResourceSupport {

	private List<CurrentRating> currentRatings;

	private EducatedGuess myGuess;

	private Question myQuestion;

	private Integer questionNumber;

	// service end points for answers will be added dynamically (cf.
	// ResourceSupport)

	public List<CurrentRating> getCurrentRatings() {
		return currentRatings;
	}

	public void setCurrentRatings(List<CurrentRating> currentRatings) {
		this.currentRatings = currentRatings;
	}

	public EducatedGuess getMyGuess() {
		return myGuess;
	}

	public void setMyGuess(EducatedGuess myGuess) {
		this.myGuess = myGuess;
	}

	public Question getMyQuestion() {
		return myQuestion;
	}

	public void setMyQuestion(Question myQuestion) {
		this.myQuestion = myQuestion;
	}

	public Integer getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}
}
