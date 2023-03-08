package domain;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeckTest {

    @Test
    void initDeck_카드갯수_테스트() {
        //given, when
        int size = Deck.getDeck().size();
        int expected = 51;
        //then
        assertThat(size).isEqualTo(expected);
    }
}