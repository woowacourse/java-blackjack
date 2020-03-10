package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CardTest {
	@Test
	void create() {
		assertThat(Card.of("스페이드", 1)).isInstanceOf(Card.class);
	}
}
