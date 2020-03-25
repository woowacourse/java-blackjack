package domain.betting;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.Test;

import domain.user.Player;

public class FinalMoneyTest {

	@Test
	void compareTest() {
		FinalMoney finalMoney = new FinalMoney(3000);
		Player player = new Player("둔덩", "1000");

		assertThat(finalMoney.compare(player)).isEqualTo(2000);
	}
}
