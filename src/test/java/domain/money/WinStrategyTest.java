package domain.money;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *    class description
 *
 *    @author AnHyungJu, ParkDooWon
 */
class WinStrategyTest {
	@Test
	void calculate_OneTimes_GivenBettingMoney() {
		assertThat(new WinStrategy().calculate(Money.of("10000"))).isEqualTo(10000D);
	}
}