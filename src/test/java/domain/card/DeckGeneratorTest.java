package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DeckGeneratorTest {

    @Test
    void 블랙잭에서_사용하는_덱은_52장이다() {
        // given
        Deck deck = DeckGenerator.generateDeck();

        // when
        final int size = deck.size();

        // then
        Assertions.assertThat(size).isEqualTo(52);
    }
}