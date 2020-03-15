package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CardsTest {
	@Test
	void create() {
		assertThat(new Cards()).isInstanceOf(Cards.class);
	}

	@Test
	void getSize() {
		Cards cards = new Cards();
		assertThat(cards.getSize()).isEqualTo(0);
	}

	@Test
	void add() {
		Cards cards = new Cards();
		cards.add(Card.of("스페이드", "J"));
		assertThat(cards.getSize()).isEqualTo(1);
	}

	@Test
	void getScore() {
		Cards cards = new Cards();
		cards.add(Card.of("스페이드", "K"));
		cards.add(Card.of("스페이드", "3"));
		cards.add(Card.of("스페이드", "A"));
		assertThat(cards.getScore()).isEqualTo(new Score(14));
	}
}
