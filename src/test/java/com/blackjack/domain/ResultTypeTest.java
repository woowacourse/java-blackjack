package com.blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTypeTest {
	@DisplayName("왼쪽 점수가 오른쪽보다 큰 경우 WIN")
	@Test
	void of_LeftScoreBiggerThanRight_Win() {
		assertThat(ResultType.of(new Score(5), new Score(0))).isEqualTo(ResultType.WIN);
	}

	@DisplayName("점수가 동일한 경우 DRAW")
	@Test
	void of_SameScore_Draw() {
		assertThat(ResultType.of(new Score(5), new Score(5))).isEqualTo(ResultType.DRAW);
	}

	@DisplayName("왼쪽 점수가 오른쪽보다 작은 경우 LOSE")
	@Test
	void of_LeftScoreLessThanRight_Lose() {
		assertThat(ResultType.of(new Score(0), new Score(5))).isEqualTo(ResultType.LOSE);
	}

	@DisplayName("왼쪽 점수가 블랙잭인 경우 BLACKJACK_WIN")
	@Test
	void of_LeftIsBlackJackAndNotSameScore_BlackjackWin() {
		assertThat(ResultType.of(new Score(21, true), new Score(5)))
				.isEqualTo(ResultType.BLACKJACK_WIN);
	}

	@DisplayName("왼쪽 점수와 오른쪽 점수가 모두 블랙잭인 경우 DRAW")
	@Test
	void of_LeftAndRightAreBlackjack_Draw() {
		assertThat(ResultType.of(new Score(21, true), new Score(21, true)))
				.isEqualTo(ResultType.DRAW);
	}
}
