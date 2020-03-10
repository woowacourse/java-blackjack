package domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class CardsTest {
	@Test
	void calculateScoreTest() {
		Cards cards = new Cards(Arrays.asList(new Card(Symbol.CLOVER, Type.ONE)));
		assertThat(cards.calculateScore()).isEqualTo(11);
	}
}
