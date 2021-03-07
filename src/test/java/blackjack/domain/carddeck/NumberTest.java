package blackjack.domain.carddeck;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberTest {

    @Test
    @DisplayName("카드 숫자별 점수 테스트")
    void testCarNumberScore() {
        assertThat(Number.ACE.getScore()).isEqualTo(11);
        assertThat(Number.TWO.getScore()).isEqualTo(2);
        assertThat(Number.THREE.getScore()).isEqualTo(3);
        assertThat(Number.FOUR.getScore()).isEqualTo(4);
        assertThat(Number.FIVE.getScore()).isEqualTo(5);
        assertThat(Number.SIX.getScore()).isEqualTo(6);
        assertThat(Number.SEVEN.getScore()).isEqualTo(7);
        assertThat(Number.EIGHT.getScore()).isEqualTo(8);
        assertThat(Number.NINE.getScore()).isEqualTo(9);
        assertThat(Number.TEN.getScore()).isEqualTo(10);
        assertThat(Number.JACK.getScore()).isEqualTo(10);
        assertThat(Number.QUEEN.getScore()).isEqualTo(10);
        assertThat(Number.KING.getScore()).isEqualTo(10);
    }
}
