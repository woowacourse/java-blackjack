package blakcjack.domain.card;

import blakcjack.domain.money.Money;
import blakcjack.domain.name.Name;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
		Cards expectedCards = new Cards();
		expectedCards.add(Card.of(CardSymbol.SPADE, CardNumber.ACE));
		assertThat(cards.getFirstCard()).isEqualTo(expectedCards);
	}

	@DisplayName("카드들 중에서 처음 2장을 반환해주는지")
	@Test
	void getFirstTwoCards_multipleCards_returnCardsAtFirstAndSecondPosition() {
		Cards expectedCards = new Cards();
		expectedCards.add(Card.of(CardSymbol.SPADE, CardNumber.ACE));
		expectedCards.add(Card.of(CardSymbol.HEART, CardNumber.ACE));
		assertThat(cards.getFirstTwoCards()).isEqualTo(expectedCards);
	}

	private Cards createCustomCards(final Card... cards) {
		Cards customCards = new Cards();
		for (Card card : cards) {
			customCards.add(card);
		}
		return customCards;
	}

	private Deck createCustomDeck(final Card... cards) {
		return new Deck(Arrays.asList(cards));
	}

	private void drawCards(final Deck deck, final Participant... participants) {
		for (Participant participant : participants) {
			participant.drawOneCardFrom(deck);
			participant.drawOneCardFrom(deck);
		}
	}
}