package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *
 *    @author AnHyungJu
 */
@SuppressWarnings("NonAsciiCharacters")
public class PlayerTest {
	@Test
	void 생성() {
		Player player = new Player("a");

		assertThat(player).isNotNull();
		assertThat(player.getName()).isEqualTo("a");
		assertThat(player.getHands()).isNotNull();
	}
}
