package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardContentsTest {

    @Test
    @DisplayName("카드 숫자를 잘 제공한다")
    void getCardNumber_success() {
        //given
        CardContents ACE = CardContents.A;
        //when
        String cardNumber = ACE.getNumber();
        //then
        Assertions.assertThat(cardNumber).isEqualTo("A");
    }

    @Test
    @DisplayName("카드 점수를 잘 제공한다")
    void getCardScore_success() {
        //given
        CardContents tenCard = CardContents.TEN;
        //when
        int score = tenCard.getScore();
        //then
        Assertions.assertThat(score).isEqualTo(10);
    }

}
