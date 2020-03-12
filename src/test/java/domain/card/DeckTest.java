package domain.card;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

	@Test
	void create_Deck() {
		assertThat(new Deck(new ArrayList<>())).isInstanceOf(Deck.class);
	}

	@Test
	void pop_Card_From_Deck() {
		Card card = new Card(Symbol.ACE, Type.CLOVER);
		Deck deck = new Deck(Collections.singletonList(card));
		assertThat(deck.pop()).isEqualTo(card);
	}
}
