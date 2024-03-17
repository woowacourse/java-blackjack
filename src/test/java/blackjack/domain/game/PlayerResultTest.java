package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게임 결과")
public class PlayerResultTest {
    @Test
    @DisplayName("블랙잭은 값의 1.5배를 반환한다.")
    void blackjackProfit() {
        // given & when & then
        assertThat(PlayerResult.BLACKJACK_WIN.additionalProfit(100))
                .isEqualTo((long) (100 * 1.5));
    }

    @Test
    @DisplayName("승리는 값 그대로 반환한다.")
    void winProfit() {
        // given & when & then
        assertThat(PlayerResult.WIN.additionalProfit(100))
                .isEqualTo(100L);
    }

    @Test
    @DisplayName("push는 0을 반환한다.")
    void pushProfit() {
        // given & when & then
        assertThat(PlayerResult.PUSH.additionalProfit(100))
                .isEqualTo(0L);
    }

    @Test
    @DisplayName("lose는 값의 -1배를 반환한다.")
    void loseProfit() {
        // given & when & then
        assertThat(PlayerResult.BLACKJACK_WIN.additionalProfit(100))
                .isEqualTo(100 * -1);
    }
}
