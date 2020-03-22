package domain.result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {
	private Score twentyOne, ten;

	@BeforeEach
	void setUp() {
		twentyOne = Score.from(21);
		ten = Score.from(10);
	}

	@Test
	@DisplayName("점수가 큰지 비교하는 테스")
	void compareBiggerTest() {
		assertThat(twentyOne.isBiggerThan(ten)).isTrue();
	}

	@Test
	@DisplayName("점수가 작은지 비교하는 테스")
	void compareLowerTest() {
		assertThat(ten.isLowerThan(twentyOne)).isTrue();
	}

	@Test
	@DisplayName("점수가 같은지 비교하는 테스")
	void compareEqualTest() {
		assertThat(twentyOne.isEqualTo(twentyOne)).isTrue();
	}
}