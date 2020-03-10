package blackjack.player.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardTest {

	@ParameterizedTest
	@CsvSource(value = {"ACE,true", "TWO,false"})
	void isAce(Card.CardNumber cardNumber, boolean result) {
		Card card = new Card(Card.Symbol.HEART, cardNumber);
		assertThat(card.isAce()).isEqualTo(result);
	}
}