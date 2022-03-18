package blackjack.domain.game;

import static blackjack.domain.participant.PlayerTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

class GameResultTest {

	private static Player player1 = new Player(new Name("abc1"),
		new Cards(getCardList(Denomination.JACK, Denomination.ACE)));
	private static Player player2 = new Player(new Name("abc2"),
		new Cards(getCardList(Denomination.ACE, Denomination.NINE)));
	private static Player player3 = new Player(new Name("abc3"),
		new Cards(getCardList(Denomination.EIGHT, Denomination.NINE)));
	private static Player player4 = new Player(new Name("abc4"),
		new Cards(getCardList(Denomination.JACK, Denomination.NINE)));
	private static Dealer dealer = new Dealer(new Name("딜러"),
		new Cards(getCardList(Denomination.QUEEN, Denomination.NINE)));
	private Players players = new Players(Arrays.asList(player1, player2, player3, player4));
	private GameResult gameResult = new GameResult(players, dealer);

	@ParameterizedTest(name = "{0}")
	@MethodSource("provideParameters")
	@DisplayName("수익 계산")
	void calculate_player_revenue(String comment, Player player, double revenue) {
		assertThat(gameResult.calculatePlayerRevenue(player)).isEqualTo(revenue);
	}

	private static Stream<Arguments> provideParameters() {
		return Stream.of(
			Arguments.arguments("블랙잭으로 이긴 경우 배팅금액의 1.5배의 얻는다.", player1, 1500),
			Arguments.arguments("블랙잭은 아니지만 이긴 경우 배팅금액만큼 얻는다.", player2, 1000),
			Arguments.arguments("진 경우 배팅금액만큼 잃는다.", player3, -1000),
			Arguments.arguments("비긴 경우 수익은 없다.", player4, 0)
		);
	}

	@Test
	@DisplayName("딜러의 수익을 계산한다.")
	void calculate_dealer_revenue() {
		assertThat(gameResult.calculateDealerRevenue()).isEqualTo(-1500);
	}
}
