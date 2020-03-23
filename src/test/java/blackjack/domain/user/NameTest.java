package blackjack.domain.user;

import blackjack.domain.user.exceptions.NameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static blackjack.domain.testAssistant.TestAssistant.createName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class NameTest {

	@DisplayName("Name 객체가 생성되는지 확인")
	@ParameterizedTest
	@ValueSource(strings = {"a", "그니", "두다다다다"})
	void Name_IsNotNull(String name) {
		assertThat(createName(name)).isNotNull();
	}

	@DisplayName("Name 생성자가 공백 이름이 들어왔을때 NameException 을 throw 하는지 확인")
	@ParameterizedTest
	@ValueSource(strings = {"", " ", "   "})
	void Name_Blank_ThrowNameException(String blank) {
		assertThatThrownBy(() -> createName(blank)).isInstanceOf(NameException.class);
	}

	@DisplayName("Name 생성자가 Null 값이 들어왔을때 NameException 을 throw 하는지 확인")
	@Test
	void Name_Null_ThrowNameException() {
		assertThatThrownBy(() -> createName(null)).isInstanceOf(NameException.class);
	}

	@DisplayName("같은 값을 가진 Name 객체를 같은 값으로 취급하는지 확인")
	@ParameterizedTest
	@ValueSource(strings = {"a", "그니", "두두다다"})
	void equals_HasSameValues_IsTrue(String input) {
		assertThat(createName(input)).isEqualTo(new Name(input));
	}

}