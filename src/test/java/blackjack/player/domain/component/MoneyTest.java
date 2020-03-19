package blackjack.player.domain.component;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

class MoneyTest {

	@DisplayName("머니를 생성할때 널을 입력하면 Exception")
	@ParameterizedTest
	@NullSource
	void createMoney(String money) {
		assertThatThrownBy(() -> {
			Money.createMoney(money);
		}).isInstanceOf(RuntimeException.class);
	}

	@DisplayName("머니를 생성할때 문자를 입력하면 Exception")
	@Test
	void createMoney2() {
		assertThatThrownBy(() -> {
			Money.createMoney("만원");
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("머니에서 인수를 곱했을때 제대로 곱해지는지 확인")
	@Test
	void multiply() {
		Money thousand = Money.createMoney(1000);
		double profit = 1.5;
		Money thousandFiveHundred = Money.createMoney(1500);

		Money actual = thousand.multiply(profit);
		assertThat(actual).isEqualTo(thousandFiveHundred);
	}

	@DisplayName("머니와 정수를 비교하여 정수보다 낮은지 확인")
	@ParameterizedTest
	@CsvSource(value = {"1000,true", "999,false", "998,false"})
	void isLessThan(int value, boolean expect) {
		Money money = Money.createMoney(999);

		assertThat(money.isLessThan(value)).isEqualTo(expect);
	}
}