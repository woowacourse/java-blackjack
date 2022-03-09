package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeckTest {

    @Test
    @DisplayName("52개의 카드를 섞어서 덱에 넣는다.")
    public void deckCreateTest() {
        Deck deck = new Deck(new CardGenerator());

        assertThat(deck.getCards().size()).isEqualTo(52);
    }
}
