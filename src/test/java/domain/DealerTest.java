package domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

import java.util.ArrayDeque;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import domain.constant.Suit;

public class DealerTest {

	private static final int DEALER_PICK_CARD_SCORE_MAX = 16;
	private static final int BUST_SCORE = 21;

	@Nested
	@DisplayName("딜러가 카드를 받아야 하는지 여부를 반환한다.")
	class IsPickCard {

		@DisplayName("16점 이하라면, 딜러가 카드를 뽑을 수 있는지에 대한 여부가 true이다.")
		@Test
		public void isPickCard() {
			// given
			final var dealer = new Dealer();

			// when
			final var actual = dealer.isPickCard(BUST_SCORE, DEALER_PICK_CARD_SCORE_MAX);

			// then
			assertThat(actual).isTrue();
		}

		@DisplayName("딜러의 현재 점수 합이 16점 초과라면, 딜러가 카드를 뽑을 수 있는지에 대한 여부가 false이다.")
		@Test
		public void isPickCard1() {
			// given
			final Card card1 = new Card(Rank.TEN, Suit.CLUB);
			final Card card2 = new Card(Rank.SEVEN, Suit.HEART);
			final CardHand cardHand = new CardHand(List.of(card1, card2));
			final var dealer = new Dealer(cardHand);

			// when
			final var actual = dealer.isPickCard(BUST_SCORE, DEALER_PICK_CARD_SCORE_MAX);

			// then
			assertThat(actual).isFalse();
		}
	}

	@Nested
	@DisplayName("딜러는 플레이어와 대결한다.")
	class StartDuel {

		@DisplayName("딜러가 1승 1패라면 1승, 1패를 기록한다.")
		@Test
		void startDuel() {
			// given
			final Card card1 = new Card(Rank.TEN, Suit.CLUB);
			final Card card2 = new Card(Rank.SEVEN, Suit.HEART);
			final CardHand cardHand = new CardHand(List.of(card1, card2));
			final var dealer = new Dealer(cardHand);

			final Player player = new Player("loser");
			final List<Card> playerCards = List.of(new Card(Rank.ACE, Suit.CLUB));
			final Deck playerDeck = new Deck(new ArrayDeque<>(playerCards));
			player.pickCard(playerDeck);

			final Player winner = new Player("w");
			final List<Card> winnerCards = List.of(new Card(Rank.ACE, Suit.CLUB),
				new Card(Rank.TEN, Suit.CLUB));
			final Deck winnerDeck = new Deck(new ArrayDeque<>(winnerCards));
			winner.pickCard(winnerDeck);
			winner.pickCard(winnerDeck);

			// when
			dealer.startDuel(player, BUST_SCORE);
			dealer.startDuel(winner, BUST_SCORE);

			// then
			assertSoftly(s -> {
				s.assertThat(dealer.getParticipant().getDuelHistory().getWinCount()).isEqualTo(1);
				s.assertThat(dealer.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(1);
			});
		}

	}
}
