package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 카드덱은_52개의_카드로_구성된다() {
        Deck deck1 = Deck.create();

        assertThat(deck1.getCards().size()).isEqualTo(52);
    }

    @Test
    void 카드덱은_무작위의_순서를_가진다() {
        Deck origin = Deck.create();
        Deck deck = Deck.create();

        deck.shuffle();

        assertThat(origin.getCards()).isNotEqualTo(deck.getCards());
    }
}
