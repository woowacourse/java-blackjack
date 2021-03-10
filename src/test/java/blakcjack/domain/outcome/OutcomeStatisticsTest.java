package blakcjack.domain.outcome;

import blakcjack.domain.card.Card;
import blakcjack.domain.card.CardNumber;
import blakcjack.domain.card.CardSymbol;
import blakcjack.domain.card.Deck;
import blakcjack.domain.money.Money;
import blakcjack.domain.name.Name;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OutcomeStatisticsTest {
	private Dealer dealer;
	private List<Player> players;
	//TODO
	// win, blackjack win case 추가
	@BeforeEach
	void setUp() {
		Deck customDeck = createCustomDeck(
				Card.of(CardSymbol.SPADE, CardNumber.FIVE), // medium bear - lose
				Card.of(CardSymbol.SPADE, CardNumber.FOUR),

				Card.of(CardSymbol.SPADE, CardNumber.ACE), // sakjung - draw
				Card.of(CardSymbol.SPADE, CardNumber.KING),

				Card.of(CardSymbol.SPADE, CardNumber.THREE), // pobi - lose
				Card.of(CardSymbol.SPADE, CardNumber.TWO),

				Card.of(CardSymbol.HEART, CardNumber.JACK), // dealer
				Card.of(CardSymbol.HEART, CardNumber.ACE)
		);
		Player player1 = new Player(new Name("pobi"), new Money(100));
		Player player2 = new Player(new Name("sakjung"), new Money(200));
		Player player3 = new Player(new Name("mediumBear"), new Money(100));
		dealer = new Dealer();
		drawCards(customDeck, dealer, player1, player2, player3);
		players = Arrays.asList(player1, player2, player3);
	}

	@DisplayName("객체 생성 제대로 하는지")
	@Test
	void create() {
		final Map<Participant, Money> expectedParticipantsProfit = new LinkedHashMap<>();
		expectedParticipantsProfit.put(dealer, new Money(200));
		expectedParticipantsProfit.put(players.get(0), new Money(-100));
		expectedParticipantsProfit.put(players.get(1), new Money(0));
		expectedParticipantsProfit.put(players.get(2), new Money(-100));

		OutcomeStatistics outcomeStatistics = new OutcomeStatistics(dealer, players);
		assertThat(outcomeStatistics.getParticipantsProfit()).isEqualTo(expectedParticipantsProfit);
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