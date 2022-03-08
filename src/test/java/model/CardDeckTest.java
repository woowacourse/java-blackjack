package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    void popCard() {
        CardDeck cardDeck = new CardDeck();
        List<Card> cards = new ArrayList<>();
        while (!cardDeck.isEmpty()) {
            cards.add(cardDeck.drawCard());
        }
        assertThat(cards.size()).isEqualTo(52);
        assertThat(cards.size()).isEqualTo(Set.copyOf(cards).size());
    }
}
