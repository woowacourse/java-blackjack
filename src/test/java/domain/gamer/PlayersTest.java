package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import domain.BlackjackGame;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class PlayersTest {
	@ParameterizedTest
	@NullAndEmptySource
	void 이름_null_이나_빈_값(String[] playersName) {
		assertThatThrownBy(() -> new Players(playersName))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}

	@Test
	void 이름_중복() {
		String[] playersName = {"a", "a", "b"};
		assertThatThrownBy(() -> new BlackjackGame(playersName))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("중복");
	}

	@Test
	void 인원수_초과() {
		String[] playersName = {"a", "b", "c", "d", "e", "gg"};
		assertThatThrownBy(() -> new BlackjackGame(playersName))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("초과");
	}
}
