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
import blackjack.domain.exceptions.InvalidPlayersBettingMoneyException;
import blackjack.domain.user.Player;

class PlayersBettingMoneyTest {
	@Test
	void PlayersBettingMoney_PlayersBettingMoney_GenerateInstance() {
		Player pobi = Player.valueOf("pobi", Arrays.asList(Card.of(Symbol.EIGHT, Type.DIAMOND)));
		Player stitch = Player.valueOf("stitch", Arrays.asList(Card.of(Symbol.ACE, Type.HEART)));
		Map<Player, BettingMoney> playersBettingMoney = new HashMap<>();
		playersBettingMoney.put(pobi, BettingMoney.valueOf(10000));
		playersBettingMoney.put(stitch, BettingMoney.valueOf(5000));

		assertThat(new PlayersBettingMoney(playersBettingMoney)).isInstanceOf(PlayersBettingMoney.class);
	}

	@ParameterizedTest
	@NullSource
	void validate_EmptyPlayersBettingMoney_InvalidPlayersBettingMoneyExceptionThrown(Map<Player, BettingMoney> value) {
		assertThatThrownBy(() -> new PlayersBettingMoney(value))
			.isInstanceOf(InvalidPlayersBettingMoneyException.class)
			.hasMessage(InvalidPlayersBettingMoneyException.NULL);
	}

	@Test
	void calculateResultBy_PlayersResultType_GenerateResultPlayersBettingMoney() {
		Player pobi = Player.valueOf("pobi", Arrays.asList(Card.of(Symbol.EIGHT, Type.DIAMOND)));
		Player stitch = Player.valueOf("stitch", Arrays.asList(Card.of(Symbol.ACE, Type.HEART)));
		Map<Player, BettingMoney> bettingMoney = new HashMap<>();
		bettingMoney.put(pobi, BettingMoney.valueOf(10000));
		bettingMoney.put(stitch, BettingMoney.valueOf(5000));
		PlayersBettingMoney playersBettingMoney = new PlayersBettingMoney(bettingMoney);
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
	void getBettingMoneyFrom_Player_ReturnBettingMoneyFromPlayer() {
		Player pobi = Player.valueOf("pobi", Arrays.asList(Card.of(Symbol.EIGHT, Type.DIAMOND)));
		Player stitch = Player.valueOf("stitch", Arrays.asList(Card.of(Symbol.ACE, Type.HEART)));
		Map<Player, BettingMoney> bettingMoney = new HashMap<>();
		bettingMoney.put(pobi, BettingMoney.valueOf(10000));
		bettingMoney.put(stitch, BettingMoney.valueOf(5000));
		PlayersBettingMoney playersBettingMoney = new PlayersBettingMoney(bettingMoney);

		assertThat(playersBettingMoney.getBettingMoneyFrom(pobi)).isEqualTo(BettingMoney.valueOf(10000));
	}
}
