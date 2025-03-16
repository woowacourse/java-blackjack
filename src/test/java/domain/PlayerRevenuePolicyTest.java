package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.PlayerRevenuePolicy.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerRevenuePolicyTest {
    @DisplayName("플레이어가 블랙잭이 아니면서 승리한 경우, NORMAL_WIN 정책을 반환할 수 있다.")
    @Test
    void winningWithNotBlackjack() {
        // given
        GameResult gameResult = GameResult.WIN;
        final boolean isBlackjack = false;

        // when
        PlayerRevenuePolicy policy = PlayerRevenuePolicy.from(gameResult, isBlackjack);

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
        PlayerRevenuePolicy policy = PlayerRevenuePolicy.from(gameResult, isBlackjack);

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
        PlayerRevenuePolicy policy = PlayerRevenuePolicy.from(gameResult, isBlackjack);

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
        PlayerRevenuePolicy policy = PlayerRevenuePolicy.from(gameResult, isBlackjack);

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
        PlayerRevenuePolicy policy = PlayerRevenuePolicy.from(gameResult, isBlackjack);

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
            PlayerRevenuePolicy.from(gameResult, isBlackjack);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어가 블랙잭으로 승리한 경우(= BLACKJACK_WIN) 베팅 금액의 1.5배만큼의 수익을 얻는다.")
    @Test
    void blackjackWinRevenue() {
        // given
        final int bettingCost = 10000;
        PlayerRevenuePolicy policy = BLACKJACK_WIN;

        // when
        final int revenue = policy.getRevenue(bettingCost);

        // then
        assertThat(revenue).isEqualTo((int) (bettingCost * 1.5));
    }

    @DisplayName("플레이어가 승리한 경우(= NORMAL_WIN) 베팅 금액만큼의 수익을 얻는다.")
    @Test
    void notBlackjackWinRevenue() {
        // given
        final int bettingCost = 10000;
        PlayerRevenuePolicy policy = NORMAL_WIN;

        // when
        final int revenue = policy.getRevenue(bettingCost);

        // then
        assertThat(revenue).isEqualTo(bettingCost);
    }

    @DisplayName("플레이어가 블랙잭이 아니면서 비긴 경우(= NORMAL_DRAW) 수익을 얻지 않는다.")
    @Test
    void normalDrawRevenue() {
        // given
        final int bettingCost = 10000;
        PlayerRevenuePolicy policy = NORMAL_DRAW;

        // when
        final int revenue = policy.getRevenue(bettingCost);

        // then
        assertThat(revenue).isEqualTo(0);
    }

    @DisplayName("플레이어가 블랙잭이면서 비긴 경우(= BLACKJACK_DRAW) 수익을 얻지 않는다.")
    @Test
    void blackjackDrawRevenue() {
        // given
        final int bettingCost = 10000;
        PlayerRevenuePolicy policy = BLACKJACK_DRAW;

        // when
        final int revenue = policy.getRevenue(bettingCost);

        // then
        assertThat(revenue).isEqualTo(0);
    }

    @DisplayName("플레이어가 패배한 경우 베팅 금액을 잃는다.")
    @Test
    void loseRevenue() {
        // given
        final int bettingCost = 10000;
        PlayerRevenuePolicy policy = LOSE;

        // when
        final int revenue = policy.getRevenue(bettingCost);

        // then
        assertThat(revenue).isEqualTo(-1 * bettingCost);
    }
}
