package domain.result;

import static domain.card.Symbol.*;
import static domain.card.Type.*;
import static domain.result.MatchResult.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Money;
import domain.user.Player;

@SuppressWarnings("NonAsciiCharacters")
class MatchResultTest {
	@DisplayName("플레이어와 딜러의 점수 비교후, 플레이어의 패배 반환")
	@ParameterizedTest
	@MethodSource("playerAndDealerLoseSet")
	void calculatePlayerMatchResultLoseTest(Player player, Dealer dealer) {
		MatchResult actual = calculatePlayerMatchResult(player, dealer);
		assertThat(actual).isEqualTo(LOSE);
	}

	private static Stream<Arguments> playerAndDealerLoseSet() {
		return Stream.of(
			Arguments.of(
				Player.fromNameAndMoneyAndCards("test", 1_000, new Card(HEART, EIGHT)),
				Dealer.fromCards(new Card(HEART, EIGHT))
			),
			Arguments.of(
				Player.fromNameAndMoneyAndCards("test2", 1_000, new Card(HEART, EIGHT)),
				Dealer.fromCards(new Card(HEART, NINE))
			),
			Arguments.of(
				Player.fromNameAndMoneyAndCards("test3", 1_000, new Card(HEART, EIGHT), new Card(HEART, KING),
					new Card(HEART, THREE)),
				Dealer.fromCards(new Card(HEART, QUEEN), new Card(CLOVER, ACE))
			),
			Arguments.of(
				Player.fromNameAndMoneyAndCards("test4", 1_000, new Card(HEART, EIGHT), new Card(HEART, KING)),
				Dealer.fromCards(new Card(HEART, QUEEN), new Card(CLOVER, ACE))
			),
			Arguments.of(
				Player.fromNameAndMoneyAndCards("test5", 1_000, new Card(HEART, EIGHT), new Card(HEART, KING),
					new Card(HEART, TEN)),
				Dealer.fromCards(new Card(HEART, QUEEN), new Card(CLOVER, ACE))
			),
			Arguments.of(
				Player.fromNameAndMoneyAndCards("test6", 1_000, new Card(HEART, EIGHT), new Card(HEART, KING),
					new Card(HEART, TEN)),
				Dealer.fromCards(new Card(HEART, QUEEN), new Card(CLOVER, TEN), new Card(DIAMOND, TEN))
			)
		);
	}

	@DisplayName("플레이어와 딜러 둘다 블랙잭인 경우, 플레이어 무승부 반환")
	@ParameterizedTest
	@MethodSource("playerAndDealerDrawSet")
	void calculatePlayerMatchResultDrawTest(Player player, Dealer dealer) {
		MatchResult actual = calculatePlayerMatchResult(player, dealer);
		assertThat(actual).isEqualTo(DRAW);
	}

	private static Stream<Arguments> playerAndDealerDrawSet() {
		return Stream.of(
			Arguments.of(
				Player.fromNameAndMoneyAndCards("test2", 1_000, new Card(HEART, ACE), new Card(DIAMOND, KING)),
				Dealer.fromCards(new Card(HEART, QUEEN), new Card(CLOVER, ACE))
			)
		);
	}

	@DisplayName("플레이어와 딜러의 점수 비교후, 플레이어의 승리 반환")
	@ParameterizedTest
	@MethodSource("playerAndDealerWinSet")
	void calculatePlayerMatchResultWinTest(Player player, Dealer dealer) {
		MatchResult actual = calculatePlayerMatchResult(player, dealer);
		assertThat(actual).isEqualTo(WIN);
	}

	private static Stream<Arguments> playerAndDealerWinSet() {
		return Stream.of(
			Arguments.of(
				Player.fromNameAndMoneyAndCards("test", 1_000, new Card(HEART, NINE)),
				Dealer.fromCards(new Card(HEART, EIGHT))
			),
			Arguments.of(
				Player.fromNameAndMoneyAndCards("test2", 1_000, new Card(HEART, ACE), new Card(HEART, NINE)),
				Dealer.fromCards(new Card(DIAMOND, ACE), new Card(SPADE, ACE), new Card(DIAMOND, SIX))
			),
			Arguments.of(
				Player.fromNameAndMoneyAndCards("test3", 1_000, new Card(HEART, EIGHT), new Card(HEART, KING)),
				Dealer.fromCards(new Card(HEART, QUEEN), new Card(CLOVER, TEN), new Card(CLOVER, JACK))
			)
		);
	}

	@DisplayName("플레이어와 딜러의 점수 비교후, 플레이어의 블랙잭 승 반환")
	@ParameterizedTest
	@MethodSource("playerBlackjackWinSet")
	void calculatePlayerMatchResultBlackjackWinTest(Player player, Dealer dealer) {
		MatchResult actual = calculatePlayerMatchResult(player, dealer);
		assertThat(actual).isEqualTo(BLACKJACK_WIN);
	}

	private static Stream<Arguments> playerBlackjackWinSet() {
		return Stream.of(
			Arguments.of(
				Player.fromNameAndMoneyAndCards("test2", 1_000, new Card(HEART, ACE), new Card(HEART, TEN)),
				Dealer.fromCards(new Card(DIAMOND, ACE), new Card(SPADE, ACE), new Card(DIAMOND, SIX))
			),
			Arguments.of(
				Player.fromNameAndMoneyAndCards("test3", 1_000, new Card(HEART, ACE), new Card(HEART, KING)),
				Dealer.fromCards(new Card(HEART, QUEEN), new Card(CLOVER, TEN), new Card(CLOVER, ACE))
			)
		);
	}

	@DisplayName("게임 결과별 배당금 계산")
	@ParameterizedTest
	@MethodSource("게임결과별_배당금계산_테스트_세트")
	void calculate_final_prize_test(MatchResult result, int bettingMoney, int expected) {
		assertThat(result.calculatePrize(Money.of(bettingMoney))).isEqualTo(expected);
	}

	private static Stream<Arguments> 게임결과별_배당금계산_테스트_세트() {
		return Stream.of(
			Arguments.of(BLACKJACK_WIN, 1_000, 1_500),
			Arguments.of(WIN, 1_000, 1_000),
			Arguments.of(DRAW, 1_000, 0),
			Arguments.of(LOSE, 1_000, -1_000)
		);
	}
}