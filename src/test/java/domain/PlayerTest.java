package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
public class PlayerTest {
	private static Stream<Arguments> generateInput() {
		return Stream.of(
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.TWO), new Card(Type.DIAMOND, Symbol.TWO)), false),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.KING)), false),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.NINE),
				new Card(Type.DIAMOND, Symbol.TWO)), true));
	}

	@ParameterizedTest
	@MethodSource("generateInput")
	void 버스트(List<Card> cards, boolean expected) {
		Player player = new Player("a");
		for (Card card : cards) {
			player.add(card);
		}
		assertThat(player.isBust()).isEqualTo(expected);
	}

}
