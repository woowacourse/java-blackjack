package blackjack.domain;

import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @DisplayName("카드 덱 2개 뽑기")
    @Test
    void pickInitialCards() {
        Deck deck = new Deck();
        assertThat(deck.pickInitialCards().size()).isEqualTo(2);
    }

    @DisplayName("카드 덱 1개 뽑기")
    @Test
    void pickSingleCard() {
        Deck deck = new Deck();
        assertThat(deck.pickSingleCard()).isNotNull();
    }
}