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
import java.util.stream.Collectors;

import de.kuehweg.education.mentalist.domain.Celebrity;
import de.kuehweg.education.mentalist.domain.Game;
import de.kuehweg.education.mentalist.domain.Rating;

public class MakeAGuess {

	private Game game;

	public MakeAGuess(Game game) {
		this.game = game;
	}

	public Celebrity bestGuessSoFar() {
		List<Rating> ratingsInDescendingOrder = game.getRatings().stream().sorted((rating1, rating2) -> {
			int comparison = Long.compare(rating2.getRating(), rating1.getRating());
			return comparison != 0 ? comparison
					: Long.compare(rating1.getCelebrity().getId(), rating2.getCelebrity().getId());
		}).collect(Collectors.toList());
		// TODO we might add further criteria here in case of a tie. E.g. the
		// number of games played for a celebrity
		return ratingsInDescendingOrder.get(0).getCelebrity();
	}
}
