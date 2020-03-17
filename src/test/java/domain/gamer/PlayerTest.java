package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

/**
 *
 *    @author AnHyungJu
 */
public class PlayerTest {
	@Test
	void testConstructor() {
		Player player = new Player("a");

		assertThat(player).isNotNull();
		assertThat(player.getName()).isEqualTo("a");
		assertThat(player.getHands()).isNotNull();
	}

	private static Stream<Arguments> generateDrawInput() {
		return Stream.of(
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.TWO), new Card(Type.DIAMOND, Symbol.TWO)), true),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.KING)), false),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.NINE),
				new Card(Type.DIAMOND, Symbol.TWO)), true));
	}

	@ParameterizedTest
	@MethodSource("generateDrawInput")
	void canHit_True_ScoreLessThan21(List<Card> cards, boolean expected) {
		Player player = new Player("a");
		for (Card card : cards) {
			player.hit(card);
		}
		assertThat(player.canHit()).isEqualTo(expected);
	}
}
