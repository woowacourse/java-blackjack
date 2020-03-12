package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

/**
 *
 *    @author AnHyungJu, ParkDooWon
 */
@SuppressWarnings("NonAsciiCharacters")
public class GamerTest {
	private static Stream<Arguments> generateBustInput() {
		return Stream.of(
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.TWO), new Card(Type.DIAMOND, Symbol.TWO)), false),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.KING)), false),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.THREE), new Card(Type.DIAMOND, Symbol.NINE),
				new Card(Type.DIAMOND, Symbol.KING)), true));
	}

	private static Stream<Arguments> generateBlackjackInput() {
		return Stream.of(
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.TWO), new Card(Type.DIAMOND, Symbol.TWO)), false),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.KING)), true),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.NINE),
				new Card(Type.DIAMOND, Symbol.TWO)), false));
	}

	@ParameterizedTest
	@MethodSource("generateBustInput")
	void 버스트(List<Card> cards, boolean expected) {
		Gamer gamer = new Gamer("a");
		for (Card card : cards) {
			gamer.add(card);
		}
		assertThat(gamer.isBust()).isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource("generateBlackjackInput")
	void 블랙잭(List<Card> cards, boolean expected) {
		Gamer gamer = new Gamer("a");
		for (Card card : cards) {
			gamer.add(card);
		}
		assertThat(gamer.isBlackjack()).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void 이름_null이나_빈_값(String name) {
		assertThatThrownBy(() -> new Player(name))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}
}
