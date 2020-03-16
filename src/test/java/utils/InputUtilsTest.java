package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class InputUtilsTest {
	@DisplayName("isHit 동작 테스트")
	@Test
	void isHit() {
		String inputYes1 = "y";
		String inputYes2 = "Y";
		String inputNo1 = "n";
		String inputNo2 = "N";

		assertThat(InputUtils.isHitToBoolean(inputYes1)).isTrue();
		assertThat(InputUtils.isHitToBoolean(inputYes2)).isTrue();
		assertThat(InputUtils.isHitToBoolean(inputNo1)).isFalse();
		assertThat(InputUtils.isHitToBoolean(inputNo2)).isFalse();
	}

	@DisplayName("isHit 유효성검증 테스트")
	@Test
	void isHitWithError() {
		String inputOther = "a";

		assertThatThrownBy(() -> InputUtils.isHitToBoolean(inputOther))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("y,n 중 하나만 입력하세요.");
	}
}
