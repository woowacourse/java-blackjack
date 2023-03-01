package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private static class ZeroIndexGenerator implements CardIndexGenerator {

        @Override
        public int chooseIndex(int deckSize) {
            return 0;
        }
    }

    @DisplayName("카드를 뽑는다")
    @Test
    void pickCard() {
        Deck deck = Deck.from(new ZeroIndexGenerator());

        Card card1 = deck.pickCard();
        Card card2 = deck.pickCard();

        assertThat(card1.getSuit()).isEqualTo(Suits.values()[0]);
        assertThat(card1.getDenomination()).isEqualTo(Denomination.values()[0]);

        assertThat(card2.getSuit()).isEqualTo(Suits.values()[0]);
        assertThat(card2.getDenomination()).isEqualTo(Denomination.values()[1]);
    }
}
