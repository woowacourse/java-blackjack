package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.PlayerFactory;

class ReportTest {
	private Dealer dealer;
	private List<Player> players;

	@BeforeEach
	void setUp() {
		List<Card> dealerCards = Arrays.asList(new Card(Symbol.EIGHT, Type.HEART), new Card(Symbol.KING, Type.DIAMOND));
		List<Card> pobiCards = Arrays.asList(new Card(Symbol.QUEEN, Type.HEART), new Card(Symbol.KING, Type.DIAMOND));
		List<Card> sonyCards = Arrays.asList(new Card(Symbol.EIGHT, Type.HEART), new Card(Symbol.KING, Type.DIAMOND));
		List<Card> stitchCards = Arrays.asList(new Card(Symbol.SEVEN, Type.HEART), new Card(Symbol.KING, Type.DIAMOND));

		Player pobi = Player.valueOf("pobi", pobiCards);
		Player sony = Player.valueOf("sony", sonyCards);
		Player stitch = Player.valueOf("stitch", stitchCards);

		dealer = Dealer.valueOf("dealer", dealerCards);
		players = Arrays.asList(pobi, sony, stitch);
	}

	@Test
	void from_DealerAndPlayers_GenerateInstance() {
		assertThat(Report.from(new Dealer("dealer"), PlayerFactory.create("pobi, sony, stitch")))
			.isInstanceOf(Report.class);
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
