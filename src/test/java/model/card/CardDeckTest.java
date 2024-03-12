package model.card;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @DisplayName("덱이 비어있을 때 카드를 뽑으면 예외가 발생한다.")
    @Test
    void validateNotEmpty() {
        CardDeck cardDeck = new CardDeck(CardDeck.createCards());
        for (int i = 0; i < 48; i++) {
            cardDeck.selectRandomCards(CardSize.ONE);
        }
        assertThatThrownBy(() -> cardDeck.selectRandomCards(CardSize.ONE))
                .isInstanceOf(IllegalStateException.class);

    }
}
