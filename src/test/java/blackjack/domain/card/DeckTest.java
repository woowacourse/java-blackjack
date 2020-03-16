package blackjack.domain.card;

import blackjack.domain.card.exceptions.DeckException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {
	private static Card threeHeart;
	private static Card fourDiamond;
	private static Card fiveClub;

	@Test
	void of_SimpleDeck_IsNotNull() {
		assertThat(simpleDeck).isNotNull();
	}

	private static Card sixSpade;

	private Drawable simpleDeck;
	private List<Card> fourCards;

	@BeforeAll
	static void beforeAll() {
		threeHeart = Card.of(Symbol.THREE, Type.HEART);
		fourDiamond = Card.of(Symbol.FOUR, Type.DIAMOND);
		fiveClub = Card.of(Symbol.FIVE, Type.CLUB);
		sixSpade = Card.of(Symbol.SIX, Type.SPADE);
	}

	@BeforeEach
	void setUp() {
		fourCards = new ArrayList<>(Arrays.asList(threeHeart,
				fourDiamond,
				fiveClub,
				sixSpade));

		simpleDeck = Deck.of(fourCards);
	}

	@Test
	void of_FourCards_IsNotNull() {
		assertThat(simpleDeck).isNotNull();
	}

	@Test
	void of_EmptyList_IsNotNull() {
		assertThat(Deck.of(Collections.emptyList())).isNotNull();
	}

	@Test
	void ofDeckFactory_ShuffledDeckFactory_IsSizeFifteenAndNotDuplicated() {
		// when
		Drawable deck = Deck.ofDeckFactory(new ShuffledDeckFactory());

		// then
		Set<Card> cards = new HashSet<>();
		for (int i = 0; i < 52; i++) {
			cards.add(deck.draw());
		}

		assertThat(cards.size()).isEqualTo(52);
	}

	@ParameterizedTest
	@MethodSource("draw_Deck_ReturnTopCard")
	void draw_Deck_ReturnTopCard(List<Card> cards) {
		Drawable deck = Deck.of(cards);
		assertThat(deck.draw()).isEqualTo(cards.get(cards.size() - 1));
	}

	static Stream<List<Card>> draw_Deck_ReturnTopCard() {
		return Stream.of(Collections.singletonList(threeHeart),
				Arrays.asList(threeHeart, fourDiamond),
				Arrays.asList(threeHeart, fourDiamond, fiveClub, sixSpade),
				Arrays.asList(threeHeart, threeHeart, fourDiamond, threeHeart));
	}

	@ParameterizedTest
	@MethodSource("draw_OnceDrawnDeck_ReturnSecondTopCard")
	void draw_OnceDrawnDeck_ReturnSecondTopCard(List<Card> cards) {
		Drawable deck = Deck.of(cards);
		deck.draw();
		assertThat(deck.draw()).isEqualTo(cards.get(cards.size() - 2));
	}

	static Stream<List<Card>> draw_OnceDrawnDeck_ReturnSecondTopCard() {
		return Stream.of(Arrays.asList(threeHeart, fourDiamond),
				Arrays.asList(threeHeart, fourDiamond, fiveClub, sixSpade),
				Arrays.asList(threeHeart, threeHeart, fourDiamond, threeHeart));
	}

	@Test
	void draw_ThereAreNoCard_ThrowDeckException() {
		//given
		Drawable emptyDeck = Deck.of(Collections.emptyList());

		// then
		assertThatThrownBy(() -> emptyDeck.draw())
				.isInstanceOf(DeckException.class);
	}

	@Test
	void equals_SimpleDeck_IsEqualToDeckHavingSameCards() {
		// given
		Drawable expected = Deck.of(fourCards);

		// then
		assertThat(simpleDeck).isEqualTo(expected);
	}

	@Test
	void equals_EmptyDeck_IsEqualToOtherEmptyDeck() {
		// given
		Drawable emptyDeck = Deck.of(Collections.emptyList());
		Drawable otherEmptyDeck = Deck.of(Collections.emptyList());

		// then
		assertThat(emptyDeck).isEqualTo(otherEmptyDeck);
	}

	@Test
	void equals_SimpleDeck_IsNotEqualToDeckHavingDifferentCards() {
		// given
		Drawable notExpected = Deck.of(Collections.singletonList(Card.of(Symbol.THREE, Type.HEART)));

		// then
		assertThat(simpleDeck).isNotEqualTo(notExpected);
	}
}