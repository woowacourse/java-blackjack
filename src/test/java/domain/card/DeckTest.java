package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

	@DisplayName("Deck이 생성되는지 확인")
	@Test
	void create_Deck() {
		assertThat(new Deck(new ArrayList<>())).isInstanceOf(Deck.class);
	}

	@DisplayName("Deck에 들어간 카드가 pop()하면 나오는지 확인")
	@Test
	void pop_Card_From_Deck() {
		Card card = new Card(Symbol.ACE, Type.CLOVER);
		Deck deck = new Deck(Collections.singletonList(card));
		assertThat(deck.pop()).isEqualTo(card);
	}
}
