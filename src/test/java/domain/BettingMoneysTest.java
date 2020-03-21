package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class BettingMoneysTest {
	@Test
	void constructor() {
		assertThat(new BettingMoneys(new HashMap<>())).isInstanceOf(BettingMoneys.class);
	}

	@Test
	void get_배팅을_안한_경우() {
		assertThat(new BettingMoneys(new HashMap<>()).get(new Player("a"))).isEqualTo(BettingMoney.ZERO);
	}

	@Test
	void get_배팅을_한_경우() {
		Player player = new Player("a");
		Map<Player, BettingMoney> bettingMoneys = new HashMap<>();
		bettingMoneys.put(player, new BettingMoney(2000));
		assertThat(new BettingMoneys(bettingMoneys).get(player)).isEqualTo(new BettingMoney(2000));
	}
}
