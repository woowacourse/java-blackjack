package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {

	private Card card;

	@BeforeEach
	void setUp() {
		card = new Card(Symbol.ACE, Type.DIAMOND);
	}

	@Test
	void isAce_Return_True_When_Ace_Clover() {
		assertTrue(card.isAce());
	}

	@Test
	void getPoint() {
		assertThat(card.getScore()).isEqualTo(1);
	}
}
