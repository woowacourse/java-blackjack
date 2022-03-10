package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.factory.CardMockFactory;
import blackjack.domain.util.CreateHand;

@DisplayName("Dealer 테스트")
class DealerTest {

	@DisplayName("딜러가 현재 가지고 있는 패에 따라 한 장을 더 드로우 할지 말지를 결정한다")
	@ParameterizedTest(name = "{index} {displayName} hand={0} expectedResult={1} drawSelect={2}")
	@MethodSource("createHand")
	void drawableTest(final Hand hand, final boolean expectedResult, final boolean drawSelect) {
		Role dealer = new Dealer(hand, () -> drawSelect);
		assertThat(dealer.canDraw()).isEqualTo(expectedResult);
	}

	private static Stream<Arguments> createHand() {
		final Hand matchOptimalCase = CreateHand.create(CardMockFactory.of("A클로버"), CardMockFactory.of("K클로버"));
		final Hand randomDrawWithAce = CreateHand.create(CardMockFactory.of("A클로버"), CardMockFactory.of("8클로버"));
		final Hand drawWithAce = CreateHand.create(CardMockFactory.of("A클로버"), CardMockFactory.of("5클로버"));
		final Hand notDrawWithoutAce = CreateHand.create(CardMockFactory.of("7클로버"), CardMockFactory.of("K클로버"));
		final Hand drawWithoutAce = CreateHand.create(CardMockFactory.of("6클로버"), CardMockFactory.of("K클로버"));

		return Stream.of(
			Arguments.of(matchOptimalCase, false, true),
			Arguments.of(randomDrawWithAce, false, false),
			Arguments.of(randomDrawWithAce, true, true),
			Arguments.of(drawWithAce, true, false),
			Arguments.of(notDrawWithoutAce, false, true),
			Arguments.of(drawWithoutAce, true, false)
		);
	}

}

