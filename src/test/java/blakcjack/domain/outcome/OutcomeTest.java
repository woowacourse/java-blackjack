package blakcjack.domain.outcome;

import blakcjack.domain.card.Card;
import blakcjack.domain.card.CardNumber;
import blakcjack.domain.card.CardSymbol;
import blakcjack.domain.card.Deck;
import blakcjack.domain.money.Money;
import blakcjack.domain.name.Name;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class OutcomeTest {
	private Player player;
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		player = new Player(new Name("pobi"), new Money(10));
		dealer = new Dealer();
	}

	@DisplayName("플레이어 버스트시 플레이어 패")
	@Test
	void of_playerBust_returnLose() {
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

		Outcome outcome = Outcome.of(dealer, player);
		assertThat(outcome).isEqualTo(Outcome.LOSE);
	}

	@DisplayName("딜러만 버스트시 플레이어 승")
	@Test
	void of_onlyDealerBust_returnWin() {
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

		Outcome outcome = Outcome.of(dealer, player);
		assertThat(outcome).isEqualTo(Outcome.WIN);
	}

	@DisplayName("둘다 버스트 아닐 시 딜러와 플레이어의 점수 비교로 결과 판단")
	@Test
	void of_noOneBust_compareScore() {
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

		Outcome outcome = Outcome.of(dealer, player);
		assertThat(outcome).isEqualTo(Outcome.LOSE);
	}

	@DisplayName("플레이어가 블랙잭으로 승리할 경우 블랙잭으로 승리하는 경우로 판단")
	@Test
	void of_playerWinWithBlackjack_returnBlackjackWin() {
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

		Outcome outcome = Outcome.of(dealer, player);
		assertThat(outcome).isEqualTo(Outcome.BLACKJACK_WIN);
	}

	@DisplayName("플레이어와 딜러의 결과가 같을 경우 무승부")
	@Test
	void of_PlayerAndDealerHaveSameScoreAndNoOneBust_returnDraw() {
		final Deck customDeck = createCustomDeck(
				Card.of(CardSymbol.HEART, CardNumber.JACK),
				Card.of(CardSymbol.HEART, CardNumber.ACE),

				Card.of(CardSymbol.SPADE, CardNumber.ACE),
				Card.of(CardSymbol.SPADE, CardNumber.JACK)
		);
		player.drawOneCardFrom(customDeck);
		player.drawOneCardFrom(customDeck);

		dealer.drawOneCardFrom(customDeck);
		dealer.drawOneCardFrom(customDeck);

		Outcome outcome = Outcome.of(dealer, player);
		assertThat(outcome).isEqualTo(Outcome.DRAW);
	}

	private Deck createCustomDeck(final Card... cards) {
		return new Deck(Arrays.asList(cards));
	}
}