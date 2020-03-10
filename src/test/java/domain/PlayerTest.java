package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {
	@Test
	void create() {
		assertThat(new Player("이름")).isInstanceOf(Player.class);
	}

	@Test
	void create_이름이_공백인_경우() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Player(""));
	}
}
