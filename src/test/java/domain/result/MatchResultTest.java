package domain.result;

import static domain.card.Symbol.*;
import static domain.card.Type.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

class MatchResultTest {
	@DisplayName("플레이어와 딜러의 점수 비교후, 플레이어의 패배 반환")
	@ParameterizedTest
	@MethodSource("playerAndDealerLoseSet")
	void calculatePlayerMatchResultLoseTest(Player player, Dealer dealer) {
		MatchResult actual = MatchResult.calculatePlayerMatchResult(player, dealer);
		assertThat(actual).isEqualTo(MatchResult.LOSE);
	}

	private static Stream<Arguments> playerAndDealerLoseSet() {
		return Stream.of(
			Arguments.of(
				Player.fromNameAndCards("test", new Card(HEART, EIGHT)),
				Dealer.fromCards(new Card(HEART, EIGHT))
			),
			Arguments.of(
				Player.fromNameAndCards("test2", new Card(HEART, EIGHT)),
				Dealer.fromCards(new Card(HEART, NINE))
			),
			Arguments.of(
				Player.fromNameAndCards("test3", new Card(HEART, EIGHT), new Card(HEART, KING), new Card(HEART, THREE)),
				Dealer.fromCards(new Card(HEART, QUEEN), new Card(CLOVER, ACE))
			),
			Arguments.of(
				Player.fromNameAndCards("test4", new Card(HEART, EIGHT), new Card(HEART, KING)),
				Dealer.fromCards(new Card(HEART, QUEEN), new Card(CLOVER, ACE))
			),
			Arguments.of(
				Player.fromNameAndCards("test5", new Card(HEART, EIGHT), new Card(HEART, KING), new Card(HEART, TEN)),
				Dealer.fromCards(new Card(HEART, QUEEN), new Card(CLOVER, ACE))
			),
			Arguments.of(
				Player.fromNameAndCards("test6", new Card(HEART, EIGHT), new Card(HEART, KING), new Card(HEART, TEN)),
				Dealer.fromCards(new Card(HEART, QUEEN), new Card(CLOVER, TEN), new Card(DIAMOND, TEN))
			)
		);
	}

	@DisplayName("플레이어와 딜러 둘다 블랙잭인 경우, 플레이어 무승부 반환")
	@ParameterizedTest
	@MethodSource("playerAndDealerDrawSet")
	void calculatePlayerMatchResultDrawTest(Player player, Dealer dealer) {
		MatchResult actual = MatchResult.calculatePlayerMatchResult(player, dealer);
		assertThat(actual).isEqualTo(MatchResult.DRAW);
	}

	private static Stream<Arguments> playerAndDealerDrawSet() {
		return Stream.of(
			Arguments.of(
				Player.fromNameAndCards("test2", new Card(HEART, ACE), new Card(DIAMOND, KING)),
				Dealer.fromCards(new Card(HEART, QUEEN), new Card(CLOVER, ACE))
			)
		);
	}

	@DisplayName("플레이어와 딜러의 점수 비교후, 플레이어의 승리 반환")
	@ParameterizedTest
	@MethodSource("playerAndDealerWinSet")
	void calculatePlayerMatchResultWinTest(Player player, Dealer dealer) {
		MatchResult actual = MatchResult.calculatePlayerMatchResult(player, dealer);
		assertThat(actual).isEqualTo(MatchResult.WIN);
	}

	private static Stream<Arguments> playerAndDealerWinSet() {
		return Stream.of(
			Arguments.of(
				Player.fromNameAndCards("test", new Card(HEART, NINE)),
				Dealer.fromCards(new Card(HEART, EIGHT))
			),
			Arguments.of(
				Player.fromNameAndCards("test2", new Card(HEART, ACE), new Card(HEART, NINE)),
				Dealer.fromCards(new Card(DIAMOND, ACE), new Card(SPADE, ACE), new Card(DIAMOND, SIX))
			),
			Arguments.of(
				Player.fromNameAndCards("test3", new Card(HEART, EIGHT), new Card(HEART, KING)),
				Dealer.fromCards(new Card(HEART, QUEEN), new Card(CLOVER, TEN), new Card(CLOVER, JACK))
			)
		);
	}

	@DisplayName("플레이어와 딜러의 점수 비교후, 플레이어의 블랙잭 승 반환")
	@ParameterizedTest
	@MethodSource("playerBlackjackWinSet")
	void calculatePlayerMatchResultBlackjackWinTest(Player player, Dealer dealer) {
		MatchResult actual = MatchResult.calculatePlayerMatchResult(player, dealer);
		assertThat(actual).isEqualTo(MatchResult.BLACKJACK_WIN);
	}

	private static Stream<Arguments> playerBlackjackWinSet() {
		return Stream.of(
			Arguments.of(
				Player.fromNameAndCards("test2", new Card(HEART, ACE), new Card(HEART, TEN)),
				Dealer.fromCards(new Card(DIAMOND, ACE), new Card(SPADE, ACE), new Card(DIAMOND, SIX))
			),
			Arguments.of(
				Player.fromNameAndCards("test3", new Card(HEART, ACE), new Card(HEART, KING)),
				Dealer.fromCards(new Card(HEART, QUEEN), new Card(CLOVER, TEN), new Card(CLOVER, ACE))
			)
		);
	}

	@DisplayName("승,패 입력시 반대 결과 반환, 무승부 입력시 그대로 무승부 반환")
	@ParameterizedTest
	@CsvSource(value = {"WIN,LOSE", "DRAW,DRAW", "LOSE,WIN"})
	void reverseWinAndLoseTest(MatchResult inputResult, MatchResult expected) {
		assertThat(inputResult.switchWinAndLose()).isEqualTo(expected);
	}
}