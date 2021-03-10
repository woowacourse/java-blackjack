package blakcjack.domain.participant;

import blakcjack.domain.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		dealer = new Dealer();
	}

	@DisplayName("딜러 객체 생성 제대로 하는지")
	@Test
	void create() {
		final Dealer dealer = new Dealer();
		assertThat(dealer).isEqualTo(new Dealer());
	}

	@DisplayName("카드 제대로 뽑는지")
	@Test
	void drawCard_deck_drawOneCardFromDeck() {
		final Deck customDeck = createCustomDeck(Card.of(CardSymbol.CLUB, CardNumber.ACE));
		dealer.drawOneCardFrom(customDeck);

		Cards cards = new Cards();
		cards.add(Card.of(CardSymbol.CLUB, CardNumber.ACE));
		assertThat(dealer.getCards()).isEqualTo(cards);
	}

	@DisplayName("딜러 점수가 17점 미만이면 true 17점 이상이면 false")
	@Test
	void isScoreLowerThanMaximumDrawCriterion() {
		final Deck customDeck = createCustomDeck(
				Card.of(CardSymbol.CLUB, CardNumber.ACE),
				Card.of(CardSymbol.CLUB, CardNumber.SIX),
				Card.of(CardSymbol.CLUB, CardNumber.TEN));
		dealer.drawOneCardFrom(customDeck);
		dealer.drawOneCardFrom(customDeck);
		assertThat(dealer.isScoreLowerThanMaximumDrawCriterion()).isTrue();

		dealer.drawOneCardFrom(customDeck);
		assertThat(dealer.isScoreLowerThanMaximumDrawCriterion()).isFalse();
	}

	@DisplayName("초기 패를 보여줄 때 딜러는 첫 번째 카드만 공개하는지")
	@Test
	void getInitialHand() {
		Deck customDeck = createCustomDeck(
				Card.of(CardSymbol.CLUB, CardNumber.ACE),
				Card.of(CardSymbol.CLUB, CardNumber.TWO)
		);
		dealer.drawOneCardFrom(customDeck);
		dealer.drawOneCardFrom(customDeck);

		assertThat(dealer.getInitialHand()).isEqualTo(createCustomCards(
				Card.of(CardSymbol.CLUB, CardNumber.TWO)));
	}

	private Deck createCustomDeck(final Card... cards) {
		return new Deck(Arrays.asList(cards));
	}

	private Cards createCustomCards(final Card... cards) {
		Cards customCards = new Cards();
		for (Card card : cards) {
			customCards.add(card);
		}
		return customCards;
	}
}
