package domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PrizeTest {
	@DisplayName("주어진 곱셈 인자를 곱한 상금이 반환된다.")
	@ParameterizedTest
	@CsvSource(value = {"1.5,1500", "2.0,2000", "-1.0,-1000", "0.5,500"})
	void multiply(double multiFactor, int expectedPrize) {
		Prize prize = Prize.valueOf(1_000);
		Prize actual = prize.multiply(multiFactor);
		Prize expected = Prize.valueOf(expectedPrize);
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("플레이어의 상금으로 부터 딜러의 상금을 계산하기 위해 -1을 곱한 상금 객체를 반환한다.")
	@Test
	void calculateDealerPrize() {
		Prize prize = Prize.valueOf(1_000);
		Prize actual = prize.calculateDealerPrize();
		Prize expected = Prize.valueOf(-1_000);
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("두 상금을 입력 받아 둘 사이 합 연산이 이뤄진 상금 객체를 반환한다.")
	@Test
	void sum() {
		Prize firstPrize = Prize.valueOf(1_000);
		Prize secondPrize = Prize.valueOf(15_000);
		Prize actual = Prize.sum(firstPrize, secondPrize);
		Prize expected = Prize.valueOf(16_000);
		assertThat(actual).isEqualTo(expected);
	}
}