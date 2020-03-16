package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.exceptions.InvalidReportException;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.PlayerFactory;
import blackjack.util.StringUtil;

class ReportTest {
	private Dealer dealer;
	private List<Player> players;

	private static Stream<Arguments> provideNullDealerOrNullPlayers() {
		Dealer dealer = new Dealer(Dealer.NAME);
		List<Player> players = Arrays.asList(
			new Player("pobi"),
			new Player("stitch"));

		return Stream.of(
			Arguments.of(null, players),
			Arguments.of(dealer, null),
			Arguments.of(null, null));
	}

	@BeforeEach
	void setUp() {
		dealer = Dealer.valueOf("dealer", Arrays.asList(
			new Card(Symbol.EIGHT, Type.HEART),
			new Card(Symbol.KING, Type.DIAMOND)));

		Player pobi = Player.valueOf("pobi", Arrays.asList(
			new Card(Symbol.QUEEN, Type.HEART),
			new Card(Symbol.KING, Type.DIAMOND)));
		Player sony = Player.valueOf("sony", Arrays.asList(
			new Card(Symbol.EIGHT, Type.HEART),
			new Card(Symbol.KING, Type.DIAMOND)));
		Player stitch = Player.valueOf("stitch", Arrays.asList(
			new Card(Symbol.SEVEN, Type.HEART),
			new Card(Symbol.KING, Type.DIAMOND)));
		players = Arrays.asList(pobi, sony, stitch);
	}

	@Test
	void from_DealerAndPlayers_GenerateInstance() {
		assertThat(Report.from(new Dealer("dealer"),
			PlayerFactory.create(StringUtil.parsingPlayerNames("pobi, sony, stitch"))))
			.isInstanceOf(Report.class);
	}

	@ParameterizedTest
	@MethodSource("provideNullDealerOrNullPlayers")
	void validateUser_DealerOrPlayersHaveNullValue_InvalidReportExceptionThrown(Dealer nullDealer,
		List<Player> nullPlayers) {
		assertThatThrownBy(() -> Report.from(nullDealer, nullPlayers))
			.isInstanceOf(InvalidReportException.class)
			.hasMessage(InvalidReportException.EMPTY);
	}

	@Test
	void generatePlayersResult_dealerAndPlayers_MapOfPlayerAndResultType() {
		Report report = Report.from(dealer, players);

		Map<Player, ResultType> expected = new HashMap<>();
		expected.put(players.get(0), ResultType.WIN);
		expected.put(players.get(1), ResultType.DRAW);
		expected.put(players.get(2), ResultType.LOSE);
		assertThat(report).extracting("playersResult").isEqualTo(expected);
	}

	@Test
	void generateDealerResult_dealerAndPlayers_MapOfResultTypeAndCount() {
		Report report = Report.from(dealer, players);

		Map<ResultType, Long> expected = new EnumMap<>(ResultType.class);
		expected.put(ResultType.WIN, 1L);
		expected.put(ResultType.DRAW, 1L);
		expected.put(ResultType.LOSE, 1L);
		assertThat(report).extracting("dealerResult").isEqualTo(expected);
	}
}
