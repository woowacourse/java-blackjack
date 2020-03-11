package blackjack.player;

import blackjack.player.card.CardBundle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

	private static Stream<Arguments> playerProvider() {
		return Stream.of(
			Arguments.of(
				new Dealer(new CardBundle()), false, true
			),
			Arguments.of(
				new Gambler(new CardBundle(), "pobi"), true, false
			)
		);
	}

	@DisplayName("플레이어가 겜블러인지, 딜러인지 확인")
	@ParameterizedTest
	@MethodSource("playerProvider")
	void test(Player player, boolean gamblerResult, boolean dealerResult) {
		assertThat(player.isDealer()).isEqualTo(dealerResult);
		assertThat(player.isGambler()).isEqualTo(gamblerResult);
	}

}