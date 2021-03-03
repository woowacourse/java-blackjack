package blackjack.domain.carddeck;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.carddeck.CardNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardNumberTest {

    @Test
    @DisplayName("카드 숫자별 점수 테스트")
    void testCarNumberScore() {
        assertThat(CardNumber.ACE.getNumber()).isEqualTo(1);
        assertThat(CardNumber.TWO.getNumber()).isEqualTo(2);
        assertThat(CardNumber.THREE.getNumber()).isEqualTo(3);
        assertThat(CardNumber.FOUR.getNumber()).isEqualTo(4);
        assertThat(CardNumber.FIVE.getNumber()).isEqualTo(5);
        assertThat(CardNumber.SIX.getNumber()).isEqualTo(6);
        assertThat(CardNumber.SEVEN.getNumber()).isEqualTo(7);
        assertThat(CardNumber.EIGHT.getNumber()).isEqualTo(8);
        assertThat(CardNumber.NINE.getNumber()).isEqualTo(9);
        assertThat(CardNumber.TEN.getNumber()).isEqualTo(10);
        assertThat(CardNumber.JACK.getNumber()).isEqualTo(10);
        assertThat(CardNumber.QUEEN.getNumber()).isEqualTo(10);
        assertThat(CardNumber.KING.getNumber()).isEqualTo(10);
    }
}
