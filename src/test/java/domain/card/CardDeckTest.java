package domain.card;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class CardDeckTest {

	@Test
	void divideTest() {
		CardDeck cardDeck = new CardDeck(Collections.singletonList(0));
		assertThat(cardDeck.draw()).isEqualTo(new Card(Symbol.DIAMOND, Type.ACE));
	}
}
