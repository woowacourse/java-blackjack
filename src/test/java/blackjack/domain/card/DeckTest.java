package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("Deck에서 뽑는 초기 카드는 2장이어야 한다")
    void pickCardFromDeck() {
        Deck deck = new Deck();

        assertThat(deck.pickInitialCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Deck에서 카드를 뽑을 때 비어있는지 검증한다.")
    void validateDeckEmpty() {
        Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {
            deck.pickCard();
        }

        assertThatThrownBy(() -> {
            deck.pickCard();
        }).isInstanceOf(IndexOutOfBoundsException.class);
    }
}
