package blakcjack.domain.participant;

import blakcjack.domain.card.*;
import blakcjack.domain.name.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
	private Player player;
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		player = new Player(new Name("pobi"));
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
		final Dealer dealer = new Dealer();
		final Deck customDeck = createCustomDeck(Card.of(CardSymbol.CLUB, CardNumber.ACE));
		dealer.drawOneCardFrom(customDeck);

		Cards cards = new Cards();
		cards.add(Card.of(CardSymbol.CLUB, CardNumber.ACE));
		assertThat(dealer.getCards()).isEqualTo(cards);
	}

	@DisplayName("딜러 점수가 17점 미만이면 true 17점 이상이면 false")
	@Test
	void isScoreLowerThanSevenTeen() {
		final Dealer dealer = new Dealer();
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

	private Deck createCustomDeck(final Card... cards) {
		return new Deck(Arrays.asList(cards));
	}
}
