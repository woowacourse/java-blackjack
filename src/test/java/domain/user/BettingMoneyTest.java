package domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {
	@DisplayName("배팅 금액이 양수가 아닌 경우, 예외 발생 테스트")
	@ParameterizedTest
	@ValueSource(ints = {0, -1})
	void throw_exception_if_not_enough_betting_money(int value) {
		assertThatThrownBy(() -> BettingMoney.of(value)).isInstanceOf(IllegalArgumentException.class);
	}
}