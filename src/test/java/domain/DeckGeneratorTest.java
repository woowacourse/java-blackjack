package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckGeneratorTest {

    @DisplayName("카드 목록을 구성한다.")
    @Test
    void 카드_목록을_구성한다() {

        // given
        DeckGenerator deckGenerator = new DeckGenerator();

        // when & then
        Assertions.assertThatCode(deckGenerator::generate)
                .doesNotThrowAnyException();

    }
}
