package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.factory.CardMockFactory;

@DisplayName("Hand 테스트")
class HandTest {

	@Test
	@DisplayName("드로우한 카드가 제대로 추가되는지 확인")
	void addCard() {
		final Hand hand = new Hand();
		final Card card = CardMockFactory.of("A클로버");

		hand.addCard(card);
		final List<Card> expectedHand = List.of(card);
		final List<Card> actualHand = hand.getCards();

		assertThat(actualHand.containsAll(expectedHand)).isTrue();
	}

	@DisplayName("현재 패에 가지고 있는 최적의 점수 계산 확인")
	@ParameterizedTest(name = "{index} {displayName} cards={0}")
	@MethodSource("getHandAndScore")
	void check_Calculated_Score(Hand hand, int expectedScore) {
		final int actualScore = hand.calculateOptimalScore();
		assertThat(actualScore).isEqualTo(expectedScore);
	}

	private static Stream<Arguments> getHandAndScore() {
		final Hand hand1 = createHand(CardMockFactory.of("A클로버"), CardMockFactory.of("K클로버"));
		final Hand hand2 = createHand(CardMockFactory.of("A클로버"), CardMockFactory.of("K클로버"),
			CardMockFactory.of("J클로버"));
		final Hand hand3 = createHand(CardMockFactory.of("10클로버"), CardMockFactory.of("K클로버"),
			CardMockFactory.of("J클로버"));
		final Hand hand4 = createHand(CardMockFactory.of("10클로버"), CardMockFactory.of("K클로버"));

		return Stream.of(
			Arguments.of(hand1, 21),
			Arguments.of(hand2, 21),
			Arguments.of(hand3, 0),
			Arguments.of(hand4, 20));
	}

	private static Hand createHand(Card... cards) {
		final Hand hand = new Hand();
		for (Card card : cards) {
			hand.addCard(card);
		}
		return hand;
	}
}
