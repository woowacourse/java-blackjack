package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.exceptions.InvalidReportException;
import blackjack.domain.user.Player;

class ReportTest {
	@Test
	void Report_PlayersBettingMoneyByInt_GenerateInstance() {
		Player pobi = Player.valueOf("pobi", Arrays.asList(Card.of(Symbol.EIGHT, Type.DIAMOND)));
		Player stitch = Player.valueOf("stitch", Arrays.asList(Card.of(Symbol.ACE, Type.HEART)));
		Map<Player, Integer> playersBettingMoney = new HashMap<>();
		playersBettingMoney.put(pobi, 10000);
		playersBettingMoney.put(stitch, 5000);

		assertThat(new Report(playersBettingMoney)).isInstanceOf(Report.class);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void validate_EmptyPlayersBettingMoney_InvalidPlayersBettingMoneyExceptionThrown(Map<Player, Integer> value) {
		assertThatThrownBy(() -> new Report(value))
			.isInstanceOf(InvalidReportException.class)
			.hasMessage(InvalidReportException.NULL);
	}

	@Test
	void calculateResultBy_PlayersResultType_GenerateResultPlayersBettingMoney() {
		Player pobi = Player.valueOf("pobi", Arrays.asList(Card.of(Symbol.EIGHT, Type.DIAMOND)));
		Player stitch = Player.valueOf("stitch", Arrays.asList(Card.of(Symbol.ACE, Type.HEART)));

		Map<Player, BettingMoney> bettingMoney = new HashMap<>();
		bettingMoney.put(pobi, new BettingMoney(10000));
		bettingMoney.put(stitch, new BettingMoney(5000));

		Map<Player, ResultType> playersResultType = new HashMap<>();
		playersResultType.put(pobi, ResultType.WIN);
		playersResultType.put(stitch, ResultType.LOSE);

		Map<Player, Integer> expected = new HashMap<>();
		expected.put(pobi, 10000);
		expected.put(stitch, -5000);

		assertThat(Report.calculateResultBy(playersResultType, bettingMoney))
			.extracting("playersProfit").isEqualTo(expected);
	}

	@Test
	void getDealerBettingProfit_PlayersBettingProfit_ReturnDealerProfit() {
		Player pobi = Player.valueOf("pobi", Arrays.asList(Card.of(Symbol.EIGHT, Type.DIAMOND)));
		Player stitch = Player.valueOf("stitch", Arrays.asList(Card.of(Symbol.ACE, Type.HEART)));

		Map<Player, Integer> bettingMoney = new HashMap<>();
		bettingMoney.put(pobi, 10000);
		bettingMoney.put(stitch, -5000);
		Report report = new Report(bettingMoney);

		int expected = -5000;
		assertThat(report.getDealerBettingProfit()).isEqualTo(expected);
	}
}
