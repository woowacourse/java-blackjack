package domain;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Symbol;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParticipantInterfaceCardsTest {
	@Test
	void toStringTest() {
		ParticipantCards cards = new ParticipantCards();
		cards.add(new Card(Symbol.ACE, Shape.다이아몬드));
		cards.add(new Card(Symbol.EIGHT, Shape.스페이드));

		assertThat(cards.toString()).isEqualTo("1다이아몬드, 8스페이드");
	}
}
