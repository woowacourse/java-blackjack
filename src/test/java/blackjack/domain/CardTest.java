package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.component.CardNumber;
import blackjack.domain.card.component.CardFigure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardTest {
    @DisplayName("생성한 카드의 Equals 확인")
    @Test
    void equalsTest() {
        Card card = new Card(CardNumber.TWO, CardFigure.HEART);
        Card expected = new Card(CardNumber.TWO, CardFigure.HEART);
        assertThat(card).isEqualTo(expected);
    }
    
    @DisplayName("카드가 에이스인지 확인")
    @ParameterizedTest
    @CsvSource(value = {"ACE,true", "FOUR,false"})
    void hasTest(CardNumber cardNumber, boolean expected) {
        Card card = new Card(cardNumber, CardFigure.CLOVER);
        assertThat(card.isAce()).isEqualTo(expected);
    }
}