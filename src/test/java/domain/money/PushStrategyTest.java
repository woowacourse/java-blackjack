package domain.money;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *    class description
 *
 *    @author AnHyungJu, ParkDooWon
 */
class PushStrategyTest {
	@Test
	void calculate_ZeroTimes_GivenBettingMoney() {
		assertThat(new PushStrategy().calculate(Money.of("10000"))).isEqualTo(0D);
	}
}