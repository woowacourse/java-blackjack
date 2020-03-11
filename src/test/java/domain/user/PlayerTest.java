package domain.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}
