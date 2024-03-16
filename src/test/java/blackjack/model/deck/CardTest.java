package blackjack.model.deck;

import static blackjack.model.deck.Score.ACE;
import static blackjack.model.deck.Score.NINE;
import static blackjack.model.deck.Score.TWO;
import static blackjack.model.deck.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    @DisplayName("카드가 정상적으로 생성된다.")
    void createCardWithoutException() {
        assertDoesNotThrow(() -> new Card(Shape.DIA, NINE));
    }

    @Test
    @DisplayName("카드의 모양이 Ace이면 맞다고 확인한다.")
    void announceTrueWhenCardShapeIsAce() {
        Card card = new Card(SPADE, ACE);

        assertThat(card.isAce()).isTrue();
    }

    @Test
    @DisplayName("카드의 모양이 Ace가 아니면 아니라고 확인한다.")
    void announceFalseWhenCardShapeIsNotAce() {
        Card card = new Card(SPADE, TWO);

        assertThat(card.isAce()).isFalse();
    }
}
