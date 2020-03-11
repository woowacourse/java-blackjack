package domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    @Test
    void create() {
        assertThat(new Cards()).isInstanceOf(Cards.class);
    }

    @Test
    void addCard() {
        Cards cards = new Cards();
        assertThat(cards.getSize()).isEqualTo(0);

        cards.addCard(Card.of("스페이드", "K"));
        assertThat(cards.getSize()).isEqualTo(1);
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
    @Test
    void hasAce() {
        Cards cards = new Cards();
        cards.addCard(Card.of("스페이드", "K"));
        assertThat(cards.hasAce()).isFalse();

        cards.addCard(Card.of("스페이드", "A"));
        assertThat(cards.hasAce()).isTrue();
    }
    @Test
    void isBust() {
        Cards cards = new Cards();
        cards.addCard(Card.of("스페이드", "K"));
        cards.addCard(Card.of("스페이드", "Q"));
        assertThat(cards.isBust()).isFalse();

        cards.addCard(Card.of("스페이드", "2"));
        assertThat(cards.isBust()).isTrue();

    }
}
