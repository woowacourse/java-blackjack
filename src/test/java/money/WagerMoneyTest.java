package money;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import paticipant.Player;
import value.Money;

public class WagerMoneyTest {

	@Nested
	@DisplayName("생성")
	class WagerMoneyConstruct {

		@DisplayName("생성 테스트")
		@Test
		void wagerMoneyConstruct() {
			// given
			final Map<Player, Money> money = new HashMap<>();

			// when
			final WagerMoney wagerMoney = new WagerMoney(money);

			// then
			assertThat(wagerMoney.getMoney()).isEqualTo(money);
		}
	}
}
