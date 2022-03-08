import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

	@Test
	@DisplayName("pickCard 에서 리턴된 값이 하나인지")
	void PickCard_Return_Only_One() {
		CardDeck cardDeck = new CardDeck();
		assertThat(cardDeck.pickCard().size()).isEqualTo(1);
	}

}
