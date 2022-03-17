package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

	@DisplayName("배팅 금액이 공백이면 예외를 반환한다")
	@Test
	void exception_blank() {
		assertThatThrownBy(() -> Money.from("")).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 배팅 금액은 공백일 수 없습니다.");

	}

	@DisplayName("배팅 금액이 숫자가 아니면 예외를 반환한다")
	@Test
	void exception_type() {
		assertThatThrownBy(() -> Money.from("!!")).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 배팅 금액은 숫자로 입력해야 합니다");

	}

	@DisplayName("배팅 금액이 음수면 예외를 반환한다")
	@Test
	void exception_positive() {
		assertThatThrownBy(() -> Money.from("-100")).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 배팅 금액은 양수로 입력해야 합니다.");

	}

	@DisplayName("배팅 금액이 10원 단위가 아니면 예외를 반환한다")
	@Test
	void exception_unit() {
		assertThatThrownBy(() -> Money.from("123456")).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 배팅 금액은 10원 단위로 입력해야 합니다.");

	}
}
