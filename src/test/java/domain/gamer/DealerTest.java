package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

/**
 *   class description
 *
 *   @author ParkDooWon, AnHyungJu
 */
@SuppressWarnings("NonAsciiCharacters")
public class DealerTest {
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
		Dealer dealer = new Dealer();
		for (Card card : cards) {
			dealer.add(card);
		}
		assertThat(dealer.isBust()).isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource("generateBlackjackInput")
	void 블랙잭(List<Card> cards, boolean expected) {
		Dealer dealer = new Dealer();
		for (Card card : cards) {
			dealer.add(card);
		}
		assertThat(dealer.isBlackjack()).isEqualTo(expected);
	}
}
