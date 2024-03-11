package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardTest {
    @DisplayName("올바르게 카드의 Number와 Shape를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "EIGHT, CLOVER, 8클로버",
    })
    void getSymbolAndShape(Number number, Shape shape, String expected) {
        Card card = new Card(number, shape);
        assertThat(card.getSymbol() + card.getShape()).isEqualTo(expected);
    }

    @DisplayName("올바르게 점수를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "FIVE, HEART, 5",
    })
    void getScore(Number number, Shape shape, Integer expected) {
        Card card = new Card(number, shape);
        assertThat(card.getScore()).isEqualTo(expected);
    }

    @DisplayName("에이스를 판별한다")
    @ParameterizedTest
    @CsvSource({
            "ACE, DIAMOND"
    })
    void isAce(Number number, Shape shape) {
        Card card = new Card(number, shape);
        assertTrue(card.isAce());
    }
}
