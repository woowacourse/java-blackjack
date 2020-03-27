package domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

	@Test
	@DisplayName("이름에 blank 값이 들어가면 예외를 제대로 처리하는지")
	void invalidNameTest() {
		assertThatThrownBy(() ->
			Name.create("")
		).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("이름은 blank값이 될 수 없습니다.");
	}

	@Test
	@DisplayName("이름에 null 값이 들어가면 예외를 제대로 처리하는지")
	void nullNameTest() {
		String nullString = null;
		assertThatThrownBy(() ->
			Name.create(nullString)
		).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("이름은 blank값이 될 수 없습니다.");
	}
}
