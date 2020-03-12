package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayersFactoryTest {
	@Test
	void create() {
		assertThat(PlayersFactory.create("a,b,c,d")).isInstanceOf(Players.class);
	}
}
