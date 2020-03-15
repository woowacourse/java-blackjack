package domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
public class HandsTest {
	@Test
	void 총_점수_계산() {
		Hands hands = new Hands();
		hands.add(new Card(Type.HEART, Symbol.ACE));
		hands.add(new Card(Type.DIAMOND, Symbol.KING));
		assertThat(hands.calculateTotalScore()).isEqualTo(21);
	}

	@Test
	void ACE_유무() {
		Hands hands1 = new Hands();
		hands1.add(new Card(Type.HEART, Symbol.ACE));
		hands1.add(new Card(Type.DIAMOND, Symbol.KING));
		assertThat(hands1.hasAce()).isEqualTo(true);
	}

	private static Stream<Arguments> generateInput() {
		return Stream.of(
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.TWO), new Card(Type.DIAMOND, Symbol.TWO)), false),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.KING)), true),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.NINE),
				new Card(Type.DIAMOND, Symbol.TWO)), false));
	}

	@ParameterizedTest
	@MethodSource("generateInput")
	void isBlackjack_True_ScoreIs21AndHasTwoCards(List<Card> cards, boolean expected) {
		Hands hands = new Hands();

		for (Card card : cards) {
			hands.add(card);
		}

		assertThat(hands.isBlackjack()).isEqualTo(expected);
	}

	@Test
	void isBust_True_ScoreMoreThan21() {
		Hands hands = new Hands();

		hands.add(new Card(Type.DIAMOND, Symbol.KING));
		hands.add(new Card(Type.SPADE, Symbol.KING));
		hands.add(new Card(Type.DIAMOND, Symbol.TWO));

		assertThat(hands.isBust()).isTrue();
	}
}
