package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import constant.Emblem;

public class DealerTest {

	@Nested
	@DisplayName("딜러가 카드를 받아야 하는지 여부를 반환한다.")
	class IsPickCard {

		@DisplayName("16점 이하라면, 딜러가 카드를 뽑는 여부를 올바르게 반환한다.")
		@Test
		public void isPickCard() throws Exception {
			// given
			final var dealer = new Dealer();

			// when
			final var actual = dealer.isPickCard();

			// then
			assertThat(actual).isTrue();
		}

		@DisplayName("16점 초과라면, 딜러가 카드를 뽑는 여부를 올바르게 반환한다.")
		@Test
		public void isPickCard1() throws Exception {
			// given
			final Card card1 = new Card(Rank.TEN, Emblem.CLUB);
			final Card card2 = new Card(Rank.SEVEN, Emblem.HEART);
			final CardHand cardHand = new CardHand(List.of(card1, card2));
			final var dealer = new Dealer(cardHand);

			// when
			final var actual = dealer.isPickCard();

			// then
			assertThat(actual).isFalse();
		}
	}

	@Nested
	@DisplayName("딜러는 플레이어와 대결한다.")
	class StartDuel {

		@DisplayName("딜러는 플레이와의 대결 결과를 올바르게, 기록한다.")
		@Test
		void startDuel() throws Exception {
			// given
			final Card card1 = new Card(Rank.TEN, Emblem.CLUB);
			final Card card2 = new Card(Rank.SEVEN, Emblem.HEART);
			final CardHand cardHand = new CardHand(List.of(card1, card2));
			final var dealer = new Dealer(cardHand);

			final Player player = new Player("loser");
			final List<Card> playerCards = List.of(new Card(Rank.ACE, Emblem.CLUB));
			final Deck playerDeck = new Deck(new ArrayDeque<>(playerCards));
			player.pickCard(playerDeck);

			final Player winner = new Player("w");
			final List<Card> winnerCards = List.of(new Card(Rank.ACE, Emblem.CLUB),
				new Card(Rank.TEN, Emblem.CLUB));
			final Deck winnerDeck = new Deck(new ArrayDeque<>(winnerCards));
			winner.pickCard(winnerDeck);
			winner.pickCard(winnerDeck);

			// when
			dealer.startDuel(player);
			dealer.startDuel(winner);

			// then
			assertThat(dealer.getWinCount()).isEqualTo(1);
			assertThat(dealer.getLoseCount()).isEqualTo(1);
		}

	}
}
