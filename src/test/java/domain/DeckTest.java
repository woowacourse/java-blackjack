package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("덱을 생성한다.")
    @Test
    void 덱을_생성한다() {

        // given
        final DeckGenerator deckGenerator = new DeckGenerator();

        // when & then
        Assertions.assertThatCode(() -> new Deck(deckGenerator.generate()))
                .doesNotThrowAnyException();
    }
}
