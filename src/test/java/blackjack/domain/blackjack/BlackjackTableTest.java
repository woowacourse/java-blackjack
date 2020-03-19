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
	void dealInitialHand_DealerAndPlayer_DrawInitialCardsForEachUsers() {
		BlackjackTable blackjackTable = new BlackjackTable(deck, dealer, players);
		blackjackTable.dealInitialHand();

		for (User user : blackjackTable.collectUsers()) {
			assertThat(user).extracting("hand").asList().hasSize(INITIAL_DEAL_NUMBER);
		}
	}

	@Test
	void collectUsers_DealerAndPlayers_ReturnUserList() {
		BlackjackTable blackjackTable = new BlackjackTable(deck, dealer, players);

		List<User> expected = new ArrayList<>();
		expected.add(dealer);
		expected.addAll(players);
		assertThat(blackjackTable.collectUsers()).isEqualTo(expected);
	}

	@Test
	void isDealerBlackjack_DealerIsBlackjack_ReturnTrue() {
		Dealer blackjackDealer = Dealer.valueOf(Dealer.NAME, Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.ACE, Type.SPADE)));
		BlackjackTable blackjackTable = new BlackjackTable(deck, blackjackDealer, players);

		assertThat(blackjackTable.isDealerBlackjack()).isTrue();
	}

	// NOTE : 2020-03-17 리뷰어에게 테스트 작성에 대해 물어보기
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

		for (User user : blackjackTable.collectUsers()) {
			assertThat(user).extracting("hand").asList().hasSize(4);
		}
	}
}
