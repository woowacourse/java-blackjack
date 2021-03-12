package blakcjack.domain.participant;

import blakcjack.domain.card.*;
import blakcjack.domain.money.Money;
import blakcjack.domain.name.Name;
import blakcjack.domain.outcome.OutcomeStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static blakcjack.domain.card.EmptyDeckException.EMPTY_DECK_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantsTest {
	private Participants participants;
	private List<Player> players;
	private Dealer dealer;
	private Money money;

	@BeforeEach
	void setUp() {
		money = new Money(10);
		Player player1 = new Player(new Name("pobi"), new Money(100));
		Player player2 = new Player(new Name("sakjung"), new Money(200));
		Player player3 = new Player(new Name("mediumBear"), new Money(100));
		dealer = new Dealer();
		players = Arrays.asList(player1, player2, player3);
		participants = new Participants(dealer, players);
	}

	@DisplayName("딜러와 플레이어들을 정상적으로 잘 생성하는지")
	@Test
	void create_playerNames_createDealerAndPlayers() {
		assertThat(participants.getParticipants()).isEqualTo(Arrays.asList(
				new Dealer(),
				new Player(new Name("pobi"), money),
				new Player(new Name("sakjung"), money),
				new Player(new Name("mediumBear"), money)
		));
	}

	@DisplayName("처음에 각 참가자들에게 두장씩 나눠주는 것을 제대로 나눠 주는지")
	@Test
	void initializeHandsFrom() {
		final Deck customDeck = createCustomDeck(
				Card.of(CardSymbol.HEART, CardNumber.JACK), // mediumBear
				Card.of(CardSymbol.HEART, CardNumber.ACE),

				Card.of(CardSymbol.SPADE, CardNumber.FIVE), // sakjung
				Card.of(CardSymbol.SPADE, CardNumber.FOUR),

				Card.of(CardSymbol.DIAMOND, CardNumber.ACE), // pobi
				Card.of(CardSymbol.DIAMOND, CardNumber.KING),

				Card.of(CardSymbol.SPADE, CardNumber.ACE), // Dealer
				Card.of(CardSymbol.SPADE, CardNumber.KING)
		);

		participants.initializeHandsFrom(customDeck);

		assertThatThrownBy(customDeck::pop).isInstanceOf(EmptyDeckException.class)
				.hasMessage(EMPTY_DECK_ERROR);

		final Cards dealerExpectedCards = new Cards();
		dealerExpectedCards.add(Card.of(CardSymbol.SPADE, CardNumber.KING));
		dealerExpectedCards.add(Card.of(CardSymbol.SPADE, CardNumber.ACE));
		assertThat(participants.getParticipants().get(0).getCards()).isEqualTo(dealerExpectedCards);

		final Cards pobiExpectedCards = new Cards();
		pobiExpectedCards.add(Card.of(CardSymbol.DIAMOND, CardNumber.KING));
		pobiExpectedCards.add(Card.of(CardSymbol.DIAMOND, CardNumber.ACE));
		assertThat(participants.getParticipants().get(1).getCards()).isEqualTo(pobiExpectedCards);
	}

	@DisplayName("플레이어들의 결과를 토대로 결과 통계를 올바르게 생성해 내는지")
	@Test
	void getOutcomeStatistics_giveCardsToPlayersAndDealer_createCorrespondingOutcomeStatistics() {
		final Deck customDeck = createCustomDeck(
				Card.of(CardSymbol.SPADE, CardNumber.FIVE), // medium bear - lose
				Card.of(CardSymbol.SPADE, CardNumber.FOUR),

				Card.of(CardSymbol.SPADE, CardNumber.ACE), // sakjung - draw
				Card.of(CardSymbol.SPADE, CardNumber.KING),

				Card.of(CardSymbol.SPADE, CardNumber.THREE), // pobi - lose
				Card.of(CardSymbol.SPADE, CardNumber.TWO),

				Card.of(CardSymbol.HEART, CardNumber.JACK), // dealer
				Card.of(CardSymbol.HEART, CardNumber.ACE)
		);
		participants.initializeHandsFrom(customDeck);

		assertThat(participants.getOutcomeStatistics()).isEqualTo(new OutcomeStatistics(dealer, players));
	}

	private Deck createCustomDeck(final Card... cards) {
		return new Deck(Arrays.asList(cards));
	}
}