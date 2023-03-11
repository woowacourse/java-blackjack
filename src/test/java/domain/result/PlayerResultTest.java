package domain.result;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Player;
import domain.TestData;

class PlayerResultTest {

	@Test
	@DisplayName("점수가 21인 플레이어가 승리해야 한다.")
	public void playerVictoryTest() {
		Player score21Player = TestData.getScore21Player();
		Player score19Player = TestData.getScore19Player();

		PlayerResult winResult = PlayerResult.decide(score21Player, score19Player);
		PlayerResult lossResult = PlayerResult.decide(score19Player, score21Player);

		Assertions.assertThat(winResult.getResultState()).isEqualTo(ResultState.WIN);
		Assertions.assertThat(lossResult.getResultState()).isEqualTo(ResultState.LOSS);
	}

	@Test
	@DisplayName("동일 점수끼리는 무승부의 결과를 가져야 한다.")
	public void playerDrawTest() {
		Player player1 = TestData.getScore21Player();
		Player player2 = TestData.getScore21Player();

		PlayerResult result = PlayerResult.decide(player1, player2);

		Assertions.assertThat(result.getResultState()).isEqualTo(ResultState.DRAW);
	}
}
