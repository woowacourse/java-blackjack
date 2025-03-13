package participant;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import card.Card;
import card.CardHand;
import card.Rank;
import card.Suit;
import paticipant.Dealer;
import paticipant.Participant;
import value.Score;

public class DealerTest {
	private static final Score DEALER_PICK_CARD_SCORE_MAX = Score.from(16);
	private static final Score BUST_SCORE = Score.from(21);

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
			final var dealer = new Dealer(new Participant(cardHand));

			// when
			final var actual = dealer.isPickCard(BUST_SCORE, DEALER_PICK_CARD_SCORE_MAX);

			// then
			assertThat(actual).isFalse();
		}
	}

	@Nested
	@DisplayName("딜러의 Bust 여부 체크")
	class IsBust {

		@DisplayName("딜러의 현재 점수가 bustScore 초과라면 true를 반환한다.")
		@Test
		void isBust() {
			// given
			final List<Card> playerCards = List.of(new Card(Rank.TEN, Suit.CLUB), new Card(Rank.TEN, Suit.SPADE),
				new Card(Rank.TEN, Suit.HEART));
			final Participant participant = new Participant(new CardHand(playerCards));
			final Dealer bustedDealer = new Dealer(participant);

			// when
			boolean actual = bustedDealer.isBust(BUST_SCORE);

			// then
			assertThat(actual).isTrue();
		}

		@DisplayName("딜러의 현재 점수가 bustScore 이하려면 false를 반환한다.")
		@Test
		void isBust1() {
			// given
			final List<Card> playerCards = List.of(new Card(Rank.ACE, Suit.CLUB), new Card(Rank.TEN, Suit.HEART));
			final Participant participant = new Participant(new CardHand(playerCards));
			final Dealer noBustedDealer = new Dealer(participant);

			// when
			boolean actual = noBustedDealer.isBust(BUST_SCORE);

			// then
			assertThat(actual).isFalse();
		}
	}

	@Nested
	@DisplayName("딜러의 점수를 계산한다.")
	class CalculateAllScore {

		@DisplayName("딜러의 현재 점수를 계산한다.")
		@Test
		void calculateAllScore() {
			// given
			final List<Card> playerCards = List.of(new Card(Rank.ACE, Suit.CLUB), new Card(Rank.TEN, Suit.HEART));
			final Participant participant = new Participant(new CardHand(playerCards));
			final Dealer dealer = new Dealer(participant);

			// when
			final int actual = dealer.calculateAllScore(BUST_SCORE).value();

			// then
			assertThat(actual).isEqualTo(21);
		}
	}
}
