package blackjack.domain.user;

import static blackjack.domain.exceptionMessages.UserExceptionMessage.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

public class NameTest {
	@ParameterizedTest
	@EmptySource
	@DisplayName("이름이 빈 입력이거나 null인 경우 에러 발생")
	void blank_or_null(String input) {
		assertThatThrownBy(() -> new Name(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(EMPTY_NAME_EXCEPTION.getMessage());
	}
}
