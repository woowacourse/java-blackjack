package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

/**
 *   class description
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class NameTest {
	@ParameterizedTest
	@NullAndEmptySource
	void Should_ThrownException_When_NameIsNull_OrEmpty(String playersName) {
		assertThatThrownBy(() -> new Name(playersName))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}
}
