package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import domain.card.Card;
import domain.card.Hands;
import domain.card.Symbol;
import domain.card.Type;

/**
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class GamerTest {
	private Gamer gamer;

	@BeforeEach
	void setUp() {
		gamer = new Player("a");
	}

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

	private static Stream<Arguments> generateDrawInput() {
		return Stream.of(
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.TWO), new Card(Type.DIAMOND, Symbol.TWO)), true),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.KING)), false),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.NINE),
				new Card(Type.DIAMOND, Symbol.TWO)), true));
	}

	private static Stream<Arguments> generateScoreInput() {
		return Stream.of(
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.TWO), new Card(Type.DIAMOND, Symbol.TWO)), 4),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.KING)), 21),
			Arguments.of(Arrays.asList(new Card(Type.HEART, Symbol.ACE), new Card(Type.DIAMOND, Symbol.NINE),
				new Card(Type.DIAMOND, Symbol.TWO)), 12));
	}

	@ParameterizedTest
	@MethodSource("generateBustInput")
	void isBust_True_ScoreOver21(List<Card> cards, boolean expected) {
		for (Card card : cards) {
			gamer.hit(card);
		}
		assertThat(gamer.isBust()).isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource("generateBlackjackInput")
	void isBlackjack_True_ScoreIs21_And_HasTwoCards(List<Card> cards, boolean expected) {
		for (Card card : cards) {
			gamer.hit(card);
		}
		assertThat(gamer.isBlackjack()).isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource("generateDrawInput")
	void canHit_True_ScoreLessThan21(List<Card> cards, boolean expected) {
		for (Card card : cards) {
			gamer.hit(card);
		}
		assertThat(gamer.canHit()).isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource("generateScoreInput")
	void scoreHands_ExpectedScore_hasInputCards(List<Card> cards, int expected) {
		for (Card card : cards) {
			gamer.hit(card);
		}
		assertThat(gamer.scoreHands()).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void Should_ThrownException_When_NameIsNull_Or_Empty(String name) {
		assertThatThrownBy(() -> new Player(name))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}

	@Test
	void testGetter() {
		assertThat(gamer.getName()).isInstanceOf(String.class).isNotBlank();
		assertThat(gamer.getHands()).isInstanceOf(Hands.class).isNotNull();
	}
}
