package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
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
	}

	@Test
	void from_DealerAndPlayers_GenerateInstance() {
		BlackjackTable blackjackTable = new BlackjackTable(new Deck(CardFactory.create()), dealer, players);

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
	void generatePlayersResult_dealerAndPlayers_MapOfPlayerAndResultType() {
		BlackjackTable blackjackTable = new BlackjackTable(new Deck(CardFactory.create()), dealer, players);
		Report report = Report.from(blackjackTable);

		Map<Player, ResultType> expected = new HashMap<>();
		expected.put(players.get(0), ResultType.WIN);
		expected.put(players.get(1), ResultType.DRAW);
		expected.put(players.get(2), ResultType.LOSE);
		assertThat(report).extracting("playersResult").isEqualTo(expected);
	}

	@Test
	void generateDealerResult_dealerAndPlayers_MapOfResultTypeAndCount() {
		BlackjackTable blackjackTable = new BlackjackTable(new Deck(CardFactory.create()), dealer, players);
		Report report = Report.from(blackjackTable);

		Map<ResultType, Long> expected = new EnumMap<>(ResultType.class);
		expected.put(ResultType.WIN, 1L);
		expected.put(ResultType.DRAW, 1L);
		expected.put(ResultType.LOSE, 1L);
		assertThat(report).extracting("dealerResult").isEqualTo(expected);
	}
}
