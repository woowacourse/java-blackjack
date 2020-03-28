package domain.money;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *    class description
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class BlackjackWinStrategyTest {
	@Test
	void calculate_OnePointFiveTimes_GivenBettingMoney() {
		assertThat(new BlackjackWinStrategy().calculate(Money.of("10000"))).isEqualTo(15000D);
	}
}