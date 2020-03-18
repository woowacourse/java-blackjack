package domains.result;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domains.card.Card;
import domains.card.Symbol;
import domains.card.Type;
import domains.user.Dealer;
import domains.user.Hands;
import domains.user.Player;
import domains.user.Players;
import domains.user.money.ProfitMoney;
import domains.user.name.PlayerName;

class ProfitsTest {
	@DisplayName("게임 결과를 받아 수익을 계산")
	@Test
	void construct_GivenGameResult_CreateProfits() {
		Card ace = new Card(Symbol.ACE, Type.CLUB);
		Card six = new Card(Symbol.SIX, Type.DIAMOND);
		Card eight = new Card(Symbol.EIGHT, Type.SPADE);
		Card king = new Card(Symbol.KING, Type.HEART);

		Hands winHands = new Hands(new ArrayList<>(Arrays.asList(ace, king)));
		Hands loseHands = new Hands(new ArrayList<>(Arrays.asList(six, ace)));
		Hands dealerHands = new Hands(new ArrayList<>(Arrays.asList(eight, king)));

		Player ddoring = new Player(new PlayerName("또링"), "1000", winHands);
		Player smallbear = new Player(new PlayerName("작은곰"), "50000", loseHands);
		Players players = new Players(Arrays.asList(ddoring, smallbear));
		Dealer dealer = new Dealer(dealerHands);
		GameResult gameResult = new GameResult(players, dealer);

		Map<Player, ProfitMoney> playerProfits = new Profits(gameResult).getPlayerProfits();

		Map<Player, ProfitMoney> expectedProfits = new HashMap<>();
		expectedProfits.put(ddoring, new ProfitMoney(1500));
		expectedProfits.put(smallbear, new ProfitMoney(-50000));

		assertThat(playerProfits).isEqualTo(expectedProfits);
	}
}
