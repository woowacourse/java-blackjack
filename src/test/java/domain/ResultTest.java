package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResultTest {
	private Result result;

	@BeforeEach
	void setup() {
		result = new Result("안녕", 3, 5);
	}

	@Test
	void getName() {
		assertThat(result.getName()).isEqualTo("안녕");
	}

	@Test
	void getWinCount() {
		assertThat(result.getWinCount()).isEqualTo(3);
	}

	@Test
	void getLoseCount() {
		assertThat(result.getLoseCount()).isEqualTo(5);
	}

	@Test
	void hasMany() {
		assertThat(result.hasMany()).isTrue();
	}

	@Test
	void hasWin() {
		assertThat(result.hasWin()).isTrue();
	}
}
