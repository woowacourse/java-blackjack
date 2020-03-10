package domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

	@DisplayName("ACE를 포함하지 않는 경우")
	@Test
	void calculateScoreTest() {
		Cards cards = new Cards(Arrays.asList(new Card(Symbol.SPADE, Type.KING),
			new Card(Symbol.SPADE, Type.JACK)));

		assertThat(cards.calculateScore()).isEqualTo(20);
	}

	@DisplayName("ACE를 포함하는 경우, 11인 경우")
	@Test
	void calculateScoreTest2() {
		Cards cards = new Cards(Arrays.asList(new Card(Symbol.CLOVER, Type.ACE),
			new Card(Symbol.CLOVER, Type.TEN)));
		assertThat(cards.calculateScore()).isEqualTo(21);
	}

	@DisplayName("ACE를 포함하는 경우, 12인 경우")
	@Test
	void calculateScoreTest3() {
		Cards cards = new Cards(Arrays.asList(new Card(Symbol.CLOVER, Type.ACE),
			new Card(Symbol.CLOVER, Type.FIVE),
			new Card(Symbol.CLOVER, Type.SIX)));
		assertThat(cards.calculateScore()).isEqualTo(12);
	}

	@DisplayName("ACE가 2개인 경우")
	@Test
	void calculateScoreTest4() {
		Cards cards = new Cards(Arrays.asList(new Card(Symbol.CLOVER, Type.ACE),
			new Card(Symbol.CLOVER, Type.ACE)));
		assertThat(cards.calculateScore()).isEqualTo(12);
	}
}
