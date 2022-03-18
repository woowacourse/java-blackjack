package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckGeneratorTest {

    @Test
    @DisplayName("52장의 카드를 포함하고 있는 카드덱을 정상 생성한다.")
    void createCardDeck() {
        assertThatCode(() -> {
            CardDeckGenerator cardDeckGenerator = new TestDeckGenerator();
            cardDeckGenerator.createCardDeck();
        }).doesNotThrowAnyException();
    }
}
