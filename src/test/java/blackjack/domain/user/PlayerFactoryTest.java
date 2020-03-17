package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class PlayerFactoryTest {
	@Test
	void create_PlayerNames_GeneratePlayerList() {
		List<Player> players = Arrays.asList(
			new Player("pobi"),
			new Player("sony"),
			new Player("stitch"));

		assertThat(PlayerFactory.create("pobi, sony, stitch")).isEqualTo(players);
	}
}
