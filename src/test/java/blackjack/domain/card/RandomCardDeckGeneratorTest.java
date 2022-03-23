package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.generator.RandomCardDeckGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomCardDeckGeneratorTest {

    @Test
    @DisplayName("52장의 카드를 포함하고 있는 카드덱을 정상 생성한다.")
    void createCardDeck() {
        final RandomCardDeckGenerator cardDeckGenerator = new RandomCardDeckGenerator();
        final int expected = 52;

        final List<Card> cards = cardDeckGenerator.generate();
        final int actual = cards.size();

        assertThat(actual).isEqualTo(expected);
    }
}
