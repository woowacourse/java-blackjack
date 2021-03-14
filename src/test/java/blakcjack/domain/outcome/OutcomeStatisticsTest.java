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

	@BeforeEach
	void setUp() {
		Deck customDeck = createCustomDeck(
				Card.of(CardSymbol.CLUB, CardNumber.QUEEN), // connie - draw
				Card.of(CardSymbol.CLUB, CardNumber.NINE),

				Card.of(CardSymbol.SPADE, CardNumber.FIVE), // medium bear - lose
				Card.of(CardSymbol.SPADE, CardNumber.FOUR),

				Card.of(CardSymbol.SPADE, CardNumber.ACE), // sakjung - blackjack win
				Card.of(CardSymbol.SPADE, CardNumber.KING),

				Card.of(CardSymbol.HEART, CardNumber.KING), // pobi - win
				Card.of(CardSymbol.HEART, CardNumber.QUEEN),

				Card.of(CardSymbol.HEART, CardNumber.JACK), // dealer - 19
				Card.of(CardSymbol.HEART, CardNumber.NINE)
		);
		Player player1 = new Player(new Name("pobi"), new Money(100));
		Player player2 = new Player(new Name("sakjung"), new Money(100));
		Player player3 = new Player(new Name("mediumBear"), new Money(200));
		Player player4 = new Player(new Name("connie"), new Money(100));
		dealer = new Dealer();
		drawCards(customDeck, dealer, player1, player2, player3, player4);
		players = Arrays.asList(player1, player2, player3, player4);
	}

	@DisplayName("객체 생성 할 때 딜러와 플레이어들의 카드를 토대로 수익 통계를 제대로 만드는지")
	@Test
	void create() {
		final Map<Participant, Money> expectedParticipantsProfit = new LinkedHashMap<>();
		expectedParticipantsProfit.put(dealer, new Money(-50));
		expectedParticipantsProfit.put(players.get(0), new Money(100));
		expectedParticipantsProfit.put(players.get(1), new Money(150));
		expectedParticipantsProfit.put(players.get(2), new Money(-200));
		expectedParticipantsProfit.put(players.get(3), new Money(0));

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