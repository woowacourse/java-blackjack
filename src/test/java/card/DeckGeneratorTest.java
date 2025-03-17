package card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeckGeneratorTest {

    @Test
    void _52장으로_구성된_덱을_생성한다() {
        // given
        final int expected = 52;

        // when
        Deck deck = DeckGenerator.generateDeck();

        // then
        Assertions.assertThat(deck.size()).isEqualTo(expected);
    }
}
