package blackjack.domain.blackjack;

import static blackjack.domain.blackjack.BlackjackTable.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.exceptions.InvalidBlackjackTableException;
import blackjack.domain.result.BettingMoney;
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

		Player pobi = new Player("pobi");
		Player sony = new Player("sony");
		Player stitch = new Player("stitch");
		players = Arrays.asList(pobi, sony, stitch);
	}

	@Test
	void BlackjackTable_InputDeck_GenerateInstance() {
		assertThat(new BlackjackTable(deck, dealer, players)).isInstanceOf(BlackjackTable.class);
	}

	@Test
	void validate_NullSource_InvalidBlackjackTableExceptionThrown() {
		assertThatThrownBy(() -> new BlackjackTable(null, dealer, players))
			.isInstanceOf(InvalidBlackjackTableException.class)
			.hasMessageEndingWith(InvalidBlackjackTableException.DECK_OR_DEALER_NULL);
		assertThatThrownBy(() -> new BlackjackTable(deck, null, players))
			.isInstanceOf(InvalidBlackjackTableException.class)
			.hasMessageEndingWith(InvalidBlackjackTableException.DECK_OR_DEALER_NULL);
		assertThatThrownBy(() -> new BlackjackTable(deck, dealer, null))
			.isInstanceOf(InvalidBlackjackTableException.class)
			.hasMessageEndingWith(InvalidBlackjackTableException.PLAYERS_EMPTY);
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
		Dealer blackjackDealer = Dealer.from(Arrays.asList(
			Card.of(Symbol.TEN, Type.CLUB),
			Card.of(Symbol.ACE, Type.SPADE)));
		BlackjackTable blackjackTable = new BlackjackTable(deck, blackjackDealer, players);

		assertThat(blackjackTable.isDealerBlackjack()).isTrue();
	}

	@Test
	void playWith_UserDecisions_PlayGameWithUserDecisions() {
		Dealer drownDealer = Dealer.from(Arrays.asList(
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

	@ParameterizedTest
	@NullSource
	void validate_NullUserDecisions_NullPointerExceptionThrown(UserDecisions value) {
		BlackjackTable blackjackTable = new BlackjackTable(deck, dealer, players);

		assertThatThrownBy(() -> blackjackTable.playWith(value))
			.isInstanceOf(InvalidBlackjackTableException.class)
			.hasMessage(InvalidBlackjackTableException.USER_DECISIONS_NULL);
	}

	@ParameterizedTest
	@MethodSource("provideBlackjackTableAndPlayersBettingMoney")
	void reportFrom_PlayersBettingMoney_GeneratePlayersBettingMoneyInstance(BlackjackTable blackjackTable,
		Map<Player, BettingMoney> playersBettingMoney, Map<Player, BettingMoney> expected) {
		assertThat(blackjackTable.reportFrom(playersBettingMoney)).extracting("playersProfit")
			.isEqualTo(expected);
	}

	private static Stream<Arguments> provideBlackjackTableAndPlayersBettingMoney() {
		Deck deck = new Deck(CardFactory.create());

		Dealer dealer = Dealer.from(Arrays.asList(
			Card.of(Symbol.EIGHT, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND)));

		Player pobi = Player.valueOf("pobi", Arrays.asList(
			Card.of(Symbol.QUEEN, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND)));
		Player sony = Player.valueOf("sony", Arrays.asList(
			Card.of(Symbol.EIGHT, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND)));
		Player stitch = Player.valueOf("stitch", Arrays.asList(
			Card.of(Symbol.SEVEN, Type.HEART),
			Card.of(Symbol.KING, Type.DIAMOND)));
		List<Player> players = Arrays.asList(pobi, sony, stitch);
		BlackjackTable blackjackTable = new BlackjackTable(deck, dealer, players);

		Map<Player, BettingMoney> bettingMoney = new LinkedHashMap<>();
		bettingMoney.put(pobi, new BettingMoney("10000"));
		bettingMoney.put(sony, new BettingMoney("5000"));
		bettingMoney.put(stitch, new BettingMoney("1000"));

		Map<Player, Integer> expected = new LinkedHashMap<>();
		expected.put(pobi, 10000);
		expected.put(sony, 0);
		expected.put(stitch, -1000);

		return Stream.of(
			Arguments.of(blackjackTable, bettingMoney, expected));
	}

	@ParameterizedTest
	@NullAndEmptySource
	void validate_Null_InvalidBlackjackTableExceptionThrown(Map<Player, BettingMoney> playersBettingMoney) {
		BlackjackTable blackjackTable = new BlackjackTable(deck, dealer, players);

		assertThatThrownBy(() -> blackjackTable.reportFrom(playersBettingMoney))
			.isInstanceOf(InvalidBlackjackTableException.class)
			.hasMessage(InvalidBlackjackTableException.PLAYERS_BETTING_MONEY_EMPTY);
	}
}
