package domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.StringUtil;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersFactoryTest {
	@Test
	@DisplayName("입력받은 이름에 맞는 플레이어를 생성하는지 테스트합니다.")
	void createTest() {
		List<String> input = StringUtil.parseByComma("pobi,jason,brown");
		List<Name> names = Name.list("pobi,jason,brown");
		List<Money> monies = Arrays.asList(Money.ZERO, Money.ZERO, Money.ZERO);
		List<Player> players = PlayersFactory.of(names, monies);

		for (Player player : players) {
			assertThat(input).contains(player.getName());
		}
	}
}