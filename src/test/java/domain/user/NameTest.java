package domain.user;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class NameTest {
	@DisplayName("이름이 빈문자, 혹은 null 인 경우, 예외 발생 테스트")
	@ParameterizedTest
	@NullAndEmptySource
	void constructNameWithNullAndEmptyTest(String name) {
		assertThatThrownBy(() -> new Name(name))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
