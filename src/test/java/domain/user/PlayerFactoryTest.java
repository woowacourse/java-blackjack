package domain.user;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerFactoryTest {

	@DisplayName("생성 테스트")
	@Test
	void createTest() {
		List<Player> players = PlayerFactory.create("동글,  둔덩", name -> 3_000);
		Player player = Player.fromNameAndMoney("동글", 3000);
		Player secondPlayer = Player.fromNameAndMoney("둔덩", 3000);
		assertThat(players).containsExactlyInAnyOrder(player, secondPlayer);
	}
}
