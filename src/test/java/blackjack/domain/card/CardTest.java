package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    @DisplayName("올바르게 카드의 Number와 Shape를 반환한다.")
    @Test
    void getSymbolAndShapeTest() {
        Number number = Number.EIGHT;
        Shape shape = Shape.CLOVER;
        Card card = new Card(number, shape);
        assertThat(card.getSymbol() + card.getShape()).isEqualTo("8클로버");
    }

    @DisplayName("올바르게 점수를 반환한다.")
    @Test
    void getScoreTest() {
        Number number = Number.FIVE;
        Shape shape = Shape.CLOVER;
        Card card = new Card(number, shape);
        assertThat(card.getScore()).isEqualTo(5);
    }

    @DisplayName("에이스를 판별한다")
    @Test
    void isAceTest() {
        Number number = Number.ACE;
        Shape shape = Shape.HEART;
        Card card = new Card(number, shape);
        assertTrue(card.isAce());
    }
}
