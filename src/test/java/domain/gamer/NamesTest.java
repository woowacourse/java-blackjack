package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
@SuppressWarnings("NonAsciiCharacters")
public class NamesTest {
	@Test
	void 이름_중복() {
		String[] inputNames = new String[] {"a", "a", "b"};

		assertThatThrownBy(() -> new Names(inputNames))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("중복");
	}

	@Test
	void 인원수_초과() {
		String[] inputNames = new String[] {"a", "b", "c", "d", "e", "f"};

		assertThatThrownBy(() -> new Names(inputNames))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("초과");
	}
}
