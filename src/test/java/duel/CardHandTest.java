package duel;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import card.Card;
import card.CardHand;
import card.Rank;
import card.Suit;
import game.GameScore;
import value.Count;

class CardHandTest {

	@Nested
	@DisplayName("모든 Card의 점수 합을 계산한다.")
	class CalculateAllGameScore {

		@ParameterizedTest
		@MethodSource("getCardList")
		@DisplayName("주어진 모든 Card 점수 합을 계산하여 반환한다.")
		void test_calculateAllScore(List<Card> cards, int expected) {
			//given
			final var cardHand = new CardHand(cards);
			final GameScore bustGameScore = GameScore.from(21);

			//when&then
			assertThat(cardHand.calculateAllScore(bustGameScore)).isEqualTo(GameScore.from(expected));
		}

		private static Stream<Arguments> getCardList() {
			return Stream.of(
				Arguments.of(List.of(new Card(Rank.NINE, Suit.CLUB), new Card(Rank.NINE, Suit.HEART)),
					18),
				Arguments.of(List.of(new Card(Rank.ACE, Suit.CLUB), new Card(Rank.ACE, Suit.HEART)),
					12),
				Arguments.of(List.of(new Card(Rank.ACE, Suit.CLUB), new Card(Rank.TEN, Suit.HEART)),
					21),
				Arguments.of(List.of(new Card(Rank.ACE, Suit.CLUB), new Card(Rank.ACE, Suit.HEART),
					new Card(Rank.TEN, Suit.HEART)), 12),
				Arguments.of(List.of(new Card(Rank.ACE, Suit.CLUB), new Card(Rank.ACE, Suit.HEART),
					new Card(Rank.TEN, Suit.CLUB), new Card(Rank.TEN, Suit.HEART)), 22),
				Arguments.of(List.of(new Card(Rank.ACE, Suit.CLUB), new Card(Rank.ACE, Suit.HEART),
					new Card(Rank.ACE, Suit.SPADE)), 13),
				Arguments.of(List.of(new Card(Rank.ACE, Suit.CLUB), new Card(Rank.ACE, Suit.HEART),
					new Card(Rank.ACE, Suit.SPADE), new Card(Rank.ACE, Suit.DIAMOND)), 14)
			);
		}
	}

	@Nested
	@DisplayName("카드 카운트 계산")
	class CalculateCount {

		@DisplayName("손에 들고있는 카드의 수를 계산하라")
		@Test
		void calculateCardCount() {
			// given
			final List<Card> cards = List.of(
				new Card(Rank.TEN, Suit.CLUB),
				new Card(Rank.TEN, Suit.SPADE),
				new Card(Rank.TEN, Suit.HEART),
				new Card(Rank.TEN, Suit.DIAMOND)
			);
			final CardHand cardHand = new CardHand(cards);
			final Count expected = new Count(4);

			// when
			final Count actual = cardHand.calculateCardCount();

			// then
			assertThat(actual).isEqualTo(expected);
		}
	}

}
