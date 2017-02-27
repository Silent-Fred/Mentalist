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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "celebrity_id", "question_id" }))
@Entity
public class KnowledgeBase {

	// TODO This would actually not be necessary, but I somehow don't like the
	// idea of having to use a special embedded key class for a composite key.
	// So there's the unique constraint to express the basic idea.
	private @Id @GeneratedValue Long id;

	@ManyToOne
	@JoinColumn(name = "celebrity_id")
	private Celebrity celebrity;
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;

	@Column
	private long yes;
	@Column
	private long no;
	@Column
	private long dunno;
	@Column
	private long probablyYes;
	@Column
	private long probablyNo;

	public KnowledgeBase() {
	}

	public KnowledgeBase(Celebrity celebrity, Question question) {
		this.celebrity = celebrity;
		this.question = question;
	}

	public Celebrity getCelebrity() {
		return celebrity;
	}

	public void setCelebrity(Celebrity celebrity) {
		this.celebrity = celebrity;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public long getYes() {
		return yes;
	}

	public void setYes(long yes) {
		this.yes = yes;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public long getDunno() {
		return dunno;
	}

	public void setDunno(long dunno) {
		this.dunno = dunno;
	}

	public long getProbablyYes() {
		return probablyYes;
	}

	public void setProbablyYes(long probablyYes) {
		this.probablyYes = probablyYes;
	}

	public long getProbablyNo() {
		return probablyNo;
	}

	public void setProbablyNo(long probablyNo) {
		this.probablyNo = probablyNo;
	}
}
