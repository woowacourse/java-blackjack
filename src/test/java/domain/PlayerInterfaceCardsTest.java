package domain;

import domain.card.PlayerCards;
import domain.card.cardfactory.Card;
import domain.card.cardfactory.Shape;
import domain.card.cardfactory.Symbol;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayerInterfaceCardsTest {
	@Test
	void toStringTest() {
		PlayerCards cards = new PlayerCards();
		cards.add(new Card(Symbol.ACE, Shape.DIAMOND));
		cards.add(new Card(Symbol.EIGHT, Shape.SPADE));

		assertThat(cards.toStringAllCard()).isEqualTo("A다이아몬드, 8스페이드");
	}
}
