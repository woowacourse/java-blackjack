package domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
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
