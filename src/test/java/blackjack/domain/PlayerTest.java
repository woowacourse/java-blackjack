package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Hand;
import blackjack.domain.factory.CardMockFactory;
import blackjack.domain.role.Player;
import blackjack.domain.role.Role;
import blackjack.domain.util.CreateHand;

@DisplayName("Player 테스트")
class PlayerTest {

	@DisplayName("플레이어가 현재 가지고 있는 패에 따라 한 장을 더 드로우 할지 말지를 결정한다")
	@ParameterizedTest(name = "{index} {displayName} hand={0}")
	@MethodSource("createHand")
	void drawableTest(final Hand hand, final boolean expectedResult) {
		Role player = new Player("player", hand);
		assertThat(player.canDraw()).isEqualTo(expectedResult);
	}

	private static Stream<Arguments> createHand() {
		final Hand hand1 = CreateHand.create(CardMockFactory.of("A클로버"), CardMockFactory.of("K클로버"));
		final Hand hand2 = CreateHand.create(CardMockFactory.of("2클로버"), CardMockFactory.of("K클로버"),
			CardMockFactory.of("J클로버"));
		final Hand hand3 = CreateHand.create(CardMockFactory.of("A클로버"), CardMockFactory.of("K클로버"),
			CardMockFactory.of("J클로버"));

		return Stream.of(
			Arguments.of(hand1, true),
			Arguments.of(hand2, false),
			Arguments.of(hand3, true)
		);
	}

}
