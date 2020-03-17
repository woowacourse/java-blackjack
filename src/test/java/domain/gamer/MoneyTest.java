package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

	@ParameterizedTest
	@CsvSource(value = {"3000.0,5000.0,8000.0", "3000.0,2000.0, 5000.0"})
	@DisplayName("더하기 연산을 올바르게 수행하는지 테스트")
	void plusTest(double first, double second, double expected) {
		assertThat(new Money(first).plus(new Money(second))).isEqualTo(new Money(expected));
	}

	@ParameterizedTest
	@CsvSource(value = {"3000.0,1 ,3000.0", "3000.0, 1.5, 4500.0", "1000,-1,-1000"})
	@DisplayName("더하기 연산을 올바르게 수행하는지 테스트")
	void multiplyTest(double first, double second, double expected) {
		assertThat(new Money(first).multiply(second)).isEqualTo(new Money(expected));
	}
}