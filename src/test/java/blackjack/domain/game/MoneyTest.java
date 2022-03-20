package blackjack.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Money 테스트")
class MoneyTest {

	@Test
	@DisplayName("돈 합셈 검증")
	void sum_Monies() {
		List<Money> monies = new ArrayList<>();
		monies.add(new Money(1000));
		monies.add(new Money(2000));
		Money money = Money.sumOf(monies);
		assertThat(money.getValue()).isEqualTo(3000);
	}

	@DisplayName("돈 곱셈 검증")
	@ParameterizedTest(name = "{index} {displayName} money: {0}, multiplier: {1}")
	@CsvSource(value = {"100, 1, 100", "1000, 1.5, 1500", "100, -1, -100", "100, 0, 0"})
	void multiply_Money(double money, double multiplier, int expectedValue) {
		Money initMoney = new Money(money);
		Money multipliedMoney = initMoney.multiply(multiplier);
		assertThat(multipliedMoney.getValue()).isEqualTo(expectedValue);
	}

	@Test
	@DisplayName("돈 값에 마이너스 부호를 붙이는 메서드 검증")
	void get_Minus_Value() {
		Money money = new Money(1000);
		assertThat(money.getMinusValue()).isEqualTo(-1000);
	}
}

