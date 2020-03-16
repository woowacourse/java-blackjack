package blackjack.domain.blackjack;

import static blackjack.domain.blackjack.BlackjackTable.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

class BlackjackTableTest {
	private Deck deck;
	private Dealer dealer;
	private List<Player> players;

	@BeforeEach
	void setUp() {
		deck = new Deck(CardFactory.create());
		dealer = new Dealer(Dealer.NAME);
		players = Arrays.asList(
			new Player("pobi"),
			new Player("sony"),
			new Player("stitch"));
	}

	@Test
	void BlackjackTable_InputDeck_GenerateInstance() {
		assertThat(new BlackjackTable(deck, dealer, players)).isInstanceOf(BlackjackTable.class);
	}

	@Test
	void setUp_DealerAndPlayer_DrawInitialCardsForEachUsers() {
		BlackjackTable blackjackTable = new BlackjackTable(deck, dealer, players);
		blackjackTable.setUp();

		for (User user : blackjackTable.collectToUsers()) {
			assertThat(user).extracting("hand").asList().hasSize(INITIAL_DRAW_NUMBER);
		}
	}

	@Test
	void collectToUsers_DealerAndPlayers_ReturnUserList() {
		BlackjackTable blackjackTable = new BlackjackTable(deck, dealer, players);

		List<User> expected = new ArrayList<>();
		expected.add(dealer);
		expected.addAll(players);
		assertThat(blackjackTable.collectToUsers()).isEqualTo(expected);
	}

	@Test
	void playWith_UserDecisions_PlayGameWithUserDecisions() {
		Dealer drownDealer = Dealer.valueOf("dealer", Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.TWO, Type.SPADE),
			Card.of(Symbol.FOUR, Type.DIAMOND)));
		List<Player> drownPlayers = Arrays.asList(
			Player.valueOf("pobi", Arrays.asList(
				Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.ACE, Type.SPADE),
				Card.of(Symbol.KING, Type.DIAMOND))),
			Player.valueOf("pobi", Arrays.asList(
				Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.ACE, Type.SPADE),
				Card.of(Symbol.KING, Type.DIAMOND))),
			Player.valueOf("pobi", Arrays.asList(
				Card.of(Symbol.TEN, Type.CLUB),
				Card.of(Symbol.ACE, Type.SPADE),
				Card.of(Symbol.KING, Type.DIAMOND))));
		BlackjackTable blackjackTable = new BlackjackTable(deck, drownDealer, drownPlayers);
		UserDecisions userDecisions = new UserDecisions(
			(Player player) -> "y",
			(User user, List<Card> cards) -> System.out.println(),
			System.out::println);
		blackjackTable.playWith(userDecisions);

		for (User user : blackjackTable.collectToUsers()) {
			assertThat(user).extracting("hand").asList().hasSize(4);
		}
	}
}
