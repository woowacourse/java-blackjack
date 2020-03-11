package domain.card;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class CardDividerTest {

	@Test
	void divideTest() {
		CardDivider cardDivider = new CardDivider(Collections.singletonList(0));
		assertThat(cardDivider.divide()).isEqualTo(new Card(Symbol.DIAMOND, Type.ACE));
	}
}
