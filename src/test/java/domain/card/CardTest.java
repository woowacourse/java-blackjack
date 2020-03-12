package domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {

	@Test
	void create_Card() {
		assertThat(new Card(Symbol.ACE, Type.DIAMOND)).isInstanceOf(Card.class);
	}

	@Test
	void isAce_Return_True_When_Ace_Clover() {
		Card card = new Card(Symbol.ACE, Type.DIAMOND);
		assertTrue(card.isAce());
	}

	@Test
	void getPoint() {
		Card card = new Card(Symbol.ACE, Type.DIAMOND);
		assertThat(card.getScore()).isEqualTo(1);
	}
}
