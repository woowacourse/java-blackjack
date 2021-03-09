package blackjack.domain.carddeck;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberTest {

    @Test
    @DisplayName("카드 숫자별 점수 테스트")
    void testCarNumberScore() {
        assertThat(Number.ACE.getNumber()).isEqualTo(11);
        assertThat(Number.TWO.getNumber()).isEqualTo(2);
        assertThat(Number.THREE.getNumber()).isEqualTo(3);
        assertThat(Number.FOUR.getNumber()).isEqualTo(4);
        assertThat(Number.FIVE.getNumber()).isEqualTo(5);
        assertThat(Number.SIX.getNumber()).isEqualTo(6);
        assertThat(Number.SEVEN.getNumber()).isEqualTo(7);
        assertThat(Number.EIGHT.getNumber()).isEqualTo(8);
        assertThat(Number.NINE.getNumber()).isEqualTo(9);
        assertThat(Number.TEN.getNumber()).isEqualTo(10);
        assertThat(Number.JACK.getNumber()).isEqualTo(10);
        assertThat(Number.QUEEN.getNumber()).isEqualTo(10);
        assertThat(Number.KING.getNumber()).isEqualTo(10);
    }
}
