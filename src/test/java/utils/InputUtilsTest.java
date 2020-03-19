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

	@DisplayName("int 로 변환 테스트")
	@Test
	void toInt() {
		String input = "500";

		assertThat(InputUtils.toInt(input)).isEqualTo(500);
	}

	@DisplayName("문자가 아닌 문자 입력시 toInt 테스트")
	@Test
	void toIntWithError() {
		String input = "a1";

		assertThatThrownBy(() -> InputUtils.toInt(input))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("숫자가 아닌 문자를 입력하였습니다.");
	}
}
