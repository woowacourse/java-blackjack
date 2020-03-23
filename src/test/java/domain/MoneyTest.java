package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {
	@DisplayName("0이하의 돈 입력 테스트")
	@Test
	void valueOf1() {
		assertThatThrownBy(() -> Money.valueOf(0))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("0보다 작은 수를 입력하였습니다.");
	}

	@DisplayName("숫자가 아닌 문자 입력")
	@Test
	void valueOf2() {
		assertThatThrownBy(() -> Money.valueOf("a"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("숫자가 아닌 문자를 입력하였습니다.");
	}
}
