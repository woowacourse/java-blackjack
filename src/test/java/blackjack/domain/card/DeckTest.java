package blackjack.domain.card;

import blackjack.domain.card.exceptions.DeckException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

	@DisplayName("of()가 인스턴스를 반환하는지 테스트")
	@Test
	void of_SimpleDeck_IsNotNull() {
		assertThat(simpleDeck).isNotNull();
	}

	@DisplayName("of()가 빈 리스트를 받았을때 인스턴스를 반환하는지 테스트")
	@Test
	void of_EmptyList_IsNotNull() {
		assertThat(Deck.of(Collections.emptyList())).isNotNull();
	}

	@DisplayName("ofDeckFactory()가 중복되지 않고 적절한 크기인지 테스트")
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

	@DisplayName("draw()가 덱의 맨 윗 카드를 반환하는지 테스트")
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

	@DisplayName("draw()를 두 번째 호출할 시 맨 위에서 두 번째 카드가 반환되는지 테스트")
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

	@DisplayName("draw()가 덱이 비었을시 예외를 던지는지 테스트")
	@Test
	void draw_ThereAreNoCard_ThrowDeckException() {
		//given
		Drawable emptyDeck = Deck.of(Collections.emptyList());

		// then
		assertThatThrownBy(() -> emptyDeck.draw())
				.isInstanceOf(DeckException.class);
	}

	@DisplayName("drawTwoCards()가 위에서 두 장 꺼내는지 테스트")
	@ParameterizedTest
	@MethodSource("drawTwoCards_MoreThanTwoCards_ReturnTwoCards")
	void drawTwoCards_MoreThanTwoCards_ReturnTwoCards(List<Card> cards) {
		Drawable deck = Deck.of(cards);

		assertThat(deck.drawTwoCards()).containsExactly(cards.get(cards.size() - 1), cards.get(cards.size() - 2));
	}

	static Stream<List<Card>> drawTwoCards_MoreThanTwoCards_ReturnTwoCards() {
		return Stream.of(Arrays.asList(threeHeart, fourDiamond),
				Arrays.asList(threeHeart, fourDiamond, fiveClub, sixSpade),
				Arrays.asList(threeHeart, threeHeart, fourDiamond, threeHeart));
	}

	@DisplayName("drawTwoCards()가 덱에 카드가 2장보다 적을 시 예외를 던지는지 테스트")
	@ParameterizedTest
	@MethodSource("drawTwoCards_LessThanTwoCards_ThrowDeckException")
	void drawTwoCards_LessThanTwoCards_ThrowDeckException(List<Card> cards) {
		Drawable deck = Deck.of(cards);

		assertThatThrownBy(() -> deck.drawTwoCards())
				.isInstanceOf(DeckException.class);
	}

	static Stream<List<Card>> drawTwoCards_LessThanTwoCards_ThrowDeckException() {
		return Stream.of(Collections.emptyList(),
				Collections.singletonList(threeHeart),
				Collections.singletonList(fourDiamond));
	}
}