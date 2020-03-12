package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerIntentionTypeTest {

	@Test
	void of_When_Input_Y_Return_Yes() {
		assertEquals(PlayerIntentionType.YES, PlayerIntentionType.of("y"));
	}
}