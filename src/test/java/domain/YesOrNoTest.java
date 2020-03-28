package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import domain.exception.InvalidChoiceException;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
@SuppressWarnings("NonAsciiCharacters")
public class YesOrNoTest {
	@ParameterizedTest
	@CsvSource({"Y, YES", "N, NO"})
	void YesOrNo_객체_반환(String choice, YesOrNo yesOrNo) {
		assertThat(YesOrNo.getChoice(choice)).isEqualTo(yesOrNo);
	}

	@ParameterizedTest
	@CsvSource({"YES, true", "NO, false"})
	void Yes_인지_No_인지_반환(YesOrNo yesOrNo, boolean expected) {
		assertThat(yesOrNo.isYes()).isEqualTo(expected);
	}

	@ParameterizedTest
	@ValueSource(strings = {"a", "answer", "yes", "no"})
	void validate_validateInputChoice_ExceptionThrown(String inputChoice) {
		assertThatThrownBy(() -> YesOrNo.getChoice(inputChoice))
			.isInstanceOf(InvalidChoiceException.class);
	}
}
