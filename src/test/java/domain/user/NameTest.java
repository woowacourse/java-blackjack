package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static domain.user.Name.INPUT_EMPTY_NAME;
import static domain.user.Name.INPUT_NULL_NAME;
import static org.assertj.core.api.Assertions.*;

public class NameTest {

	@DisplayName("유효한 이름입력시 객체 생성")
	@Test
	void create_When_Right_Name_Create_Instance() {
		assertThat(new Name("toney")).isInstanceOf(Name.class);
	}

	@DisplayName("빈 문자열 입력시 예외처리")
	@EmptySource
	@ParameterizedTest
	void create_When_Empty_Input_Throw_Exception(String emptyName) {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			new Name(emptyName);
		}).withMessage(INPUT_EMPTY_NAME);
	}

	@DisplayName("null 입력시 예외처리")
	@NullSource
	@ParameterizedTest
	void create_When_Null_Input_Throw_Exception(String nullName) {
		assertThatNullPointerException().isThrownBy(() -> {
			new Name(nullName);
		}).withMessage(INPUT_NULL_NAME);
	}
}
