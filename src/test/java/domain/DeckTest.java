package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeckTest {

    @Test
    void initDeck_카드갯수_테스트() {
        //given
        Deck deck = new Deck();
        //when
        int size = deck.getDeck().size();
        //then
        Assertions.assertThat(size).isEqualTo(52);
    }
}