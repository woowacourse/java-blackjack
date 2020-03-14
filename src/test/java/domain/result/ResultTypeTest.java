package domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTypeTest {
	@Test
	@DisplayName("플레이어의 승패와 대칭되는 딜러의 승패가 반환되는지")
	void reverseTest() {
		assertThat(ResultType.reverse(ResultType.WIN)).isEqualTo(ResultType.LOSE);
		assertThat(ResultType.reverse(ResultType.LOSE)).isEqualTo(ResultType.WIN);
		assertThat(ResultType.reverse(ResultType.DRAW)).isEqualTo(ResultType.DRAW);
	}

	@Test
	@DisplayName("점수비교를 통해 올바른 결과(Enum)을 생성하는지 테스트")
	void ofTest() {
		assertThat(ResultType.of(Score.of(10), Score.of(17))).isEqualTo(ResultType.LOSE);
		assertThat(ResultType.of(Score.of(21), Score.of(10))).isEqualTo(ResultType.WIN);
		assertThat(ResultType.of(Score.of(5), Score.of(5))).isEqualTo(ResultType.DRAW);
	}
}