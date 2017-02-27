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

package de.kuehweg.education.mentalist.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Game {

	private @Id @GeneratedValue Long id;

	@OneToMany(cascade = CascadeType.ALL)
	private Collection<Rating> ratings;
	@OneToMany(cascade = CascadeType.ALL)
	private Collection<Answer> answers;

	private LocalDateTime lastActivity;

	public Game() {
		ratings = new ArrayList<>();
		answers = new ArrayList<>();
		lastActivity = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public Collection<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(Collection<Rating> ratings) {
		this.ratings = ratings;
		lastActivity = LocalDateTime.now();
	}

	public Collection<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Collection<Answer> answers) {
		this.answers = answers;
		lastActivity = LocalDateTime.now();
	}

	public LocalDateTime getLastActivity() {
		return lastActivity;
	}

}
