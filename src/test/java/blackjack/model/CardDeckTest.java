package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    void cardGenerate() {
        CardDeck cardDeck = new CardDeck();
        Set<Card> set = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            Card card = cardDeck.selectCard();
            if (set.contains(card)) {
                fail();
            }
            set.add(card);
        }

        assertThat(set).hasSize(52);
        assertThatThrownBy(cardDeck::selectCard).isInstanceOf(IllegalStateException.class);
    }
}
