package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

	@Test
	@DisplayName("pickCard 에서 카드를 잘 뽑는지")
	void PickCard_Return_Only_One() {
		assertThat(CardDeck.pick()).isInstanceOf(Card.class);
	}

}
