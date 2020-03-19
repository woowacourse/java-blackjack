package domain.betting;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.Test;

public class FinalMoneyTest {

	@Test
	void compareTest() {
		FinalMoney finalMoney = new FinalMoney(3000);
		Money money = Money.from(1000);

		assertThat(finalMoney.compare(money)).isEqualTo(2000);
	}
}
