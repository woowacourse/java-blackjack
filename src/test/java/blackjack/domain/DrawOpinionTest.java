package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DrawOpinionTest {
	@Test
	void of_InputDrawOpinion_ReturnInstance() {
		assertThat(DrawOpinion.of("y")).isEqualTo(DrawOpinion.YES);
	}

	@Test
	void of_InvalidInputDrawOpinion_InvalidDrawOpinionExceptionThrown() {
		assertThatThrownBy(() -> DrawOpinion.of("k"))
			.isInstanceOf(InvalidDrawOpinionException.class)
			.hasMessage(InvalidDrawOpinionException.INVALID);
	}
}
