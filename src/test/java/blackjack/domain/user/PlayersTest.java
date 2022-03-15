package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {
	@Test
	@DisplayName("빈 리스트를 받았을 때 에러 발생")
	void check_empty_list() {
		String lines = ",,,,";
		List<String> strings = List.of(lines.split(","));
		assertThatThrownBy(() -> new Players(strings))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(Players.EMPTY_PLAYER_EXCEPTION);
	}

	@Test
	@DisplayName("중복된 플레이어를 받았을 경우 에러 발생")
	void check_duplicate_player() {
		List<String> playerNames = List.of("pobi", "pobi");
		assertThatThrownBy(() -> new Players(playerNames))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(Players.DUPLICATE_PLAYER_EXCEPTION);
	}
}
