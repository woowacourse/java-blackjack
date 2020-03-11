package domain.user;

import org.junit.jupiter.api.Test;

import static domain.user.Player.INPUT_EMPTY_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class PlayerTest {

	@Test
	void create_with_name() {
		assertThat(new Player("toney")).isInstanceOf(Player.class);
	}

	@Test
	void create_with_empty_name() {
		assertThatIllegalArgumentException().isThrownBy(() ->
				new Player(""))
				.withMessage(INPUT_EMPTY_NAME);
	}
}
