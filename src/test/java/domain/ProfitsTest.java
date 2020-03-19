package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProfitsTest {
	@Test
	void calculate() {
		Players players = PlayersFactory.create("a,b,c,d");
		BettingMoneys bettingMoneys = new BettingMoneys();
		Dealer dealer = new Dealer();
		players.forEach(player -> bettingMoneys.betting(player, new BettingMoney(1000)));
		assertThat(Profits.calculate(dealer, players, bettingMoneys)).isInstanceOf(Profits.class);
	}
}
