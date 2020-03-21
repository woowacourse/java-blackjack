package domain.result;

import static domain.card.Symbol.*;
import static domain.card.Type.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

class GameResultTest {
	private Players players;
	private Dealer dealer;
	private GameResult gameResult;

	@BeforeEach
	void setUp() {
		players = new Players(Arrays.asList(
			Player.fromNameAndMoneyAndCards("test1", 1_000, Card.of(HEART, THREE), Card.of(HEART, TWO)),
			Player.fromNameAndMoneyAndCards("test2", 10_000, Card.of(CLOVER, SEVEN), Card.of(CLOVER, SIX)),
			Player.fromNameAndMoneyAndCards("test3", 100_000, Card.of(DIAMOND, EIGHT), Card.of(DIAMOND, SEVEN)),
			Player.fromNameAndMoneyAndCards("test4", 1_000_000, Card.of(SPADE, ACE), Card.of(SPADE, TEN))
		));
		dealer = Dealer.fromCards(Card.of(HEART, SEVEN), Card.of(HEART, SIX));
		gameResult = new GameResult(players, dealer);
	}

	@DisplayName("플레이어 일급컬렉션, 딜러 객체 중 적어도 하나가 null 인 경우, 결과 객체 생성시, IllegalException 발생")
	@ParameterizedTest
	@MethodSource("null_players_dealer_argument_set")
	void throw_exception_when_null_argument(Players players, Dealer dealer) {
		assertThatThrownBy(() -> new GameResult(players, dealer))
			.isInstanceOf(IllegalArgumentException.class);
	}

	private static Stream<Arguments> null_players_dealer_argument_set() {
		return Stream.of(
			Arguments.of(null, null),
			Arguments.of(null, new Dealer()),
			Arguments.of(new Players(Collections.singletonList(Player.fromNameAndMoney("test", 1_000))), null)
		);
	}

	@DisplayName("결과객체가 가지고 있는 모든 플레이어의 상금의 합에 -1을 곱한 값이 딜러의 상금이다.")
	@Test
	void calculateDealerPrizeTest() {
		int expectedDealerPrize = 1_000 - 100_000 - 1_500_000;
		assertThat(gameResult.calculateDealerPrize()).isEqualTo(Prize.valueOf(expectedDealerPrize));
	}

	@DisplayName("결과객체가 가지고 있는 모든 플레이어별 올바른 상금을 가지고 있는지 확인한다.")
	@Test
	void getPlayerPrizeResultTest() {
		Player test1 = Player.fromNameAndMoneyAndCards("test1", 1_000, Card.of(HEART, THREE), Card.of(HEART, TWO));
		Player test2 = Player.fromNameAndMoneyAndCards("test2", 10_000, Card.of(CLOVER, SEVEN), Card.of(CLOVER, SIX));
		Player test3 = Player.fromNameAndMoneyAndCards("test3", 100_000, Card.of(DIAMOND, EIGHT),
			Card.of(DIAMOND, SEVEN));
		Player test4 = Player.fromNameAndMoneyAndCards("test4", 1_000_000, Card.of(SPADE, ACE), Card.of(SPADE, TEN));

		assertThat(gameResult.getPlayerPrizeResult()).containsExactly(
			entry(test1, Prize.valueOf(-1_000)),
			entry(test2, Prize.valueOf(0)),
			entry(test3, Prize.valueOf(100_000)),
			entry(test4, Prize.valueOf(1_500_000))
		);
	}
}