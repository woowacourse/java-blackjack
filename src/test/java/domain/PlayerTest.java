package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import constant.Suit;

public class PlayerTest {

	@Nested
	@DisplayName("플레아어가 카드를 뽑을지 여부를 반환한다.")
	class isPickCard {

		@DisplayName("플레이어가 카드를 뽑을지 여부를 올바르게 반환한다.")
		@ParameterizedTest
		@MethodSource("providePlayerHand")
		public void isPickCard(final List<Card> cards, final boolean expected) {
			// given
			final CardHand cardHand = new CardHand(cards);
			final Participant participant = new Participant(cardHand);
			final Player p = new Player("", participant);

			// when
			final boolean actual = p.isPickCard();

			// then
			assertThat(actual).isEqualTo(expected);
		}

		private static Stream<Arguments> providePlayerHand() {
			return Stream.of(
				Arguments.of(
					List.of(new Card(Rank.TEN, Suit.CLUB), new Card(Rank.TEN, Suit.DIAMOND),
						new Card(Rank.ACE, Suit.CLUB)), true),
				Arguments.of(
					List.of(new Card(Rank.TEN, Suit.DIAMOND),
						new Card(Rank.TEN, Suit.CLUB), new Card(Rank.TWO, Suit.CLUB)), false)
			);
		}

	}

	@Nested
	@DisplayName("결투를 진행한다.")
	class Duel {

		@DisplayName("올바르게 승자를 가려낸다.")
		@Test
		public void duel() {
			// given
			final Player winner = new Player("Winner");
			final List<Card> winnerCards = List.of(new Card(Rank.ACE, Suit.CLUB));
			final Deck winnerDeck = new Deck(new ArrayDeque<>(winnerCards));
			winner.pickCard(winnerDeck);

			final Player loser = new Player("Loser");
			final List<Card> loserCards = List.of(new Card(Rank.NINE, Suit.CLUB));
			final Deck loserDeck = new Deck(new ArrayDeque<>(loserCards));
			loser.pickCard(loserDeck);

			final Dealer dealer = new Dealer();
			final List<Card> dealerCards = List.of(new Card(Rank.TEN, Suit.CLUB));
			final Deck dealerDeck = new Deck(new ArrayDeque<>(dealerCards));
			dealer.pickCard(dealerDeck);

			// when
			winner.duel(dealer.getParticipant());
			loser.duel(dealer.getParticipant());

			// then
			assertThat(winner.getParticipant().getDuelHistory().getWinCount()).isEqualTo(1);
			assertThat(loser.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(1);
		}

		@DisplayName("21점이 넘는다면, 상대방이 승리한다.")
		@Test
		public void duelOverThan() {
			// given
			final Player loser = new Player("Loser");
			final List<Card> loserCards = List.of(
				new Card(Rank.TEN, Suit.SPADE), new Card(Rank.TEN, Suit.CLUB),
				new Card(Rank.TWO, Suit.SPADE));
			final Deck loserDeck = new Deck(new ArrayDeque<>(loserCards));
			loser.pickCard(loserDeck);
			loser.pickCard(loserDeck);
			loser.pickCard(loserDeck);

			final Dealer dealer = new Dealer();
			final List<Card> dealerCards = List.of(new Card(Rank.TEN, Suit.CLUB));
			final Deck dealerDeck = new Deck(new ArrayDeque<>(dealerCards));
			dealer.pickCard(dealerDeck);

			// when
			loser.duel(dealer.getParticipant());

			// then
			assertThat(loser.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(1);
		}

		@DisplayName("상대방이 21점이 넘고 내가 21점 이하라면, 내가 승리한다.")
		@Test
		public void duelOverThanX() {
			// given
			final Player loser = new Player("Loser");
			final List<Card> loserCards = List.of(
				new Card(Rank.TEN, Suit.SPADE));
			final Deck loserDeck = new Deck(new ArrayDeque<>(loserCards));
			loser.pickCard(loserDeck);

			final Dealer dealer = new Dealer();
			final List<Card> dealerCards = List.of(
				new Card(Rank.TEN, Suit.SPADE), new Card(Rank.TEN, Suit.CLUB),
				new Card(Rank.TWO, Suit.SPADE));
			final Deck dealerDeck = new Deck(new ArrayDeque<>(dealerCards));
			dealer.pickCard(dealerDeck);
			dealer.pickCard(dealerDeck);
			dealer.pickCard(dealerDeck);

			// when
			loser.duel(dealer.getParticipant());

			// then
			assertThat(loser.getParticipant().getDuelHistory().getWinCount()).isEqualTo(1);
		}
	}
}
