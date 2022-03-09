package blackjack;

import static org.assertj.core.api.Assertions.*;

import blackjack.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

	@Test
	@DisplayName("pickCard 에서 카드를 잘 뽑는지")
	void PickCard_Return_Only_One() {
		CardDeck cardDeck = new CardDeck();
		assertThat(cardDeck.pickCard()).isInstanceOf(Card.class);
	}

}
