package value;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import money.Money;

public class MoneyTest {

	@Nested
	@DisplayName("Money 생성")
	class MoneyConstruct {

		@DisplayName("Money를 생성하라")
		@Test
		void moneyConstruct() {
			// given
			final int moneyValue = 1_000;

			// when
			final Money money = new Money(moneyValue);

			// then
			assertThat(money.getValue()).isEqualTo(moneyValue);
		}
	}

	@Nested
	@DisplayName("Money 연산")
	class MoneyCalculate {

		@DisplayName("x배 만큼의 money를 계산하여 반환한다.")
		@Test
		void multiply() {
			// given
			final int value = 10_000;
			final Money money = new Money(value);
			final double scale = 2.5;

			// when
			final Money actual = money.multiply(scale);

			// then
			assertThat(actual.getValue()).isEqualTo(25_000);
		}
	}
}
