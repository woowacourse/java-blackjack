package blackjack.domain.blackjack;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import blackjack.domain.exceptions.InvalidDrawOpinionException;

class DrawOpinionTest {
	@Test
	void of_InputDrawOpinion_ReturnInstance() {
		Assertions.assertThat(DrawOpinion.of("y")).isEqualTo(DrawOpinion.YES);
	}

	@Test
	void of_InvalidInputDrawOpinion_InvalidDrawOpinionExceptionThrown() {
		assertThatThrownBy(() -> DrawOpinion.of("k"))
			.isInstanceOf(InvalidDrawOpinionException.class)
			.hasMessage(InvalidDrawOpinionException.INVALID);
	}

	@Test
	void isYes_PlayerDrawOpinionIsYes_ReturnTrue() {
		DrawOpinion playerDrawOpinion = DrawOpinion.of("y");
		assertThat(playerDrawOpinion.isYes()).isTrue();
	}
}
