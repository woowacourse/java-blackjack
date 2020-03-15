package blackjack.domain;

import static blackjack.domain.BlackjackTable.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
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
		assertThat(new BlackjackTable(deck)).isInstanceOf(BlackjackTable.class);
	}

	@Test
	void collectToUsersFrom_DealerAndPlayers_ReturnUserList() {
		BlackjackTable blackjackTable = new BlackjackTable(deck);

		List<User> expected = new ArrayList<>();
		expected.add(dealer);
		expected.addAll(players);
		assertThat(blackjackTable.collectToUsersFrom(dealer, players)).isEqualTo(expected);
	}

	@Test
	void drawInitialCards_DealerAndPlayer_DrawInitialCardsForEachUsers() {
		BlackjackTable blackjackTable = new BlackjackTable(deck);
		List<User> users = blackjackTable.collectToUsersFrom(dealer, players);
		blackjackTable.drawInitialCards(users);

		// NOTE: 2020-03-15 테스트에서 forEach를 사용해도 괜찮은가
		for (User user : users) {
			assertThat(user).extracting("hand").asList().hasSize(INITIAL_DRAW_NUMBER);
		}
	}

	@ParameterizedTest
	@MethodSource("provideDealerOrPlayer")
	void drawCardFrom_User_DrawOneCardAndReturnDrownCards(User user) {
		BlackjackTable blackjackTable = new BlackjackTable(deck);
		blackjackTable.drawCardFrom(user);

		assertThat(user).extracting("hand").asList().hasSize(INITIAL_DRAW_NUMBER + 1);
	}

	private static Stream<Arguments> provideDealerOrPlayer() {
		Deck deck = new Deck(CardFactory.create());

		Dealer dealer = new Dealer(Dealer.NAME);
		dealer.draw(deck, INITIAL_DRAW_NUMBER);

		Player pobi = new Player("pobi");
		pobi.draw(deck, INITIAL_DRAW_NUMBER);

		Player sony = new Player("sony");
		sony.draw(deck, INITIAL_DRAW_NUMBER);

		return Stream.of(
			Arguments.of(dealer),
			Arguments.of(pobi),
			Arguments.of(sony));
	}
}
