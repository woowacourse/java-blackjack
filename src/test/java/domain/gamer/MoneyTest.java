package domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {
	@Test
	@DisplayName("더블형태의 생성자를 지원하는지 테스트")
	void doubleConstructorTest() {
		assertThat(new Money(10000.0)).isInstanceOf(Money.class);
	}

	@Test
	@DisplayName("문자열 생성자를 지원하는지 테스트")
	void stringConstructorTest() {
		assertThat(new Money("10000.0")).isInstanceOf(Money.class);
	}

	@Test
	@DisplayName("정수형 생성자를 지원하는지 테스트")
	void intConstructorTest() {
		assertThat(new Money(10000)).isInstanceOf(Money.class);
	}

	@Test
	@DisplayName("배팅금액이 음수인 경우 예외를 반환하는지 테스트")
	void minusBettingMoney() {
		assertThatThrownBy(() -> {
			new Money(-10000);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("배팅금액이 Double로 변환이 불가능한 경우 예외를 반환하는지 테스트")
	void nullBettingMoney() {
		assertThatThrownBy(() -> {
			new Money("1000a");
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3000.0,1 ,3000.0", "3000.0, 1.5, 4500.0", "1000,-1,-1000"})
	@DisplayName("곱하기 연산을 올바르게 수행하는지 테스트")
	void multiplyTest(double first, double second, double expected) {
		assertThat(new Money(first).multiply(second)).isEqualTo(expected);
	}
}