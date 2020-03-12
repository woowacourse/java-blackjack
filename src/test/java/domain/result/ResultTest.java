package domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
	@Test
	@DisplayName("플레이어의 승패와 대칭되는 딜러의 승패가 반환되는지")
	void reverseTest() {
		assertThat(Result.reverse(Result.WIN)).isEqualTo(Result.LOSE);
		assertThat(Result.reverse(Result.LOSE)).isEqualTo(Result.WIN);
		assertThat(Result.reverse(Result.DRAW)).isEqualTo(Result.DRAW);
	}

	@Test
	@DisplayName("점수비교를 통해 올바른 결과(Enum)을 생성하는지 테스트")
	void ofTest() {
		assertThat(Result.of(Score.of(10), Score.of(17))).isEqualTo(Result.LOSE);
		assertThat(Result.of(Score.of(21), Score.of(10))).isEqualTo(Result.WIN);
		assertThat(Result.of(Score.of(5), Score.of(5))).isEqualTo(Result.DRAW);
	}
}