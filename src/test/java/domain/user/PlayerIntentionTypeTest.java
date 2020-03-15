package domain.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerIntentionTypeTest {

	@Test
	void of_When_Input_Y_Return_Want_Draw() {
		assertEquals(PlayerIntentionType.WANT_DRAW, PlayerIntentionType.of("y"));
	}

	@Test
	void isWantDraw_Return_True_When_Input_Y() {
		assertTrue(PlayerIntentionType.of("y").isWantDraw());
	}
}