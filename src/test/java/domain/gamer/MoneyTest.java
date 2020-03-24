package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import exception.InvalidMoneyException;

class MoneyTest {
	@Test
	@DisplayName("더블형태의 생성자를 지원하는지 테스트")
	void doubleConstructorTest() {
		assertThat(Money.of(10000.0)).isInstanceOf(Money.class);
	}

	@Test
	@DisplayName("문자열 생성자를 지원하는지 테스트")
	void stringConstructorTest() {
		assertThat(Money.of("10000.0")).isInstanceOf(Money.class);
	}

	@Test
	@DisplayName("정수형 생성자를 지원하는지 테스트")
	void intConstructorTest() {
		assertThat(Money.of(10000)).isInstanceOf(Money.class);
	}

	@Test
	@DisplayName("배팅금액이 음수인 경우 예외를 반환하는지 테스트")
	void minusBettingMoney() {
		assertThatThrownBy(() -> {
			Money.of(-10000);
		}).isInstanceOf(InvalidMoneyException.class);
	}

	@Test
	@DisplayName("배팅금액이 Double로 변환이 불가능한 경우 예외를 반환하는지 테스트")
	void nullBettingMoney() {
		assertThatThrownBy(() -> {
			Money.of("1000a");
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3000.0,1 ,3000.0", "3000.0, 1.5, 4500.0", "1000,-1,-1000"})
	@DisplayName("곱하 연산을 올바르게 수행하는지 테스트")
	void multiplyTest(double first, double second, double expected) {
		assertThat(Money.of(first).multiply(second)).isEqualTo(expected);
	}
}