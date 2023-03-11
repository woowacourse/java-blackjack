package domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeckTest {

    @Test
    void initDeck_카드갯수_테스트() {
        //given
        Deck deck = new Deck();

        //when
        int size = getSize(deck);
        int expected = 52;

        //then
        assertThat(size).isEqualTo(expected);
    }

    private int getSize(Deck deck) {
        int size = 0;
        while (Deck.pickCard() != null) {
            size++;
        }

        return size;
    }
}
