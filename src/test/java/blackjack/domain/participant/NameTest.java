package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameTest {

	@DisplayName("이름이 공백이면 예외를 반환한다")
	@Test
	void exception_empty() {
		assertThatThrownBy(() -> new Name(""))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 이름은 공백일 수 없습니다.");
	}

	@DisplayName("이름에 특수 문자가 들어가면 예외를 반환한다")
	@Test
	void exception_specialCharacter() {
		assertThatThrownBy(() -> new Name("?!"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 이름에 특수문자가 포함될 수 없습니다.");
	}

}
