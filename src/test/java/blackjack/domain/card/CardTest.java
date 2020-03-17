package blackjack.domain.card;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.domain.card.Symbol.*;
import static blackjack.domain.card.Type.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
	private static Card aceClub;
	private static Card twoHeart;
	private static Card threeDiamond;
	private static Card kingSpade;

	@BeforeAll
	static void beforeAll() {
		aceClub = Card.of(ACE, CLUB);
		twoHeart = Card.of(TWO, HEART);
		threeDiamond = Card.of(THREE, DIAMOND);
		kingSpade = Card.of(KING, SPADE);
	}

	@ParameterizedTest
	@MethodSource("of_ValidInput_IsNotNull")
	void of_ValidInput_IsNotNull(Card card) {
		assertThat(card).isNotNull();
	}

	static Stream<Card> of_ValidInput_IsNotNull() {
		return Stream.of(aceClub, twoHeart, threeDiamond, kingSpade);
	}

	@Test
	void isAce_AceClub_IsTrue() {
		assertThat(aceClub.isAce()).isTrue();
	}

	@ParameterizedTest
	@MethodSource("isAce_NotAce_IsFalse")
	void isAce_NotAce_IsFalse(Card card) {
		assertThat(card.isAce()).isFalse();
	}

	static Stream<Card> isAce_NotAce_IsFalse() {
		return Stream.of(twoHeart, threeDiamond, kingSpade);
	}

	@ParameterizedTest
	@MethodSource("getScore_Card_ReturnScore")
	void getScore_Card_ReturnScore(Card card, int score) {
		assertThat(card.getScore()).isEqualTo(Score.of(score));
	}

	static Stream<Arguments> getScore_Card_ReturnScore() {
		return Stream.of(Arguments.of(aceClub, 1),
				Arguments.of(twoHeart, 2),
				Arguments.of(threeDiamond, 3),
				Arguments.of(kingSpade, 10));
	}

	@ParameterizedTest
	@MethodSource("equals_Card_isEqualToCardHavingSameValues")
	void equals_Card_isEqualToCardHavingSameValues(Card card, Card expect) {
		assertThat(card).isEqualTo(expect);
	}

	static Stream<Arguments> equals_Card_isEqualToCardHavingSameValues() {
		return Stream.of(Arguments.of(aceClub, Card.of(ACE, CLUB)),
				Arguments.of(twoHeart, Card.of(TWO, HEART)),
				Arguments.of(threeDiamond, Card.of(THREE, DIAMOND)),
				Arguments.of(kingSpade, Card.of(KING, SPADE)));
	}

	@ParameterizedTest
	@MethodSource("getName_Card_ReturnCardName")
	void getName_Card_ReturnCardName(Card card, String cardName) {
		assertThat(card.getName()).isEqualTo(cardName);
	}

	static Stream<Arguments> getName_Card_ReturnCardName() {
		return Stream.of(Arguments.of(aceClub, "A 클로버"),
				Arguments.of(twoHeart, "2 하트"),
				Arguments.of(threeDiamond, "3 다이아몬드"),
				Arguments.of(kingSpade, "K 스페이드"));
	}
}
