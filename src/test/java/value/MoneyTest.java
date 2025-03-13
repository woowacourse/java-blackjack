package value;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
}
