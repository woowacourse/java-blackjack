package duel;

import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import card.Card;
import card.CardHand;
import card.Rank;
import card.Score;
import card.Suit;

class CardHandTest {

	@Nested
	@DisplayName("모든 Card의 점수 합을 계산한다.")
	class CalculateAllScore {

		@ParameterizedTest
		@MethodSource("getCardList")
		@DisplayName("주어진 모든 Card 점수 합을 계산하여 반환한다.")
		void test_calculateAllScore(List<Card> cards, int expected) {
			//given
			final var cardHand = new CardHand(cards);
			final Score bustScore = Score.from(21);

			//when&then
			Assertions.assertThat(cardHand.calculateAllScore(bustScore)).isEqualTo(Score.from(expected));
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

}
