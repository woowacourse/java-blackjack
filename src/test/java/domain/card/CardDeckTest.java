package domain.card;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

	@DisplayName("카드덱에서 순차적으로 카드를 가져올수 있다.")
	@Test
	void cardDrawTest() {
		CardDeck cardDeck = new CardDeck(Collections.singletonList(0));
		assertThat(cardDeck.draw()).isEqualTo(new Card(Symbol.DIAMOND, Type.ACE));
	}
}
