package card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    private static final int FIRST_POSITION_INDEX = 0;

    @DisplayName("해당하는 Number와 일치하는지 여부를 return 한다")
    @Test
    void isSameCardNumberTest() {
        Card card = new Card(CardNumber.ACE, CardPattern.DIA_PATTERN);

        Assertions.assertThat(card.isAceCard()).isTrue();
    }

    @DisplayName("default 인 카드 점수를 가져온다.")
    @Test
    void getDefaultCardScore() {
        Card card = new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN);

        int cardNumber = CardNumber.ACE.getCardNumber(FIRST_POSITION_INDEX);

        Assertions.assertThat(card.getDefaultCardScore()).isEqualTo(cardNumber);
    }

    @DisplayName("카드 Number가 내가 찾으려는 CardNumber와 일치하는 경우 true를 준다.")
    @Test
    void isSameCardNumber() {
        Card card = new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN);

        Assertions.assertThat(card.isAceCard()).isTrue();
    }

    @DisplayName("카드 Number가 내가 찾으려는 CardNumber와 일치하지 않는 경우 false를 준다.")
    @Test
    void isNotSameCardNumber() {
        Card card = new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN);

        Assertions.assertThat(card.isAceCard()).isFalse();
    }
}
