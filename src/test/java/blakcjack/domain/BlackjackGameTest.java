package blakcjack.domain;

import blakcjack.domain.card.Card;
import blakcjack.domain.card.CardNumber;
import blakcjack.domain.card.CardSymbol;
import blakcjack.domain.card.Deck;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;
import blakcjack.domain.shufflestrategy.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	@DisplayName("객체 생성 성공")
	@Test
	void create() {
		assertThatCode(() -> new BlackjackGame(deck, names))
				.doesNotThrowAnyException();
	}

	@DisplayName("카드 한 장 나눠주기 성공")
	@Test
	void distributeOneCard() {
		final BlackjackGame blackjackGame = new BlackjackGame(deck, names);
		final List<Participant> players = blackjackGame.getPlayers();
		final Participant pobi = players.get(0);
		final Participant expected = new Player("pobi");
		expected.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.KING));

		blackjackGame.distributeOneCard(pobi);
		assertThat(pobi).isEqualTo(expected);
	}

	@DisplayName("딜러와 모든 플레이어에게 2장씩 카드 나눠주기 성공")
	@Test
	void initializeHands() {
		final BlackjackGame blackjackGame = new BlackjackGame(deck, names);
		blackjackGame.initializeHands();

		final List<Participant> players = blackjackGame.getPlayers();
		List<Participant> expectedPlayers = createExpectedPlayers();
		assertThat(players).isEqualTo(expectedPlayers);
	}

	private List<Participant> createExpectedPlayers() {
		final Deck deck = new Deck((cards) -> {});
		final List<Participant> expectedPlayers = new ArrayList<>();
		for (String name : names) {
			Player player = new Player(name);
			player.receiveCard(deck.drawCard());
			player.receiveCard(deck.drawCard());
			expectedPlayers.add(player);
		}
		return expectedPlayers;
	}
}