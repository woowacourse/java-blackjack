package domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {
	@Test
	@DisplayName("플레이어의 승패와 대칭되는 딜러의 승패가 반환되는지")
	void reverseTest() {
		assertThat(Result.reverse(Result.WIN)).isEqualTo(Result.LOSE);
		assertThat(Result.reverse(Result.LOSE)).isEqualTo(Result.WIN);
		assertThat(Result.reverse(Result.DRAW)).isEqualTo(Result.DRAW);
	}

	@Test
	@DisplayName("플레이어의 승패와 대칭되는 딜러의 승패가 반환되는지")
	void ofTest() {
		assertThat(Result.of(10,17)).isEqualTo(Result.LOSE);
		assertThat(Result.of(21,10)).isEqualTo(Result.WIN);
		assertThat(Result.of(5,5)).isEqualTo(Result.DRAW);
	}
}