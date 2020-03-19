package domain.betting;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.user.Player;
import domain.user.User;

public class BettingBoardTest {
	@Test
	void createTest() {
		Player player = new Player("둔덩");
		Money money = Money.from(1000);

		List<User> users = Arrays.asList(new Player("둔덩"));
		List<Money> moneys = Arrays.asList(Money.from(1000));

		BettingBoard bettingBoard = new BettingBoard(Arrays.asList(player), Arrays.asList(money));
		assertThat(bettingBoard.get(player)).isEqualTo(money);
	}
}
