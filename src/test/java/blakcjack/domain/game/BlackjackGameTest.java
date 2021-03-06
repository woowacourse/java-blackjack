package blakcjack.domain.game;

import blakcjack.domain.card.Card;
import blakcjack.domain.card.CardNumber;
import blakcjack.domain.card.CardSymbol;
import blakcjack.domain.card.Deck;
import blakcjack.domain.name.Name;
import blakcjack.domain.outcome.Outcome;
import blakcjack.domain.outcome.OutcomeStatistics;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;
import blakcjack.domain.shufflestrategy.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static blakcjack.domain.game.DuplicatePlayerNamesException.DUPLICATE_NAME_ERROR;
import static org.assertj.core.api.Assertions.*;

class BlackjackGameTest {
	private List<String> names;
	private Deck deck;

	@BeforeEach
	void setUp() {
		final ShuffleStrategy nonShuffleStrategy = (cards) -> {
		};
		names = Arrays.asList("pobi", "sakjung", "mediumBear");
		deck = new Deck(nonShuffleStrategy);
	}

	@DisplayName("객체 생성 제대로 하는지")
	@Test
	void create() {
		assertThatCode(() -> new BlackjackGame(deck, names))
				.doesNotThrowAnyException();
	}

	@DisplayName("중복 이름 검증 제대로 하는지")
	@Test
	void validateDuplicateNames_duplicatePlayerNames_throwError() {
		assertThatThrownBy(() -> new BlackjackGame(deck, Arrays.asList("pobi", "pobi")))
				.isInstanceOf(DuplicatePlayerNamesException.class)
				.hasMessage(DUPLICATE_NAME_ERROR);
	}

	@DisplayName("카드 한 장을 나눠주는 메서드가 제대로 작동 하는지")
	@Test
	void distributeOneCard() {
		final BlackjackGame blackjackGame = new BlackjackGame(deck, names);
		final List<Player> players = blackjackGame.getPlayers();
		final Participant pobi = players.get(0);
		final Participant expected = new Player(new Name("pobi"));
		expected.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.KING));

		blackjackGame.distributeOneCardTo(pobi);
		assertThat(pobi).isEqualTo(expected);
	}

	@DisplayName("딜러와 모든 플레이어에게 2장씩 카드를 제대로 나눠 주는지")
	@Test
	void initializeHands() {
		final BlackjackGame blackjackGame = new BlackjackGame(deck, names);
		blackjackGame.initializeHands();

		final List<Player> players = blackjackGame.getPlayers();
		final List<Participant> expectedPlayers = createExpectedPlayers();
		assertThat(players).isEqualTo(expectedPlayers);
	}

	private List<Participant> createExpectedPlayers() {
		final Deck deck = new Deck((cards) -> {
		});
		final List<Participant> expectedPlayers = new ArrayList<>();
		for (String name : names) {
			final Player player = new Player(new Name(name));
			player.receiveCard(deck.drawCard());
			player.receiveCard(deck.drawCard());
			expectedPlayers.add(player);
		}
		return expectedPlayers;
	}

	@DisplayName("플레이어들의 결과를 토대로 결과 통계를 올바르게 생성해 내는지")
	@Test
	void getPlayersOutcome_giveCardsToPlayersAndDealer_createCorrespondingOutcomeStatistics() {
		final BlackjackGame blackjackGame = new BlackjackGame(deck, names);
		blackjackGame.getPlayers().get(0).receiveCard(Card.of(CardSymbol.SPADE, CardNumber.TWO)); // lose
		blackjackGame.getPlayers().get(0).receiveCard(Card.of(CardSymbol.SPADE, CardNumber.THREE));

		blackjackGame.getPlayers().get(1).receiveCard(Card.of(CardSymbol.SPADE, CardNumber.KING)); // draw
		blackjackGame.getPlayers().get(1).receiveCard(Card.of(CardSymbol.SPADE, CardNumber.ACE));

		blackjackGame.getPlayers().get(2).receiveCard(Card.of(CardSymbol.SPADE, CardNumber.FOUR)); // lose
		blackjackGame.getPlayers().get(2).receiveCard(Card.of(CardSymbol.SPADE, CardNumber.FIVE));

		blackjackGame.getDealer().receiveCard(Card.of(CardSymbol.HEART, CardNumber.ACE));
		blackjackGame.getDealer().receiveCard(Card.of(CardSymbol.HEART, CardNumber.JACK));

		final Map<String, Outcome> playersOutcome = new LinkedHashMap<>();
		playersOutcome.put("pobi", Outcome.LOSE);
		playersOutcome.put("sakjung", Outcome.DRAW);
		playersOutcome.put("mediumBear", Outcome.LOSE);

		assertThat(blackjackGame.getOutcomeStatistics()).isEqualTo(new OutcomeStatistics(playersOutcome));
	}
}