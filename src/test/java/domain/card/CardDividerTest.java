package domain.card;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class CardDividerTest {

	@Test
	void divideTest() {
		CardDivider cardDivider = new CardDivider(Arrays.asList(0,1,2));

		assertThat(cardDivider.divide()).isEqualTo(new Card(Symbol.DIAMOND, Type.ACE));
	}
}
