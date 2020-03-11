package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CardsTest {
	@Test
	void create() {
		assertThat(new Cards()).isInstanceOf(Cards.class);
	}

	@Test
	void getScore() {
		Cards cards = new Cards();
		cards.addCard(Card.of("스페이드", "K"));
		cards.addCard(Card.of("스페이드", "3"));
		cards.addCard(Card.of("스페이드", "A"));
		assertThat(cards.getScore()).isEqualTo(14);
	}

	@Test
	void isBlackJack() {
		Cards cards = new Cards();
		cards.addCard(Card.of("스페이드", "K"));
		cards.addCard(Card.of("스페이드", "A"));
		assertThat(cards.isBlackJack()).isTrue();
	}

	@Test
	void isLessThan() {
		Cards cards = new Cards();
		cards.addCard(Card.of("스페이드", "K"));
		cards.addCard(Card.of("스페이드", "A"));
		assertThat(cards.isLessThan(20)).isFalse();
	}
}
