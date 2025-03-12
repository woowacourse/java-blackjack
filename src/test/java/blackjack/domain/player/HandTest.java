package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class HandTest {

    @Test
    @DisplayName("배팅 금액으로 객체를 생성한다")
    void 배팅_금액으로_객체를_생성한다() {
        assertThatNoException().isThrownBy(() -> new Hand(5_000));
    }

    @ParameterizedTest
    @CsvSource({
            "5_000, 1.5, 7_500",
            "5_000, -1.5, -7_500",
            "10_000, 1.5, 15_000",
            "10_000, 2.0, 20_000",
            "10_000, 2.5, 25_000",
    })
    @DisplayName("배수의 따라 계산해 반환한다")
    void 배수의_따라_계산해_반환한다(int batAmount, double multiple, int excepted) {
        Hand hand = new Hand(batAmount);

        int result = hand.calculateBetAmountByMultiplier(multiple);

        assertThat(result).isEqualTo(excepted);
    }
}
