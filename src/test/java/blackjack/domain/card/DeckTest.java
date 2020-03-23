package blackjack.domain.card;

import blackjack.domain.card.exceptions.DeckException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.testAssistant.TestAssistant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {

	@DisplayName("of()가 적절한 입력을 받았을 때, 인스턴스를 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("of_ValidInput_IsNotNull")
	void of_ValidInput_IsNotNull(String input) {
		Deck deck = createDeck(input);

		assertThat(deck).isNotNull();
	}

	static Stream<Deck> of_ValidInput_IsNotNull() {
		return Stream.of(createDeck(),
				createDeck("FOUR,SPADE", "THREE,DIAMOND", "TWO,HEART", "ACE,CLUB"),
				createDeck("ACE,SPADE"));
	}

	@DisplayName("draw()가 덱의 맨 윗 카드를 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("draw_Deck_ReturnTopCard")
	void draw_Deck_ReturnTopCard(Deck deck, Card expect) {
		assertThat(deck.draw()).isEqualTo(expect);
	}

	static Stream<Arguments> draw_Deck_ReturnTopCard() {
		return Stream.of(
				Arguments.of(createDeck("FOUR,SPADE", "THREE,DIAMOND", "TWO,HEART", "ACE,CLUB"),
						createCard("ACE,CLUB")),
				Arguments.of(createDeck("FOUR,SPADE", "THREE,DIAMOND", "TWO,HEART"),
						createCard("TWO,HEART"))
		);
	}

	@DisplayName("draw()가 맨 위 카드를 제거하는지 테스트")
	@ParameterizedTest
	@MethodSource("draw_RemoveTopCard")
	void draw_RemoveTopCard(Deck deck, Deck expect) {
		deck.draw();

		assertThat(deck).isEqualTo(expect);
	}

	static Stream<Arguments> draw_RemoveTopCard() {
		return Stream.of(
				Arguments.of(createDeck("FOUR,SPADE", "THREE,DIAMOND", "TWO,HEART", "ACE,CLUB"),
						createDeck("FOUR,SPADE", "THREE,DIAMOND", "TWO,HEART")),
				Arguments.of(createDeck("FOUR,SPADE"),
						createDeck())
		);
	}

	@DisplayName("draw()가 덱이 비었을시 예외를 던지는지 테스트")
	@Test
	void draw_ThereAreNoCard_ThrowDeckException() {
		Deck deck = createDeck();

		assertThatThrownBy(deck::draw).isInstanceOf(DeckException.class);
	}

	@DisplayName("drawTwoCards()가 위에서 두 장 꺼내는지 테스트")
	@ParameterizedTest
	@MethodSource("drawTwoCards_MoreThanTwoCards_ReturnTwoCards")
	void drawTwoCards_MoreThanTwoCards_ReturnTwoCards(Deck deck, List<Card> expect) {
		assertThat(deck.drawTwoCards()).isEqualTo(expect);
	}

	static Stream<Arguments> drawTwoCards_MoreThanTwoCards_ReturnTwoCards() {
		return Stream.of(
				Arguments.of(createDeck("THREE,HEART", "FOUR,DIAMOND"),
						createCards("FOUR,DIAMOND", "THREE,HEART")),
				Arguments.of(createDeck("THREE,HEART", "FOUR,DIAMOND", "FIVE,CLUB"),
						createCards("FIVE,CLUB", "FOUR,DIAMOND"))
		);
	}

	@DisplayName("drawTwoCards()가 위에서 카드를 두 장 제거하는지 테스트")
	@ParameterizedTest
	@MethodSource("drawTwoCards_MoreThanTwoCards_RemoveTopTwoCards")
	void drawTwoCards_MoreThanTwoCards_RemoveTopTwoCards(Deck deck, Deck expect) {
		deck.drawTwoCards();

		assertThat(deck).isEqualTo(expect);
	}

	static Stream<Arguments> drawTwoCards_MoreThanTwoCards_RemoveTopTwoCards() {
		return Stream.of(
				Arguments.of(createDeck("ACE,SPADE", "TWO,CLUB"),
						createDeck()),
				Arguments.of(createDeck("ACE,SPADE", "TWO,CLUB", "THREE,HEART"),
						createDeck("ACE,SPADE"))
		);
	}

	@DisplayName("drawTwoCards()가 덱에 카드가 2장보다 적을 시 예외를 던지는지 테스트")
	@ParameterizedTest
	@MethodSource("drawTwoCards_LessThanTwoCards_ThrowDeckException")
	void drawTwoCards_LessThanTwoCards_ThrowDeckException(Deck deck) {
		assertThatThrownBy(deck::drawTwoCards).isInstanceOf(DeckException.class);
	}

	static Stream<Deck> drawTwoCards_LessThanTwoCards_ThrowDeckException() {
		return Stream.of(createDeck(),
				createDeck("ACE,SPADE")
		);
	}
}