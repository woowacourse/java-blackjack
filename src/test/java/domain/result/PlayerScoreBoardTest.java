package domain.result;

import static domain.card.Symbol.*;
import static domain.card.Type.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.score.Score;
import domain.user.Dealer;
import domain.user.Player;

class PlayerScoreBoardTest {
	@DisplayName("스코어 보드에 생성시 User 에 null 이 들어가면 예외 발생")
	@Test
	void construct_score_board_with_null_user_exception_test() {
		assertThatNullPointerException().isThrownBy(() -> PlayerScoreBoard.of(null));
	}

	@DisplayName("of 정적 메서드를 통해 생성한 score 보드 객체가 점수값을 잘 가지고 있는지 테스트")
	@Test
	void construct_score_board_build_right_score_test() {
		Player player = Player.fromNameAndMoneyAndCards("test", 1_000, Card.of(HEART, TEN), Card.of(HEART, ACE));
		PlayerScoreBoard scoreBoard = PlayerScoreBoard.of(player);
		assertThat(scoreBoard.getScore()).isEqualTo(Score.ofValue(21));
	}

	@DisplayName("딜러의 점수와 비교후, 결과에 따른 배당 배수가 곱해진 상금이 계산된다.")
	@ParameterizedTest
	@MethodSource("dealerSet_comparing_with_player")
	void createPlayerResult(int playerBettingMoney, Card[] dealerCards, Prize expected) {
		Player player = Player.fromNameAndMoneyAndCards("test", playerBettingMoney, Card.of(HEART, TEN),
			Card.of(DIAMOND, TEN));
		Dealer dealer = Dealer.fromCards(dealerCards);
		PlayerScoreBoard playerScoreBoard = PlayerScoreBoard.of(player);
		UserResult playerResult = playerScoreBoard.createPlayerResult(DealerScoreBoard.of(dealer));
		assertThat(playerResult.getPrize()).isEqualTo(expected);
	}

	private static Stream<Arguments> dealerSet_comparing_with_player() {
		return Stream.of(
			Arguments.of(1_000, new Card[] {Card.of(CLOVER, TEN), Card.of(CLOVER, NINE)}, Prize.valueOf(1_000)),
			Arguments.of(1_000, new Card[] {Card.of(CLOVER, TEN), Card.of(SPADE, TEN)}, Prize.valueOf(0)),
			Arguments.of(1_000, new Card[] {Card.of(CLOVER, TEN), Card.of(CLOVER, ACE)}, Prize.valueOf(-1_000))
		);
	}
}