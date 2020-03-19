package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import blackjack.domain.blackjack.BlackjackTable;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.exceptions.InvalidReportException;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

class ReportTest {
	private Deck deck;
	private Dealer dealer;
	private List<Player> players;
	private Map<Player, BettingMoney> playersBettingMoney;

	@BeforeEach
	void setUp() {
		deck = new Deck(CardFactory.create());

		dealer = Dealer.valueOf("dealer", Arrays.asList(
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
		players = Arrays.asList(pobi, sony, stitch);

		playersBettingMoney = new LinkedHashMap<>();
		playersBettingMoney.put(pobi, BettingMoney.valueOf("10000"));
		playersBettingMoney.put(sony, BettingMoney.valueOf("5000"));
		playersBettingMoney.put(stitch, BettingMoney.valueOf("1000"));
	}

	@Test
	void from_DealerAndPlayers_GenerateInstance() {
		BlackjackTable blackjackTable = new BlackjackTable(deck, dealer, players, playersBettingMoney);

		assertThat(Report.from(blackjackTable))
			.isInstanceOf(Report.class);
	}

	@ParameterizedTest
	@NullSource
	void validate_DealerOrPlayersHaveNullValue_InvalidReportExceptionThrown(BlackjackTable blackjackTable) {
		assertThatThrownBy(() -> Report.from(blackjackTable))
			.isInstanceOf(InvalidReportException.class)
			.hasMessage(InvalidReportException.EMPTY);
	}

	@Test
	void calculateDealerProfit_PlayersProfit_ReturnDealerProfit() {
		BlackjackTable blackjackTable = new BlackjackTable(deck, dealer, players, playersBettingMoney);
		Report report = Report.from(blackjackTable);

		int expected = -9000;
		assertThat(report.calculateDealerProfit()).isEqualTo(expected);
	}

	@Test
	void getPlayersProfit_PlayersResult_ReturnPlayersProfitToInt() {
		BlackjackTable blackjackTable = new BlackjackTable(deck, dealer, players, playersBettingMoney);
		Report report = Report.from(blackjackTable);

		Map<Player, Integer> expected = new HashMap<>();
		expected.put(players.get(0), 10000);
		expected.put(players.get(1), 0);
		expected.put(players.get(2), -1000);
		assertThat(report.getPlayersProfit()).isEqualTo(expected);
	}

}
