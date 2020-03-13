package domain.card;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class CardDividerTest {

	@Test
	void divideTest() {
		CardDivider cardDivider = new CardDivider(Collections.singletonList(0));
		assertThat(cardDivider.divide()).isEqualTo(new Card(Symbol.DIAMOND, Type.ACE));
	}

	@Test
	void divideThrownErrorTest() {
		CardDivider cardDivider = new CardDivider(Arrays.asList());
		assertThatThrownBy(() -> cardDivider.divide())
			.isInstanceOf(NoSuchElementException.class);
	}
}
