package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    void 카드는_52장의_블랙잭_덱을_만들_수_있다() {
        // when: 덱 생성
        Cards cards = Cards.createDeck();

        // then: 생성된 덱은 52장이다.
        assertThat(cards.size()).isEqualTo(52);
    }

    @Test
    void 카드가_섞여있다() {
        // given
        Cards deck = Cards.createDeck();

        // when
        Cards shuffleDeck = deck.shuffle();

        // then
        assertThat(deck.compareTo(shuffleDeck)).isFalse();
    }
}
