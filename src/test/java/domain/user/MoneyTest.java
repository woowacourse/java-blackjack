package domain.user;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

	@DisplayName("최소 금액을 넘지 않을 때")
	@Test
	void createTest() {
		assertThatThrownBy(() -> new Money(0))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("숫자가 아닐 때")
	@Test
	void createTest2() {
		assertThatThrownBy(() -> new Money("둔덩"))
			.isInstanceOf(NumberFormatException.class);
	}

	@Test
	void winTest() {
		Money money = new Money(1000);

		assertThat(money.win()).isEqualTo(new Money(2000));
	}

	@Test
	void blackjackWin() {
		Money money = new Money(1000);

		assertThat(money.blackjackWin()).isEqualTo(new Money(2500));
	}

	@Test
	void compareTest() {
		Money originMoney = new Money(1000);
		Money winMoney = new Money(2000);

		assertThat(originMoney.compare(winMoney)).isEqualTo(1000);
	}
}
