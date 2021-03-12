package blakcjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {
	private final Cards cards = new Cards();

	@BeforeEach
	void setUp() {
		cards.add(Card.of(CardSymbol.SPADE, CardNumber.ACE));
		cards.add(Card.of(CardSymbol.HEART, CardNumber.ACE));
		cards.add(Card.of(CardSymbol.DIAMOND, CardNumber.ACE));
		cards.add(Card.of(CardSymbol.CLUB, CardNumber.ACE));
		cards.add(Card.of(CardSymbol.CLUB, CardNumber.FIVE));
	}

	@DisplayName("스코어를 제대로 계산 해주는지")
	@Test
	void calculateScore() {
		assertThat(cards.calculateScore()).isEqualTo(19);
	}

	@DisplayName("카드들 중에서 처음 1장을 반환해주는지")
	@Test
	void getFirstCard_multipleCards_returnCardAtFirstPosition() {
		final Cards expectedCards = new Cards();
		expectedCards.add(Card.of(CardSymbol.SPADE, CardNumber.ACE));
		assertThat(cards.getFirstCard()).isEqualTo(expectedCards);
	}

	@DisplayName("카드들 중에서 처음 2장을 반환해주는지")
	@Test
	void getFirstTwoCards_multipleCards_returnCardsAtFirstAndSecondPosition() {
		final Cards expectedCards = new Cards();
		expectedCards.add(Card.of(CardSymbol.SPADE, CardNumber.ACE));
		expectedCards.add(Card.of(CardSymbol.HEART, CardNumber.ACE));
		assertThat(cards.getFirstTwoCards()).isEqualTo(expectedCards);
	}

	@DisplayName("블랙잭을 제대로 판단 해주는지")
	@Test
	void isBlackJack() {
		final Cards blackjackCards = new Cards();
		blackjackCards.add(Card.of(CardSymbol.SPADE, CardNumber.ACE));
		blackjackCards.add(Card.of(CardSymbol.HEART, CardNumber.JACK));

		final Cards nonBlackjackCards = new Cards();
		nonBlackjackCards.add(Card.of(CardSymbol.HEART, CardNumber.KING));
		nonBlackjackCards.add(Card.of(CardSymbol.HEART, CardNumber.FIVE));
		nonBlackjackCards.add(Card.of(CardSymbol.SPADE, CardNumber.SIX));

		assertThat(blackjackCards.isBlackjack()).isTrue();
		assertThat(nonBlackjackCards.isBlackjack()).isFalse();
	}
}