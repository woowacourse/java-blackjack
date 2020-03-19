package blackjack.domain.blackjack;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import blackjack.domain.exceptions.InvalidChoiceException;

class ChoiceTest {
	@Test
	void of_InputDrawOpinion_ReturnInstance() {
		Assertions.assertThat(Choice.of("y")).isEqualTo(Choice.HIT);
	}

	@Test
	void of_InvalidInputDrawOpinion_InvalidDrawOpinionExceptionThrown() {
		assertThatThrownBy(() -> Choice.of("k"))
			.isInstanceOf(InvalidChoiceException.class)
			.hasMessage(InvalidChoiceException.INVALID);
	}

	@Test
	void isHit_PlayerDrawOpinionIsHit_ReturnTrue() {
		Choice playerChoice = Choice.of("y");
		assertThat(playerChoice.isHit()).isTrue();
	}
}
