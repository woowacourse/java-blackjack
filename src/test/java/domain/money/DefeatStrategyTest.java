package domain.money;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *    class description
 *
 *    @author AnHyungJu, ParkDooWon
 */
class DefeatStrategyTest {
	@Test
	void calculate_MinusOneTimes_GivenBettingMoney() {
		assertThat(new DefeatStrategy().calculate(Money.of("10000"))).isEqualTo(-10000D);
	}
}