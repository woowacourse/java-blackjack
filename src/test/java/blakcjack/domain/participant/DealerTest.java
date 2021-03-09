package blakcjack.domain.participant;

import blakcjack.domain.card.*;
import blakcjack.domain.name.Name;
import blakcjack.domain.outcome.Outcome;
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

	@DisplayName("플레이어 버스트시 플레이어 패")
	@Test
	void judgeOutcome_playerBust_returnLose() {
		final Deck customDeck = createCustomDeck(
				Card.of(CardSymbol.HEART, CardNumber.QUEEN),
				Card.of(CardSymbol.HEART, CardNumber.JACK),
				Card.of(CardSymbol.HEART, CardNumber.TWO),

				Card.of(CardSymbol.SPADE, CardNumber.QUEEN),
				Card.of(CardSymbol.SPADE, CardNumber.JACK),
				Card.of(CardSymbol.SPADE, CardNumber.TWO));

		player.drawOneCardFrom(customDeck);
		player.drawOneCardFrom(customDeck);
		player.drawOneCardFrom(customDeck);

		dealer.drawOneCardFrom(customDeck);
		dealer.drawOneCardFrom(customDeck);
		dealer.drawOneCardFrom(customDeck);

		Outcome outcome = dealer.judgeOutcomeOf(player);
		assertThat(outcome).isEqualTo(Outcome.LOSE);
	}

	@DisplayName("딜러만 버스트시 플레이어 승")
	@Test
	void judgeOutcome_onlyDealerBust_returnWin() {
		final Deck customDeck = createCustomDeck(
				Card.of(CardSymbol.HEART, CardNumber.QUEEN),
				Card.of(CardSymbol.HEART, CardNumber.JACK),
				Card.of(CardSymbol.HEART, CardNumber.TWO),
				Card.of(CardSymbol.SPADE, CardNumber.QUEEN),
				Card.of(CardSymbol.SPADE, CardNumber.JACK),
				Card.of(CardSymbol.SPADE, CardNumber.ACE)
		);
		player.drawOneCardFrom(customDeck);
		player.drawOneCardFrom(customDeck);
		player.drawOneCardFrom(customDeck);

		dealer.drawOneCardFrom(customDeck);
		dealer.drawOneCardFrom(customDeck);
		dealer.drawOneCardFrom(customDeck);

		Outcome outcome = dealer.judgeOutcomeOf(player);
		assertThat(outcome).isEqualTo(Outcome.WIN);
	}

	@DisplayName("둘다 버스트 아닐 시 딜러와 플레이어의 점수 비교로 결과 판단")
	@Test
	void judgeOutcome_noOneBust_compareScore() {
		final Deck customDeck = createCustomDeck(
				Card.of(CardSymbol.HEART, CardNumber.QUEEN),
				Card.of(CardSymbol.HEART, CardNumber.JACK),
				Card.of(CardSymbol.HEART, CardNumber.ACE),
				Card.of(CardSymbol.SPADE, CardNumber.QUEEN),
				Card.of(CardSymbol.SPADE, CardNumber.JACK)
		);
		player.drawOneCardFrom(customDeck);
		player.drawOneCardFrom(customDeck);

		dealer.drawOneCardFrom(customDeck);
		dealer.drawOneCardFrom(customDeck);
		dealer.drawOneCardFrom(customDeck);

		Outcome outcome = dealer.judgeOutcomeOf(player);
		assertThat(outcome).isEqualTo(Outcome.LOSE);
	}

	@DisplayName("플레이어가 블랙잭으로 승리할 경우 블랙잭으로 승리하는 경우로 판단")
	@Test
	void judgeOutcome_playerWinWithBlackjack_returnBlackjackWin() {
		final Deck customDeck = createCustomDeck(
				Card.of(CardSymbol.HEART, CardNumber.QUEEN),
				Card.of(CardSymbol.HEART, CardNumber.JACK),
				Card.of(CardSymbol.HEART, CardNumber.ACE),

				Card.of(CardSymbol.SPADE, CardNumber.ACE),
				Card.of(CardSymbol.SPADE, CardNumber.JACK)
		);
		player.drawOneCardFrom(customDeck);
		player.drawOneCardFrom(customDeck);

		dealer.drawOneCardFrom(customDeck);
		dealer.drawOneCardFrom(customDeck);
		dealer.drawOneCardFrom(customDeck);

		Outcome outcome = dealer.judgeOutcomeOf(player);
		assertThat(outcome).isEqualTo(Outcome.BLACKJACK_WIN);
	}

	private Deck createCustomDeck(final Card... cards) {
		return new Deck(Arrays.asList(cards));
	}
}
