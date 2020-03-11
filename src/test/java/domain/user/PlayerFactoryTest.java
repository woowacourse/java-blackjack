package domain.user;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerFactoryTest {

	@DisplayName("생성 테스트")
	@Test
	void createTest() {
		List<Player> players = Arrays.asList(new Player("동글"),
			new Player("둔덩"));
		assertThat(PlayerFactory.create("동글,둔덩")).isEqualTo(players);
	}
}
