package blackjack.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

class GameResultTest {

	private static Player player1 = new Player(new Name("abc1"));
	private static Player player2 = new Player(new Name("abc2"));
	private static Player player3 = new Player(new Name("abc3"));
	private static Player player4 = new Player(new Name("abc4"));
	private static Dealer dealer = new Dealer(new Name("딜러"));
	private Players players;
	private GameResult gameResult;

	static void init() {
		player1.draw(Card.valueOf(Denomination.ACE, Suit.CLOVER));
		player1.draw(Card.valueOf(Denomination.JACK, Suit.CLOVER));
		player2.draw(Card.valueOf(Denomination.ACE, Suit.CLOVER));
		player2.draw(Card.valueOf(Denomination.NINE, Suit.CLOVER));
		player2.stay();
		player3.draw(Card.valueOf(Denomination.EIGHT, Suit.CLOVER));
		player3.draw(Card.valueOf(Denomination.NINE, Suit.CLOVER));
		player3.stay();
		player4.draw(Card.valueOf(Denomination.QUEEN, Suit.CLOVER));
		player4.draw(Card.valueOf(Denomination.NINE, Suit.CLOVER));
		player4.stay();
		dealer.draw(Card.valueOf(Denomination.JACK, Suit.CLOVER));
		dealer.draw(Card.valueOf(Denomination.NINE, Suit.CLOVER));
	}

	@BeforeEach
	void initialize() {
		players = new Players(Arrays.asList(player1, player2, player3, player4));
		gameResult = new GameResult(players.match(dealer));
	}

	@ParameterizedTest(name = "{0}")
	@MethodSource("provideParameters")
	@DisplayName("수익 계산")
	void calculate_player_revenue(String comment, Player player, double revenue) {
		assertThat(gameResult.calculatePlayerRevenue(player)).isEqualTo(revenue);
	}

	private static Stream<Arguments> provideParameters() {
		init();
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
