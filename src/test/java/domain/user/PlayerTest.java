package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.user.Player.INPUT_EMPTY_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {

	@DisplayName("String으로 Palyer 생성 되는지 확인")
	@Test
	void create_with_name() {
		assertThat(new Player("toney")).isInstanceOf(Player.class);
	}

	@DisplayName("이름이 빈 문자열이면 예외처리")
	@Test
	void create_with_empty_name() {
		assertThatIllegalArgumentException().isThrownBy(() ->
				new Player(""))
				.withMessage(INPUT_EMPTY_NAME);
	}
}
