package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardNumberTest {

    @Test
    @DisplayName("카드 넘버를에 따른 점수를가지고 온다.")
    void getScore_getScoreFromCardNumber() {
        assertThat(CardNumber.FIVE.getScore()).isEqualTo(5);
    }
}
