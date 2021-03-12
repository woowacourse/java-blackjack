package blakcjack.domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static blakcjack.domain.money.Money.calculateDealerProfitFrom;
import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
	@DisplayName("플레이어의 수익들로부터 딜러의 수익을 제대로 계산해 내는지")
	@Test
	void calculateDealerProfitFrom_collectionOfMoney_returnDealerProfit() {
		final Money dealerProfit = calculateDealerProfitFrom(Arrays.asList(
				new Money(100),
				new Money(300),
				new Money(-200),
				new Money(-50)
		));
		assertThat(dealerProfit).isEqualTo(new Money(-150));
	}

	@DisplayName("수익률이 주어졌을 때 수익을 제대로 계산 하는지")
	@Test
	void calculateProfit_earningRate_returnProfit() {
		final Money money = new Money(100);
		assertThat(money.calculateProfit(1.5f)).isEqualTo(new Money(150));
	}
}