package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void create() {
        assertThatCode(() -> Deck.create()).doesNotThrowAnyException();
    }

    @DisplayName("CardStack에서 2장의 카드를 반환")
    @Test
    void getTwoCards() {
        final Deck deck = Deck.create();
        assertThat(deck.popTwoCards().size()).isEqualTo(2);
    }

    @DisplayName("CardStack에서 1장의 카드를 반환")
    @Test
    void getSingleCard() {
        final Deck deck = Deck.create();
        assertThat(deck.popSingleCard()).isInstanceOf(Card.class);
    }

    @DisplayName("CardStack이 비어있지 않은 경우에는 False를 반환")
    @Test
    void isEmpty() {
        final Deck deck = Deck.create();
        assertThat(deck.isEmpty()).isFalse();
    }
}