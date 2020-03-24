package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyFactoryTest {
	@Test
	@DisplayName("입력값에 따라 적절한 배팅머니 리스트를 반환하는지 테스트합니다.")
	void createMoney() {
		List<String> bettings = Arrays.asList(
			"10000",
			"30000",
			"2000"
		);
		List<Money> monies = MoneyFactory.create(bettings);
		for (Money money : monies) {
			assertThat(money).isInstanceOf(Money.class);
		}
	}
}