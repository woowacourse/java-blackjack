package domain;

import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import constant.Suit;

class CardHandTest {

	@Nested
	@DisplayName("모든 Card의 점수 합을 계산한다.")
	class CalculateAllScore {

		@ParameterizedTest
		@MethodSource("getCardList")
		@DisplayName("모든 Card의 점수 합을 올바르게 계산한다.")
		void test_calculateAllScore(List<Card> cards, int expected) {
			//given
			var cardHand = new CardHand(cards);

			//when&then
			Assertions.assertThat(cardHand.calculateAllScore()).isEqualTo(expected);
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
