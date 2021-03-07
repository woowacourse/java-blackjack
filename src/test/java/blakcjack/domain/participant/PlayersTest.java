package blakcjack.domain.participant;

import blakcjack.domain.card.*;
import blakcjack.domain.name.Names;
import blakcjack.domain.outcome.Outcome;
import blakcjack.domain.outcome.OutcomeStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static blakcjack.domain.card.EmptyDeckException.EMPTY_DECK_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {
	private Players players;
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		final Names names = new Names(Arrays.asList("pobi", "sakjung", "mediumBear"));
		players = new Players(names);
		dealer = new Dealer();
	}

	@Test
	void initializeHandsFrom() {
		Deck customDeck = createCustomDeck(
				Card.of(CardSymbol.HEART, CardNumber.JACK),
				Card.of(CardSymbol.HEART, CardNumber.ACE),

				Card.of(CardSymbol.SPADE, CardNumber.FIVE),
				Card.of(CardSymbol.SPADE, CardNumber.FOUR),

				Card.of(CardSymbol.SPADE, CardNumber.ACE),
				Card.of(CardSymbol.SPADE, CardNumber.KING)
		);

		players.initializeHandsFrom(customDeck);

		assertThatThrownBy(customDeck::drawCard).isInstanceOf(EmptyDeckException.class)
				.hasMessage(EMPTY_DECK_ERROR);

		Cards cards = new Cards();
		cards.add(Card.of(CardSymbol.SPADE, CardNumber.KING));
		cards.add(Card.of(CardSymbol.SPADE, CardNumber.ACE));
		assertThat(players.toList().get(0).getCards()).isEqualTo(cards);
	}

	@DisplayName("플레이어들의 결과를 토대로 결과 통계를 올바르게 생성해 내는지")
	@Test
	void getOutcomeStatisticsBy_giveCardsToPlayersAndDealer_createCorrespondingOutcomeStatistics() {
		Deck customDeck = createCustomDeck(
				Card.of(CardSymbol.HEART, CardNumber.JACK), // dealer
				Card.of(CardSymbol.HEART, CardNumber.ACE),

				Card.of(CardSymbol.SPADE, CardNumber.FIVE), // medium bear - lose
				Card.of(CardSymbol.SPADE, CardNumber.FOUR),

				Card.of(CardSymbol.SPADE, CardNumber.ACE), // sakjung - draw
				Card.of(CardSymbol.SPADE, CardNumber.KING),

				Card.of(CardSymbol.SPADE, CardNumber.THREE), // pobi - lose
				Card.of(CardSymbol.SPADE, CardNumber.TWO)
		);

		players.initializeHandsFrom(customDeck);
		dealer.initializeHandFrom(customDeck);

		final Map<String, Outcome> expectedPlayersOutcome = new LinkedHashMap<>();
		expectedPlayersOutcome.put("pobi", Outcome.LOSE);
		expectedPlayersOutcome.put("sakjung", Outcome.DRAW);
		expectedPlayersOutcome.put("mediumBear", Outcome.LOSE);

		assertThat(players.getOutcomeStatisticsBy(dealer)).isEqualTo(new OutcomeStatistics(expectedPlayersOutcome));
	}

	private Deck createCustomDeck(final Card... cards) {
		return new Deck(Arrays.asList(cards));
	}
}