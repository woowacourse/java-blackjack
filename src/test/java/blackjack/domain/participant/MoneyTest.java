package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

	@DisplayName("배팅 금액이 음수면 예외를 반환한다")
	@Test
	void exception_positive() {
		assertThatThrownBy(() -> new Money(-100)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 배팅 금액은 양수로 입력해야 합니다.");

	}

	@DisplayName("배팅 금액이 10원 단위가 아니면 예외를 반환한다")
	@Test
	void exception_unit() {
		assertThatThrownBy(() -> new Money(123456)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 배팅 금액은 10원 단위로 입력해야 합니다.");

	}
}
