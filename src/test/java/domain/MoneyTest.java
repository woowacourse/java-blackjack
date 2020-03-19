package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {
	@DisplayName("0이하의 돈 입력 테스트")
	@Test
	void money() {
		double zero = 0;
		assertThatThrownBy(() -> new Money(zero))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("0보다 작은 수를 입력하였습니다.");
	}
}
