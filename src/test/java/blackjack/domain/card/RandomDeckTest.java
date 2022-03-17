package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomDeckTest {

    private static final int CARD_SIZE = 52;

    @Test
    @DisplayName("Deck에서 뽑히는 모든 카드는 중복되지 않는다.")
    void pick_card() {
        Deck deck = new RandomDeck();
        Set<Card> cards = new HashSet<>();
        for (int i = 0; i < CARD_SIZE; i++) {
            cards.add(deck.pick());
        }

        assertAll(
                () -> assertThat(cards.size()).isEqualTo(CARD_SIZE),
                () -> assertThatThrownBy(deck::pick).isInstanceOf(IllegalStateException.class)
        );
    }
}
