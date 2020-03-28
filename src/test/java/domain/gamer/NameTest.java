package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

/**
 *   class description
 *
 *   @author ParkDooWon, AnHyungJu  
 */
@SuppressWarnings("NonAsciiCharacters")
public class NameTest {
	@ParameterizedTest
	@NullAndEmptySource
	void 이름_null_이나_빈_값(String name) {
		assertThatThrownBy(() -> new Name(name))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}
}
