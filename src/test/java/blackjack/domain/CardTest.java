package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardFigure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @DisplayName("예외 테스트: 생성자에 Null이 들어온 경우")
    @Test
    void validateNotNullTest() {
        assertThatThrownBy(() -> {
            new Card(null, CardFigure.HEART);
        }).isInstanceOf(NullPointerException.class)
                .hasMessageContaining("생성자에 Null이 들어올 수 없습니다.");

        assertThatThrownBy(() -> {
            new Card(CardNumber.TWO, null);
        }).isInstanceOf(NullPointerException.class)
                .hasMessageContaining("생성자에 Null이 들어올 수 없습니다.");
    }
}