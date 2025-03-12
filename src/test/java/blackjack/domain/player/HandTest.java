package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class HandTest {

    @Test
    @DisplayName("배팅 금액으로 객체를 생성한다")
    void 배팅_금액으로_객체를_생성한다() {
        int batAmount = 5_000;
        assertThatNoException().isThrownBy(() -> new Hand(batAmount));
    }

    @Test
    @DisplayName("배수의 따라 계산해 반환한다")
    void 배수의_따라_계산해_반환한다() {
        int batAmount = 5_000;
        Hand hand = new Hand(batAmount);

        int result = hand.calculateBetAmountByMultiplier(1.5);

        assertThat(result)
                .isEqualTo(7_500);
    }
}
