package domains.user.money;

import static org.assertj.core.api.Assertions.*;

import java.util.Collection;
import java.util.HashSet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class ProfitMoneyTest {
	@DisplayName("문자열을 입력받아 객체를 생성")
	@Test
	void constructor_String_CreateBettingMoney() {
		Assertions.assertThat(new ProfitMoney("1")).isInstanceOf(Money.class);
	}

	@DisplayName("null 혹은 빈 문자열이 입력되었을 경우 InvalidMoneyException 발생")
	@ParameterizedTest
	@NullAndEmptySource
	void constructor_NullAndEmpty_ExceptionThrown(String money) {
		assertThatThrownBy(() -> new ProfitMoney(money))
			.isInstanceOf(InvalidMoneyException.class)
			.hasMessage(InvalidMoneyException.NULL_OR_EMPTY);
	}

	@DisplayName("문자가 입력되었을 경우 InvalidMoneyException 발생")
	@Test
	void constructor_NotNumberFormatString_ExceptionThrown() {
		assertThatThrownBy(() -> new ProfitMoney("%"))
			.isInstanceOf(InvalidMoneyException.class)
			.hasMessage(InvalidMoneyException.NOT_NUMBER_FORMAT);
	}

	@DisplayName("플레이어들의 수익이 들어올 경우 딜러의 수익 계산")
	@Test
	void calculateDealerProfit_GivenPlayerProfits_ReturnDealerProfit() {
		Collection<ProfitMoney> profits = new HashSet<>();
		profits.add(new ProfitMoney(-10000));
		profits.add(new ProfitMoney(10000));

		ProfitMoney dealerProfit = ProfitMoney.calculateDealerProfit(profits);
		ProfitMoney expectedProfit = new ProfitMoney(0);

		assertThat(dealerProfit).isEqualTo(expectedProfit);

	}
}
