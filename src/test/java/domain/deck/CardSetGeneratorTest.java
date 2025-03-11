package domain.deck;

import java.util.List;
import java.util.Random;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardSetGeneratorTest {

    @DisplayName("카드 목록을 구성한다.")
    @Test
    void 카드_목록을_구성한다() {

        // given
        final CardSetGenerator cardSetGenerator = new CardSetGenerator();

        // when & then
        Assertions.assertThatCode(cardSetGenerator::generate)
                .doesNotThrowAnyException();
    }

    @DisplayName("카드를 셔플한다.")
    @Test
    void 카드를_셔플한다() {

        // given
        final CardSetGenerator cardSetGenerator = new CardSetGenerator();
        final List<Card> cards = cardSetGenerator.generate();
        final List<Card> previousCards = List.copyOf(cards);
        final Random random = new Random(123);

        // when
        cardSetGenerator.shuffle(cards, random);

        // then
        Assertions.assertThat(previousCards).isNotEqualTo(cards);
    }
}
