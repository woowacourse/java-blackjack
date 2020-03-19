package domain.betting;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

	@DisplayName("최소 금액을 넘지 않을 때")
	@Test
	void createTest() {
		assertThatThrownBy(() -> Money.from(-1))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("숫자가 아닐 때")
	@Test
	void createTest2() {
		assertThatThrownBy(() -> Money.from("둔덩"))
			.isInstanceOf(NumberFormatException.class);
	}

	@Test
	void calculateProfitTest() {
		Money money = Money.from(1000);

		assertThat(money.calculateProfit(1.5)).isEqualTo(1500);
	}

	@Test
	void calculateDifferentTest() {
		Money money = Money.from(1000);

		assertThat(money.calculateDifferent(2000)).isEqualTo(1000);
	}

}
