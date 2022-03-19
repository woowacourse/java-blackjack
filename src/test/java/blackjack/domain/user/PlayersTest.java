package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.RealDeck;

public class PlayersTest {
	@Test
	@DisplayName("빈 리스트를 받았을 때 에러 발생")
	void check_empty_list() {
		assertThatThrownBy(() -> new Players(Collections.emptyList()))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(Players.EMPTY_PLAYER_EXCEPTION);
	}

	@Test
	@DisplayName("중복된 플레이어를 받았을 경우 에러 발생")
	void check_duplicate_player() {
		List<String> playerNames = List.of("pobi", "pobi");
		List<Player> players = new ArrayList<>();
		for (String playerName : playerNames) {
			players.add(new Player(playerName, 1000, new RealDeck()));
		}

		assertThatThrownBy(() -> new Players(players))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(Players.DUPLICATE_PLAYER_EXCEPTION);
	}
}
