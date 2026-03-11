package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 덱은_총52장의_카드를_가진다() {
        Deck deck = new Deck();
        assertThat(deck.getCount()).isEqualTo(52);
    }

    @Test
    void 카드는_맨_뒤에서부터_나눠준다() {
        Deck deck = new Deck();
        Card lastCard = deck.getLastCard();
        Card drawCard = deck.draw();
        assertThat(drawCard).isEqualTo(lastCard);
    }

}
