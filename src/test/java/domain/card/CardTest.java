package domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {

	@Test
	void create_Card() {
		assertThat(new Card(Symbol.ACE, Type.DIAMOND)).isInstanceOf(Card.class);
	}
}
