package blackjack.domain.game;

import static blackjack.factory.HandMockFactory.getBlackJackHand;
import static blackjack.factory.HandMockFactory.getBottomHand;
import static blackjack.factory.HandMockFactory.getNotBlackjackTopHand;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.role.Dealer;
import blackjack.domain.role.DealerDrawable;
import blackjack.domain.role.Player;
import blackjack.domain.role.Role;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Betting 테스트")
class BettingTest {

	@DisplayName("배팅 금액을 결과에 따라서 정산하는 메서드 검증")
	@ParameterizedTest(name = "{index} {displayName} expectedValue: {2}")
	@MethodSource("createRole")
	void check_Settle(Role player, Role dealer, int expectedValue) {
		Compete compete = new Compete();
		compete.judgeCompete(player, dealer);
		Map<Role, Money> bettingMoney = new HashMap<>();
		bettingMoney.put(player, new Money("10000"));
		Betting betting = new Betting(bettingMoney);
		Map<Role, Money> afterSettle = betting.settle(compete);
		assertThat(afterSettle.get(player)
				.getValue()
				.intValue()
		).isEqualTo(expectedValue);
	}

	private static Stream<Arguments> createRole() {
		return Stream.of(
				Arguments.of(new Player("player", getBlackJackHand()),
						new Dealer(getNotBlackjackTopHand(), DealerDrawable::chooseDraw), 15000),
				Arguments.of(new Player("player", getNotBlackjackTopHand()),
						new Dealer(getBottomHand(), DealerDrawable::chooseDraw), 10000),

				Arguments.of(new Player("player", getNotBlackjackTopHand()),
						new Dealer(getBlackJackHand(), DealerDrawable::chooseDraw), -10000),

				Arguments.of(new Player("player", getBlackJackHand()),
						new Dealer(getBlackJackHand(), DealerDrawable::chooseDraw), 0)
		);
	}
}
