package domains.result;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domains.card.Card;
import domains.card.Symbol;
import domains.card.Type;
import domains.user.Dealer;
import domains.user.Hands;
import domains.user.Player;
import domains.user.Players;
import domains.user.User;
import domains.user.money.BettingMoney;
import domains.user.money.ProfitMoney;
import domains.user.name.PlayerName;

class ProfitsTest {
	@DisplayName("게임 결과를 받아 수익을 계산")
	@ParameterizedTest
	@MethodSource("gameResultData")
	void construct_GivenGameResult_CreateProfits(Players players, Map<Player, ResultType> gameResult,
		Map<Player, BettingMoney> playersBettingMoney) {
		Map<Player, ProfitMoney> playerProfits = new Profits(gameResult, playersBettingMoney).getPlayerProfits();

		Map<Player, ProfitMoney> expectedProfits = new HashMap<>();
		expectedProfits.put(players.getPlayers().get(0), new ProfitMoney(1500));
		expectedProfits.put(players.getPlayers().get(1), new ProfitMoney(-50000));
		expectedProfits.put(players.getPlayers().get(2), new ProfitMoney(0));

		assertThat(playerProfits).isEqualTo(expectedProfits);
	}

	static Stream<Arguments> gameResultData() {
		Card ace = new Card(Symbol.ACE, Type.CLUB);
		Card six = new Card(Symbol.SIX, Type.DIAMOND);
		Card eight = new Card(Symbol.EIGHT, Type.SPADE);
		Card king = new Card(Symbol.KING, Type.HEART);

		Hands winHands = new Hands(new ArrayList<>(Arrays.asList(ace, king)));
		Hands loseHands = new Hands(new ArrayList<>(Arrays.asList(six, ace)));
		Hands drawHands = new Hands(new ArrayList<>(Arrays.asList(eight, king)));
		Hands dealerHands = new Hands(new ArrayList<>(Arrays.asList(eight, king)));

		Player ddoring = new Player(new PlayerName("또링"), winHands);
		Player smallbear = new Player(new PlayerName("작은곰"), loseHands);
		Player havi = new Player(new PlayerName("하비"), drawHands);

		Players players = new Players(Arrays.asList(ddoring, smallbear, havi));

		Map<User, BettingMoney> playersBettingMoney = new HashMap<>();
		playersBettingMoney.put(ddoring, new BettingMoney("1000"));
		playersBettingMoney.put(smallbear, new BettingMoney("50000"));
		playersBettingMoney.put(havi, new BettingMoney("50000"));

		Dealer dealer = new Dealer(dealerHands);
		Map<Player, ResultType> gameResult = GameResult.create(players, dealer);

		return Stream.of(
			Arguments.of(players, gameResult, playersBettingMoney)
		);
	}
}
