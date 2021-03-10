package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BurstTest {
	private Burst state;

	@BeforeEach
	void setUp() {
		state = new Burst();
	}

	@Test
	void checkFinished() {
		assertTrue(state.isFinished());
	}

	@Test
	void exception() {
		assertThatThrownBy(() -> state.keepContinue(true)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("옳지 않은 곳에서 호출");
	}
}