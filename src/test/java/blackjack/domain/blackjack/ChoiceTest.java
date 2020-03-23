package blackjack.domain.blackjack;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import blackjack.domain.exceptions.InvalidChoiceException;

class ChoiceTest {
	@Test
	void of_InputDrawOpinion_ReturnInstance() {
		Assertions.assertThat(Choice.of("y")).isEqualTo(Choice.HIT);
		Assertions.assertThat(Choice.of("n")).isEqualTo(Choice.STAND);
	}

	@Test
	void of_InvalidInputDrawOpinion_InvalidDrawOpinionExceptionThrown() {
		assertThatThrownBy(() -> Choice.of("k"))
			.isInstanceOf(InvalidChoiceException.class)
			.hasMessage(InvalidChoiceException.INVALID);
	}

	@ParameterizedTest
	@NullSource
	void validate_NullChoiceInput_InvalidDrawOpinionExceptionThrown(String value) {
		assertThatThrownBy(() -> Choice.of(value))
			.isInstanceOf(InvalidChoiceException.class)
			.hasMessage(InvalidChoiceException.NULL);
	}

	@Test
	void isHit_PlayerDrawOpinionIsHit_ReturnTrue() {
		Choice value = Choice.of("y");

		assertThat(value.isHit()).isTrue();
	}
}
