package domain.result;

import static domain.card.Symbol.*;
import static domain.card.Type.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.score.Score;
import domain.user.Dealer;
import domain.user.Player;

class ScoreBoardsTest {
	private ScoreBoards scoreBoards;
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		dealer = Dealer.fromCards(Card.of(HEART, ACE), Card.of(DIAMOND, NINE));
		List<Player> players = Arrays.asList(getFirstPlayer(), getSecondPlayer(), getThirdPlayer(), getFourthPlayer());
		scoreBoards = ScoreBoards.fromAllUsers(players, dealer);
	}

	@DisplayName("인자에 null 이 들어있는 경우 null pointer 예외 발생 시킨다.")
	@Test
	void construct_with_null_exception_Test() {
		assertThatThrownBy(() -> ScoreBoards.fromAllUsers(null, null)).isInstanceOf(NullPointerException.class);
	}

	@DisplayName("모든 참여자별 카드를 통해 점수를 제대로 계산하는지 테스트")
	@Test
	void getScoreBoardsTest() {
		assertThat(scoreBoards.getScoreBoards()).extracting("name", "score")
			.containsExactly(Tuple.tuple("test", Score.ofValue(12)), Tuple.tuple("test2", Score.ofValue(20)), Tuple.tuple("test3", Score.ofValue(21)),
				Tuple.tuple("test4", Score.ofValue(21)), Tuple.tuple("딜러", Score.ofValue(20)));
	}

	@DisplayName("모든 참여자별 최종 상금을 제대로 계산해오는지 테스트, (블랙잭 승 1.5배, 일반 승 1배, 무승부 0배, 패배 -1배)")
	@Test
	void findFinalPrizeTest() {
		UserResults actual = scoreBoards.calculateUsersResult();
		assertThat(actual.getPlayerResults()).extracting("user", "prize")
			.containsExactly(Tuple.tuple(dealer, -1_500), Tuple.tuple(getFirstPlayer(), -1_000),
				Tuple.tuple(getSecondPlayer(), 0), Tuple.tuple(getThirdPlayer(), 1_500),
				Tuple.tuple(getFourthPlayer(), 1_000));
	}

	private Player getFirstPlayer() {
		return Player.fromNameAndMoneyAndCards("test", 1_000, Card.of(HEART, TWO), Card.of(DIAMOND, JACK));
	}

	private Player getSecondPlayer() {
		return Player.fromNameAndMoneyAndCards("test2", 1_000, Card.of(DIAMOND, ACE), Card.of(SPADE, NINE));
	}

	private Player getThirdPlayer() {
		return Player.fromNameAndMoneyAndCards("test3", 1_000, Card.of(SPADE, ACE), Card.of(DIAMOND, QUEEN));
	}

	private Player getFourthPlayer() {
		return Player.fromNameAndMoneyAndCards("test4", 1_000, Card.of(CLOVER, ACE), Card.of(DIAMOND, FIVE),
			Card.of(CLOVER, FIVE));
	}
}