package blakcjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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

	@DisplayName("카드들 중에서 첫번째 인덱스의 카드를 제대로 뽑아 내는지")
	@Test
	void getFirstCard_multipleCards_returnCardAtFirstPosition() {
		assertThat(cards.getFirstCard()).isEqualTo(Card.of(CardSymbol.SPADE, CardNumber.ACE));
	}

	@DisplayName("카드가 2장만 있을 때는 true 그 외에는 false 반환 하는지")
	@Test
	void haveOnlyTwoCards_twoCards_returnTrue() {
		Cards twoCards = createCustomCards(
				Card.of(CardSymbol.CLUB, CardNumber.ACE),
				Card.of(CardSymbol.CLUB, CardNumber.FIVE));

		Cards oneCards = createCustomCards(
				Card.of(CardSymbol.CLUB, CardNumber.ACE));

		Cards threeCards = createCustomCards(
				Card.of(CardSymbol.CLUB, CardNumber.ACE),
				Card.of(CardSymbol.CLUB, CardNumber.FIVE),
				Card.of(CardSymbol.CLUB, CardNumber.SIX));

		assertThat(twoCards.haveOnlyTwoCards()).isTrue();
		assertThat(oneCards.haveOnlyTwoCards()).isFalse();
		assertThat(threeCards.haveOnlyTwoCards()).isFalse();
	}

	@DisplayName("카드의 총 점수가 21 일 때 true를 반환 해주는지")
	@Test
	void haveBlackjackScore() {
		Cards blackjackCards = createCustomCards(
				Card.of(CardSymbol.CLUB, CardNumber.KING),
				Card.of(CardSymbol.CLUB, CardNumber.ACE));
		Cards nonBlackJackButScoring21Cards = createCustomCards(
				Card.of(CardSymbol.CLUB, CardNumber.KING),
				Card.of(CardSymbol.CLUB, CardNumber.JACK),
				Card.of(CardSymbol.CLUB, CardNumber.ACE));
		assertThat(blackjackCards.haveBlackjackScore()).isTrue();
		assertThat(nonBlackJackButScoring21Cards.haveBlackjackScore()).isTrue();
	}

	private Cards createCustomCards(final Card... cards) {
		Cards customCards = new Cards();
		for (Card card : cards) {
			customCards.add(card);
		}
		return customCards;
	}
}