package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.component.CardNumber;
import blackjack.domain.card.component.CardFigure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


class CardTest {
    @DisplayName("생성한 카드의 Equals 확인")
    @Test
    void equalsTest() {
        Card card = new Card(CardNumber.TWO, CardFigure.HEART);
        Card expected = new Card(CardNumber.TWO, CardFigure.HEART);
        assertThat(card).isEqualTo(expected);
    }
    
    @DisplayName("카드가 해당 번호를 가지고 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"FIVE,true", "FOUR,false"})
    void hasTest(CardNumber cardNumber, boolean expected) {
        Card card = new Card(CardNumber.FIVE, CardFigure.CLOVER);
        assertThat(card.has(cardNumber)).isEqualTo(expected);
    }

    @DisplayName("카드가 해당 번호와 문자를 가지고 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"FIVE, CLOVER, true", "FOUR, CLOVER, false", "FIVE, HEART, false"})
    void hasTest(CardNumber cardNumber, CardFigure cardFigure, boolean expected) {
        Card card = new Card(CardNumber.FIVE, CardFigure.CLOVER);
        assertThat(card.has(cardNumber, cardFigure)).isEqualTo(expected);
    }
}