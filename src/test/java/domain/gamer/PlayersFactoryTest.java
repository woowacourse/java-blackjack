package domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersFactoryTest {
	@ParameterizedTest
	@ValueSource(strings = {"allen,kyle,bee", "pobi,jason,cu,woni,brown,jun"})
	@DisplayName("Name의 list가 제대로 생성되는지 검사")
	void createTest(String input) {
		List<Name> names = PlayersFactory.newNames(input);
		List<Name> expectedNames = StringUtil.parseByComma(input)
				.stream()
				.map(Name::new)
				.collect(Collectors.toList());

		assertThat(names).isEqualTo(expectedNames);
	}

	@Test
	@DisplayName("입력받은 이름에 맞는 플레이어를 생성하는지 테스트합니다.")
	void createTest() {
		String namesInput = "pobi,jason,brown";
		List<Name> names = PlayersFactory.newNames(namesInput);
		List<String> monies = Arrays.asList("0", "0", "0");

		List<Player> players = PlayersFactory.of(names, monies);

		Player[] expected = new Player[]{
				new Player(new Name("pobi"), Money.ZERO),
				new Player(new Name("jason"), Money.ZERO),
				new Player(new Name("brown"), Money.ZERO)
		};

		assertThat(players).containsExactly(expected);
	}
}