package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuit;

public class ResultTest {
	@Test
	public void calculateTest() {
		Player player = new Player("pobi");

		assertThat(Result.calculate(player)).isEqualTo(17);
	}
}

//Arrays.asList(
//			new Card(CardSuit.CLOVER, CardNumber.SEVEN),
//			new Card(CardSuit.CLOVER, CardNumber.TEN))
//
