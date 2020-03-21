package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.exceptions.InvalidReportException;
import blackjack.domain.user.Player;

class ReportTest {
	@Test
	void PlayersBettingMoney_PlayersBettingMoney_GenerateInstance() {
		Player pobi = Player.valueOf("pobi", Arrays.asList(Card.of(Symbol.EIGHT, Type.DIAMOND)));
		Player stitch = Player.valueOf("stitch", Arrays.asList(Card.of(Symbol.ACE, Type.HEART)));
		Map<Player, BettingMoney> playersBettingMoney = new HashMap<>();
		playersBettingMoney.put(pobi, BettingMoney.valueOf(10000));
		playersBettingMoney.put(stitch, BettingMoney.valueOf(5000));

		assertThat(new Report(playersBettingMoney)).isInstanceOf(Report.class);
	}

	@ParameterizedTest
	@NullSource
	void validate_EmptyPlayersBettingMoney_InvalidPlayersBettingMoneyExceptionThrown(Map<Player, BettingMoney> value) {
		assertThatThrownBy(() -> new Report(value))
			.isInstanceOf(InvalidReportException.class)
			.hasMessage(InvalidReportException.NULL);
	}

	@Test
	void calculateResultBy_PlayersResultType_GenerateResultPlayersBettingMoney() {
		Player pobi = Player.valueOf("pobi", Arrays.asList(Card.of(Symbol.EIGHT, Type.DIAMOND)));
		Player stitch = Player.valueOf("stitch", Arrays.asList(Card.of(Symbol.ACE, Type.HEART)));

		Map<Player, BettingMoney> bettingMoney = new HashMap<>();
		bettingMoney.put(pobi, BettingMoney.valueOf(10000));
		bettingMoney.put(stitch, BettingMoney.valueOf(5000));
		Report playersBettingMoney = new Report(bettingMoney);

		Map<Player, ResultType> playersResultType = new HashMap<>();
		playersResultType.put(pobi, ResultType.WIN);
		playersResultType.put(stitch, ResultType.LOSE);

		Map<Player, BettingMoney> expected = new HashMap<>();
		expected.put(pobi, BettingMoney.valueOf(10000));
		expected.put(stitch, BettingMoney.valueOf(-5000));

		assertThat(playersBettingMoney.calculateResultBy(playersResultType))
			.extracting("playersBettingMoney").isEqualTo(expected);
	}

	@Test
	void getDealerBettingProfit_PlayersBettingProfit_ReturnDealerProfit() {
		Player pobi = Player.valueOf("pobi", Arrays.asList(Card.of(Symbol.EIGHT, Type.DIAMOND)));
		Player stitch = Player.valueOf("stitch", Arrays.asList(Card.of(Symbol.ACE, Type.HEART)));

		Map<Player, BettingMoney> bettingMoney = new HashMap<>();
		bettingMoney.put(pobi, BettingMoney.valueOf(10000));
		bettingMoney.put(stitch, BettingMoney.valueOf(-5000));
		Report report = new Report(bettingMoney);

		int expected = -5000;
		assertThat(report.getDealerBettingProfit()).isEqualTo(expected);
	}

	@Test
	void calculatePlayersProfit_PlayersResult_ReturnPlayersResultToInt() {
		Player pobi = Player.valueOf("pobi", Arrays.asList(Card.of(Symbol.EIGHT, Type.DIAMOND)));
		Player stitch = Player.valueOf("stitch", Arrays.asList(Card.of(Symbol.ACE, Type.HEART)));

		Map<Player, BettingMoney> bettingMoney = new HashMap<>();
		bettingMoney.put(pobi, BettingMoney.valueOf(10000));
		bettingMoney.put(stitch, BettingMoney.valueOf(5000));
		Report report = new Report(bettingMoney);

		Map<Player, Integer> expected = new HashMap<>();
		expected.put(pobi, 10000);
		expected.put(stitch, 5000);
		assertThat(report.getPlayersBettingProfit()).isEqualTo(expected);
	}
}
