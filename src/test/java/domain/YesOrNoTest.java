package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class YesOrNoTest {
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"a", "응", "아니", "3"})
	void validateRightYesOrNoTest(String input) {
		assertThatThrownBy(() -> new YesOrNo(input)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void isContinueTest() {
		YesOrNo yes = new YesOrNo("y");
		YesOrNo no = new YesOrNo("n");

		assertThat(yes.isContinue()).isTrue();
		assertThat(no.isContinue()).isFalse();
	}
}