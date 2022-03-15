package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("Deck에서 카드를 한장 빼서 반환한다.")
    void pickCardFromDeck() {
        Deck deck = new Deck();

        assertThat(deck.pickCard()).isNotNull();
    }
}
