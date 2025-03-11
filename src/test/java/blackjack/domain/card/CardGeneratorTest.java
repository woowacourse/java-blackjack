package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardGeneratorTest {

    CardGenerator cardGenerator = new CardGenerator();

    @Test
    @DisplayName("트럼프 카드 52장을 생성할 수 있다.")
    void canMakeShuffled() {
        List<Card> cards = cardGenerator.makeShuffled();
        assertThat(cards).hasSize(52);
    }
}