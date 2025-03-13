package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.RevenuePolicy.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("플레이어가 블랙잭이면서 승리한 경우, BLACKJACK_WIN 정책을 반환할 수 있다.")
    @Test
    void winningWithBlackjack() {
        // given
        GameResult gameResult = GameResult.WIN;
        final boolean isBlackjack = true;

        // when
        RevenuePolicy policy = RevenuePolicy.from(gameResult, isBlackjack);

        // then
        assertThat(policy).isSameAs(BLACKJACK_WIN);
    }

    @DisplayName("플레이어가 블랙잭이면서 비긴 경우, BLACKJACK_DRAW 정책을 반환할 수 있다.")
    @Test
    void drawWithBlackjack() {
        // given
        GameResult gameResult = GameResult.DRAW;
        final boolean isBlackjack = true;

        // when
        RevenuePolicy policy = RevenuePolicy.from(gameResult, isBlackjack);

        // then
        assertThat(policy).isSameAs(BLACKJACK_DRAW);
    }

    @DisplayName("플레이어가 블랙잭이 아니면서 비긴 경우, NORMAL_DRAW 정책을 반환할 수 있다.")
    @Test
    void drawWithoutBlackjack() {
        // given
        GameResult gameResult = GameResult.DRAW;
        final boolean isBlackjack = false;

        // when
        RevenuePolicy policy = RevenuePolicy.from(gameResult, isBlackjack);

        // then
        assertThat(policy).isSameAs(NORMAL_DRAW);
    }

    @DisplayName("플레이어가 패배한 경우, LOSE 정책을 반환할 수 있다.")
    @Test
    void lose() {
        // given
        GameResult gameResult = GameResult.LOSE;
        final boolean isBlackjack = false;

        // when
        RevenuePolicy policy = RevenuePolicy.from(gameResult, isBlackjack);

        // then
        assertThat(policy).isSameAs(LOSE);
    }

    @DisplayName("플레이어가 블랙잭이면서 패배한 경우는 존재하지 않는다 - 예외 발생")
    @Test
    void exception() {
        // given
        GameResult gameResult = GameResult.LOSE;
        final boolean isBlackjack = true;

        // when & then
        assertThatThrownBy(() -> {
            RevenuePolicy.from(gameResult, isBlackjack);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
