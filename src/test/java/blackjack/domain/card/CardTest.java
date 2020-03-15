package blackjack.domain.card;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
	private static Card aceClub;
	private static Card twoHeart;
	private static Card threeDiamond;
	private static Card kingSpade;

	@BeforeAll
	static void beforeAll() {
		aceClub = Card.of(Symbol.ACE, Type.CLUB);
		twoHeart = Card.of(Symbol.TWO, Type.HEART);
		threeDiamond = Card.of(Symbol.THREE, Type.DIAMOND);
		kingSpade = Card.of(Symbol.KING, Type.SPADE);
	}

	@ParameterizedTest
	@MethodSource("Card_IsNotNull")
	void Card_IsNotNull(Card card) {
		assertThat(card).isNotNull();
	}

	static Stream<Card> Card_IsNotNull() {
		return Stream.of(aceClub, twoHeart, threeDiamond, kingSpade);
	}

	@Test
	void isAce_IsTrue() {
		assertThat(aceClub.isAce()).isTrue();
	}

	@ParameterizedTest
	@MethodSource("isAce_IsFalse")
	void isAce_IsFalse(Card card) {
		assertThat(card.isAce()).isFalse();
	}

	static Stream<Card> isAce_IsFalse() {
		return Stream.of(twoHeart, threeDiamond, kingSpade);
	}

	@ParameterizedTest
	@MethodSource("getScore")
	void getScore(Card card, int expect) {
		assertThat(card.getScore()).isEqualTo(Score.of(expect));
	}

	static Stream<Arguments> getScore() {
		return Stream.of(Arguments.of(aceClub, 1),
				Arguments.of(twoHeart, 2),
				Arguments.of(threeDiamond, 3),
				Arguments.of(kingSpade, 10));
	}

	@ParameterizedTest
	@MethodSource("equals")
	void equals(Card card, Card expect) {
		assertThat(card).isEqualTo(expect);
	}

	static Stream<Arguments> equals() {
		return Stream.of(Arguments.of(aceClub, Card.of(Symbol.ACE, Type.CLUB)),
				Arguments.of(twoHeart, Card.of(Symbol.TWO, Type.HEART)),
				Arguments.of(threeDiamond, Card.of(Symbol.THREE, Type.DIAMOND)),
				Arguments.of(kingSpade, Card.of(Symbol.KING, Type.SPADE)));
	}

	@ParameterizedTest
	@MethodSource("getName")
	void getName(Card card, String expect) {
		assertThat(card.getName()).isEqualTo(expect);
	}

	static Stream<Arguments> getName() {
		return Stream.of(Arguments.of(aceClub, "A 클로버"),
				Arguments.of(twoHeart, "2 하트"),
				Arguments.of(threeDiamond, "3 다이아몬드"),
				Arguments.of(kingSpade, "K 스페이드"));
	}
}
