package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import util.StringUtil;

class PlayersFactoryTest {
	@ParameterizedTest
	@ValueSource(strings = {"allen,kyle,bee", "pobi,jason,cu,woni,brown,jun"})
	@DisplayName("Player의 list가 제대로 생성되는지 검사")
	void createTest(String input) {
		List<Player> players = PlayersFactory.create(input);
		List<String> names = StringUtil.parseByComma(input);

		for (String name : names) {
			assertThat(players).contains(new Player(name));
		}
	}

	@Test
	void playersUnmodifiableTest() {
		List<Player> players = PlayersFactory.create("pobi,jason,cu");

		assertThatThrownBy(() -> {
			players.add(new Player("brown"));
		}).isInstanceOf(UnsupportedOperationException.class);
	}
}