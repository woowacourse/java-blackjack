package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckGeneratorTest {

    CardDeckGenerator cardDeckGenerator = new CardDeckGenerator();

    @Test
    @DisplayName("트럼프 카드 52장을 생성할 수 있다.")
    void canMakeShuffled() {
        CardDeck cardDeck = cardDeckGenerator.makeShuffled();
        assertThat(cardDeck.getSize()).isEqualTo(52);
    }
}