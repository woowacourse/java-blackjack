package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ResultTest {
	@Test
	public void calculateTest() {
		Player player = new Player("pobi");

		assertThat(player.getGameResult().calculateWithAce(player)).isEqualTo(17);
	}
}

//Arrays.asList(
//			new Card(CardSuit.CLOVER, CardNumber.SEVEN),
//			new Card(CardSuit.CLOVER, CardNumber.TEN))
//
