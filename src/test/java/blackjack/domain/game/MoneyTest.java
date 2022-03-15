package blackjack.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Money 테스트")
class MoneyTest {

	@Test
	@DisplayName("돈 증가 검증")
	void increase_Money() {
		Money money = new Money(0);
		Money increasedMoney = money.increase(new Money(10000));
		assertThat(increasedMoney.getValue()).isEqualTo(10000);
	}

	@Test
	@DisplayName("돈 감소 검증")
	void decrease_Money() {
		Money money = new Money(10000);
		Money decreasedMoney = money.decrease(new Money(10000));
		assertThat(decreasedMoney.getValue()).isEqualTo(0);
	}
}
