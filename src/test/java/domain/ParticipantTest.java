package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import constant.CardNumber;
import constant.Emblem;

class ParticipantTest {
	@Nested
	@DisplayName("참가자 카드 뽑기")
	class PickCard {

		@DisplayName("참가자는 덱으로부터, 올바르게 카드를 뽑는다.")
		@Test
		public void pickCard() {
			// given
			final Participant participant = new MockParticipant();
			final List<Card> cards = List.of(new Card(CardNumber.ACE, Emblem.CLUB));
			final Deck deck = new Deck(new ArrayDeque<>(cards));

			// when
			participant.pickCard(deck);

			// then
			assertThat(participant.getHand().hand()).isNotEmpty();
		}
	}

	@Nested
	@DisplayName("결투를 진행한다.")
	class Duel {

		@DisplayName("올바르게 승자를 가려낸다.")
		@Test
		public void duel() throws Exception {
			// given
			final Participant winner = new MockParticipant();
			final List<Card> winnerCards = List.of(new Card(CardNumber.ACE, Emblem.CLUB));
			final Deck winnerDeck = new Deck(new ArrayDeque<>(winnerCards));
			winner.pickCard(winnerDeck);
			winner.pickCard(winnerDeck);

			final Participant loser = new MockParticipant();
			final List<Card> loserCards = List.of(new Card(CardNumber.TEN, Emblem.CLUB));
			final Deck loserDeck = new Deck(new ArrayDeque<>(loserCards));
			loser.pickCard(loserDeck);
			loser.pickCard(loserDeck);

			// when
			winner.duel(loser);

			// then
			assertThat(winner.getDuelHistory().getWinCount()).isEqualTo(1);
			assertThat(winner.getDuelHistory().getLoseCount()).isEqualTo(0);
			assertThat(loser.getDuelHistory().getLoseCount()).isEqualTo(1);
			assertThat(loser.getDuelHistory().getWinCount()).isEqualTo(0);
		}
	}

	private static class MockParticipant extends Participant {

		@Override
		public boolean isPickCard() {
			return false;
		}
	}
}
