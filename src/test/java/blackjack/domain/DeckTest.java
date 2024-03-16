package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("덱에 카드 하나를 뽑는다.")
    void drawTest() {
        Deck deck = new Deck();
        int firstSize = deck.size();

        deck.drawCard();

        assertThat(deck.size()).isEqualTo(firstSize - 1);
    }
}
