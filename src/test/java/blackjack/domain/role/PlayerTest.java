package blackjack.domain.role;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.KING;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.factory.CardMockFactory;
import blackjack.factory.HandMockFactory;
import blackjack.util.CreateHand;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
		return Stream.of(
				Arguments.of(HandMockFactory.getBlackJackHand(), true),
				Arguments.of(HandMockFactory.getBustHand(), false),
				Arguments.of(HandMockFactory.getNotBlackjackTopHand(), true)
		);
	}

	@Test
	@DisplayName("플레이어의 패 오픈 전략 확인")
	void check_Open_Hand() {
		final Hand hand = CreateHand.create(CardMockFactory.of(ACE), CardMockFactory.of(KING));
		Role player = new Player("player", hand);
		List<Card> expectedOpenCards = List.of(CardMockFactory.of(ACE), CardMockFactory.of(KING));
		assertThat(player.openHand()).isEqualTo(expectedOpenCards);
	}
}
