package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.RevenuePolicy.NORMAL_WIN;
import static org.assertj.core.api.Assertions.assertThat;

class RevenuePolicyTest {
    @DisplayName("플레이어가 블랙잭이 아니면서 승리한 경우, NORMAL_WIN 정책을 반환할 수 있다.")
    @Test
    void winningWithNotBlackjack() {
        // given
        GameResult gameResult = GameResult.WIN;
        final boolean isBlackjack = false;

        // when
        RevenuePolicy policy = RevenuePolicy.from(gameResult, isBlackjack);

        // then
        assertThat(policy).isSameAs(NORMAL_WIN);
    }
}
