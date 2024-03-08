package domain;

import domain.constant.CardNumber;
import domain.constant.CardType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    @DisplayName("생성자의 인자가 null일 경우 예외를 발생시킨다.")
    void createCard() {
        Assertions.assertThatThrownBy(() -> new Card(null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null을 인자로 받을 수 없습니다.");
    }

    @Test
    @DisplayName("카드의 모양을 반환한다.")
    void getCardType() {
        Assertions.assertThat(new Card(CardType.SPADE, CardNumber.TWO).getCardType())
                .isEqualTo(CardType.SPADE);
    }

    @Test
    @DisplayName("카드의 점수를 반환한다.")
    void getCardNumber() {
        Assertions.assertThat(new Card(CardType.SPADE, CardNumber.TWO).getCardNumber())
                .isEqualTo(CardNumber.TWO);
    }
}
