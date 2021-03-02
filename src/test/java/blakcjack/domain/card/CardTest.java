package blakcjack.domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
	@Test
	void create() {
		final Card card = Card.of(CardSymbol.HEART, CardNumber.ACE);
		assertThat(card).isEqualTo(Card.of(CardSymbol.HEART, CardNumber.ACE));
	}
}
