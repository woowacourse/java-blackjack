package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {
	@Test
	void create() {
		assertThat(new Player("이름")).isInstanceOf(Player.class);
	}
}
