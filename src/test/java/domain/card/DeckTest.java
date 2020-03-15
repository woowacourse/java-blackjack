package domain.card;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

	@Test
	void create_Deck() {
		assertThat(Deck.of(new ArrayList<>())).isInstanceOf(Deck.class);
	}

	@Test
	void pop_Card_From_Deck() {
		Card card = new Card(Symbol.ACE, Type.CLOVER);
		Deck deck = Deck.of(Collections.singletonList(card));
		assertThat(deck.pop()).isEqualTo(card);
	}
}
