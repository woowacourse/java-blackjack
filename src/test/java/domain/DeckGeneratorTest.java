package domain;

import java.util.List;
import java.util.Random;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckGeneratorTest {

    @DisplayName("카드 목록을 구성한다.")
    @Test
    void 카드_목록을_구성한다() {

        // given
        final DeckGenerator deckGenerator = new DeckGenerator();

        // when & then
        Assertions.assertThatCode(deckGenerator::generate)
                .doesNotThrowAnyException();
    }

    @DisplayName("카드를 셔플한다.")
    @Test
    void 카드를_셔플한다() {

        // given
        final DeckGenerator deckGenerator = new DeckGenerator();
        final List<Card> cards = deckGenerator.generate();
        final List<Card> previousCards = List.copyOf(cards);
        final Random random = new Random(123);

        // when
        deckGenerator.shuffle(cards, random);

        // then
        Assertions.assertThat(previousCards).isNotEqualTo(cards);
    }
}
