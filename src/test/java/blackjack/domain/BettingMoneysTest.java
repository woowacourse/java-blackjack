package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class BettingMoneysTest {
	@Test
	void add_one_betting_money_to_full_size() {
		BettingMoneys bettingMoneys = new BettingMoneys(List.of(new BettingMoney(300), new BettingMoney(200)), 2);
		assertThatThrownBy(() -> bettingMoneys.addBettingMoney(new BettingMoney(100))).isInstanceOf(IllegalArgumentException.class);
	}
}
