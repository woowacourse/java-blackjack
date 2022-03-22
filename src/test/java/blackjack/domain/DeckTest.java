package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    void distribute_Card() {
        Deck deck = new Deck();
        assertThat(deck.distributeCards(1).get(0)).isInstanceOf(Card.class);
    }

    @Test
    void check_Deck_size() {
        Deck deck = new Deck();
        deck.distributeCards(52);
        assertThat(deck.getCardsSize()).isEqualTo(0);
    }

    @Test
    void check_Deck_empty_size() {
        Deck deck = new Deck();
        assertThatThrownBy(() -> deck.distributeCards(53))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("덱의 카드가 다 소진되었습니다.");
    }
}
