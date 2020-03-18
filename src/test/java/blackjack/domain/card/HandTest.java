package blackjack.domain.card;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
	private static Card aceSpade;
	private static Card twoClub;
	private static Card sixDiamond;
	private static Card tenClub;
	private static Card jackHeart;

	private Hand hand;

	@BeforeAll
	static void beforeAll() {
		aceSpade = Card.of(Symbol.ACE, Type.SPADE);
		twoClub = Card.of(Symbol.TWO, Type.CLUB);
		sixDiamond = Card.of(Symbol.SIX, Type.DIAMOND);
		tenClub = Card.of(Symbol.TEN, Type.CLUB);
		jackHeart = Card.of(Symbol.JACK, Type.HEART);
	}

	@BeforeEach
	void setUp() {
		hand = new Hand();
	}

	@Test
	void Hand_IsNotNull() {
		assertThat(hand).isNotNull();
	}

	@ParameterizedTest
	@MethodSource("add_Card_AddCardToHand")
	void add_Card_AddCardToHand(Card card) {
		hand.add(card);
		assertThat(hand.getHand()).isEqualTo(Collections.singletonList(card));
	}

	static Stream<Card> add_Card_AddCardToHand() {
		return Stream.of(aceSpade, twoClub, sixDiamond, tenClub, jackHeart);
	}

	@ParameterizedTest
	@MethodSource("add_TwoCards_AddTwoCardsToHand")
	void add_TwoCards_AddTwoCardsToHand(List<Card> cards) {
		for (Card card : cards) {
			hand.add(card);
		}
		assertThat(hand.getHand()).isEqualTo(cards);
	}

	static Stream<List<Card>> add_TwoCards_AddTwoCardsToHand() {
		return Stream.of(Arrays.asList(aceSpade, twoClub),
				Arrays.asList(sixDiamond, tenClub),
				Arrays.asList(jackHeart, jackHeart));
	}

	@ParameterizedTest
	@MethodSource("addAll_Cards_AddAllToHand")
	void addAll_Cards_AddAllToHand(List<Card> cards) {
		hand.addAll(cards);
		assertThat(hand.getHand()).isEqualTo(cards);
	}

	static Stream<List<Card>> addAll_Cards_AddAllToHand() {
		return Stream.of(Collections.emptyList(),
				Collections.singletonList(aceSpade),
				Arrays.asList(sixDiamond, tenClub),
				Arrays.asList(aceSpade, sixDiamond, tenClub, twoClub, jackHeart, jackHeart));
	}

	@ParameterizedTest
	@MethodSource("size_HasCards_ReturnCardsSize")
	void size_HasCards_ReturnCardsSize(List<Card> cards) {
		hand.addAll(cards);
		assertThat(cards.size()).isEqualTo(cards.size());
	}

	static Stream<List<Card>> size_HasCards_ReturnCardsSize() {
		return Stream.of(Collections.emptyList(),
				Collections.singletonList(aceSpade),
				Arrays.asList(sixDiamond, tenClub),
				Arrays.asList(aceSpade, sixDiamond, tenClub, twoClub, jackHeart, jackHeart));
	}

	@ParameterizedTest
	@MethodSource("getScore")
	void getScore(List<Card> cards, int score) {
		hand.addAll(cards);
		assertThat(hand.computeScore()).isEqualTo(Score.of(score));
	}

	static Stream<Arguments> getScore() {
		return Stream.of(Arguments.of(Collections.emptyList(), 0),
				Arguments.of(Collections.singletonList(aceSpade), 11),
				Arguments.of(Arrays.asList(aceSpade, tenClub), 21),
				Arguments.of(Arrays.asList(tenClub, jackHeart, aceSpade), 21),
				Arguments.of(Arrays.asList(jackHeart, aceSpade, twoClub), 13));
	}

	@ParameterizedTest
	@MethodSource("get_HasCards_ReturnCards")
	void get_HasCards_ReturnCards(List<Card> cards) {
		hand.addAll(cards);
		assertThat(hand.getHand()).isEqualTo(cards);
	}

	static Stream<List<Card>> get_HasCards_ReturnCards() {
		return Stream.of(Collections.emptyList(),
				Collections.singletonList(aceSpade),
				Arrays.asList(sixDiamond, tenClub),
				Arrays.asList(aceSpade, sixDiamond, tenClub, twoClub, jackHeart, jackHeart));
	}
}