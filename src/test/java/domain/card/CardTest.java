package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    @DisplayName("카드에 적힌 숫자가 2~10이면 숫자를 그대로 반환한다.")
    void cardNumberTest() {
        Card card = new Card(CardNumber.TWO, CardShape.CLUB);
        int number = card.getBaseScore();

        Assertions.assertThat(number).isEqualTo(2);
    }

    @Test
    @DisplayName("카드에 적힌 숫자가 J,Q,K이면 10을 반환된다.")
    void cardAlphabetTest() {
        Card card = new Card(CardNumber.JACK, CardShape.CLUB);
        int number = card.getBaseScore();

        Assertions.assertThat(number).isEqualTo(10);
    }

    @Test
    @DisplayName("카드에 적힌 숫자가 A이면 1또는 11을 반환한다,")
    void cardAceTest() {
        Card card = new Card(CardNumber.ACE, CardShape.CLUB);
        int number = card.getBaseScore();

        Assertions.assertThat(number == 1|| number == 11).isEqualTo(true);
    }

}
