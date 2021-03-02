package blakcjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
	@DisplayName("플레이어 객체 생성 성공")
	@Test
	void create() {
		final Player player = new Player("pobi");
		assertThat(player).isEqualTo(new Player("pobi"));
	}
}
