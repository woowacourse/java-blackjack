package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DeckTest {
    
    @Test
    void 덱은_총52장의_카드를_가진다() {
        Deck deck = new Deck();
        assertThat(deck.getCount()).isEqualTo(52);
        assertThat(deck.getCount()).isNotEqualTo(51);
        assertThat(deck.getCount()).isNotEqualTo(53);
    }

}
