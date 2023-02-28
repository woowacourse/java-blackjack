package blackjack.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    @DisplayName("덱이 생성된다.")
    void createDeck() {
        final Deck deck = new Deck();
        assertThat(deck.drawCard()).isInstanceOf(Card.class);
    }
}
