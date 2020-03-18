package domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserResultTest {
	@DisplayName("Null값이 포함된 인자로 객체 생성시 예외가 발생한다.")
	@Test
	void createWithNullArgumentExceptionTest() {
		assertThatThrownBy(() -> new UserResult(null, 0))
			.isInstanceOf(NullPointerException.class);
	}
}