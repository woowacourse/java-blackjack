package com.blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class NameTest {
	@DisplayName("올바른 값을 넣었을때 인스턴스 생성")
	@Test
	void constructor() {
		assertThat(new Name("name")).isInstanceOf(Name.class);
	}

	@DisplayName("생성자의 인자로 null 또는 빈 문자열이 입력된 경우 예외 발생")
	@ParameterizedTest
	@NullAndEmptySource
	void constructor_NullOrEmpty_ExceptionThrown(String input) {
		assertThatThrownBy(() -> new Name(input)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("생성자의 인자로 공백이 포함된 문자열이 입력된 경우 예외 발생")
	@Test
	void constructor_ContainsBlank_ExceptionThrown() {
		assertThatThrownBy(() -> new Name("he ll")).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("생성자의 인자로 범위를 초과하는 길이의 문자열이 입력된 경우 예외 발생")
	@Test
	void constructor_OutOfLength_ExceptionThrown() {
		assertThatThrownBy(() -> new Name("1234567890")).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("같은 이름으로 생성된 객체를 비교할 경우 true 반환")
	@Test
	void equals() {
		Name name1 = new Name("a");
		Name name2 = new Name("a");
		assertThat(name1.equals(name2)).isTrue();
	}
}
