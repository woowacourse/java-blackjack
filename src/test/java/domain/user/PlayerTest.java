package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

	@DisplayName("String으로 Palyer 생성 되는지 확인")
	@Test
	void create_with_name() {
		assertThat(new Player("toney")).isInstanceOf(Player.class);
	}
}
