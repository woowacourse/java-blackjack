package blakcjack.domain.outcome;

import blakcjack.domain.card.Card;
import blakcjack.domain.card.CardNumber;
import blakcjack.domain.card.CardSymbol;
import blakcjack.domain.card.Deck;
import blakcjack.domain.game.BlackjackGame;
import blakcjack.domain.shufflestrategy.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OutcomeStatisticsTest {
	private Map<Outcome, Integer> dealerOutcome;
	private Map<String, Outcome> playersOutcome;
	private List<String> names;
	private Deck deck;

	@BeforeEach
	void setUp() {
		dealerOutcome = new LinkedHashMap<>();
		dealerOutcome.put(Outcome.WIN, 2);
		dealerOutcome.put(Outcome.DRAW, 1);
		dealerOutcome.put(Outcome.LOSE, 0);

		playersOutcome = new LinkedHashMap<>();
		playersOutcome.put("pobi", Outcome.LOSE);
		playersOutcome.put("sakjung", Outcome.DRAW);
		playersOutcome.put("mediumBear", Outcome.LOSE);

		final ShuffleStrategy nonShuffleStrategy = (cards) -> {
		};
		names = Arrays.asList("pobi", "sakjung", "mediumBear");
		deck = new Deck(nonShuffleStrategy);
	}

	@DisplayName("객체 생성 성공 2")
	@Test
	void create2() {
		BlackjackGame blackjackGame = new BlackjackGame(deck, names);
		blackjackGame.getPlayers().get(0).receiveCard(Card.of(CardSymbol.SPADE, CardNumber.TWO)); // lose
		blackjackGame.getPlayers().get(0).receiveCard(Card.of(CardSymbol.SPADE, CardNumber.THREE));

		blackjackGame.getPlayers().get(1).receiveCard(Card.of(CardSymbol.SPADE, CardNumber.KING)); // draw
		blackjackGame.getPlayers().get(1).receiveCard(Card.of(CardSymbol.SPADE, CardNumber.ACE));

		blackjackGame.getPlayers().get(2).receiveCard(Card.of(CardSymbol.SPADE, CardNumber.FOUR)); // lose
		blackjackGame.getPlayers().get(2).receiveCard(Card.of(CardSymbol.SPADE, CardNumber.FIVE));

		blackjackGame.getDealer().receiveCard(Card.of(CardSymbol.HEART, CardNumber.ACE));
		blackjackGame.getDealer().receiveCard(Card.of(CardSymbol.HEART, CardNumber.JACK));

		OutcomeStatistics outcomeStatistics = new OutcomeStatistics(blackjackGame);
		assertThat(outcomeStatistics.getDealerOutcome()).isEqualTo(dealerOutcome);
		assertThat(outcomeStatistics.getPlayersOutcome()).isEqualTo(playersOutcome);
	}
}