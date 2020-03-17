package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import util.StringUtil;

class PlayersFactoryTest {
	@Test
	@DisplayName("입력받은 이름에 맞는 플레이어를 생성하는지 테스트합니다.")
	void createTest() {
		List<String> input = StringUtil.parseByComma("pobi,jason,brown");
		List<Name> names = NameFactory.create("pobi,jason,brown");
		List<Player> players = PlayersFactory.from(names);

		for (Player player : players) {
			assertThat(input).contains(player.getName());
		}
	}
}