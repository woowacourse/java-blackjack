package domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersFactoryTest {
	@Test
	@DisplayName("입력받은 이름에 맞는 플레이어를 생성하는지 테스트합니다.")
	void createTest() {
		String namesInput = "pobi,jason,brown";
		List<Name> names = Name.list(namesInput);
		List<Money> monies = Arrays.asList(Money.ZERO, Money.ZERO, Money.ZERO);

		List<Player> players = PlayersFactory.of(names, monies);

		Player[] expected = new Player[]{
				new Player(new Name("pobi"), Money.ZERO),
				new Player(new Name("jason"), Money.ZERO),
				new Player(new Name("brown"), Money.ZERO)
		};

		assertThat(players).containsExactly(expected);
	}
}