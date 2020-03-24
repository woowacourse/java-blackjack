package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class ProfitsTest {
	@Test
	void calculate() {
		Players players = PlayersFactory.create("a,b,c,d");
		Map<Player, BettingMoney> bettingMoneys = new HashMap<>();
		players.forEach(player -> bettingMoneys.put(player, new BettingMoney(1000)));
		Dealer dealer = new Dealer();
		assertThat(Profits.calculate(dealer, players, new BettingMoneys(bettingMoneys))).isInstanceOf(Profits.class);
	}
}
