package blackjack.model;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameOutcomeTest {

    private GameSummary createPlayerSummary(int score, boolean isBust, boolean isBlackjack) {
        return new GameSummary("pobi", List.of(), score, isBust, isBlackjack);
    }

    private GameSummary createDealerSummary(int score, boolean isBust, boolean isBlackjack) {
        return new GameSummary("딜러", List.of(), score, isBust, isBlackjack);
    }

    @Test
    @DisplayName("플레이어만 블랙잭이면 플레이어 블랙잭 승리")
    void test_player_win_when_player_is_blackjack() {
        GameSummary playerSummary = createPlayerSummary(21, false, true);
        GameSummary dealerSummary = createDealerSummary(20, false, false);

        assertThat(GameOutcome.judge(playerSummary, dealerSummary)).isEqualTo(GameOutcome.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 블랙잭이면 무승부")
    void test_draw_when_both_are_blackjack() {
        GameSummary playerSummary = createPlayerSummary(21, false, true);
        GameSummary dealerSummary = createDealerSummary(21, false, true);

        assertThat(GameOutcome.judge(playerSummary, dealerSummary)).isEqualTo(GameOutcome.DRAW);
    }

    @Test
    @DisplayName("딜러만 블랙잭이면 플레이어 패배")
    void test_player_lose_when_dealer_is_blackjack() {
        GameSummary playerSummary = createPlayerSummary(21, false, false);
        GameSummary dealerSummary = createDealerSummary(21, false, true);

        assertThat(GameOutcome.judge(playerSummary, dealerSummary)).isEqualTo(GameOutcome.LOSE);
    }


    @Test
    @DisplayName("플레이어가 버스트면 플레이어 패배")
    void test_player_lose_when_player_is_bust() {
        GameSummary playerSummary = createPlayerSummary(22, true, false);
        GameSummary dealerSummary = createDealerSummary(20, false, false);

        assertThat(GameOutcome.judge(playerSummary, dealerSummary)).isEqualTo(GameOutcome.LOSE);
    }

    @Test
    @DisplayName("딜러만 버스트면 플레이어 승리")
    void test_player_win_when_dealer_is_bust() {
        GameSummary playerSummary = createPlayerSummary(20, false, false);
        GameSummary dealerSummary = createDealerSummary(22, true, false);

        assertThat(GameOutcome.judge(playerSummary, dealerSummary)).isEqualTo(GameOutcome.WIN);
    }

    @Test
    @DisplayName("블랙잭과 버스트 경우 외에 점수가 더 높으면 승리")
    void test_win_when_player_score_is_higher() {
        GameSummary playerSummary = createPlayerSummary(20, false, false);
        GameSummary dealerSummary = createDealerSummary(19, false, false);

        assertThat(GameOutcome.judge(playerSummary, dealerSummary)).isEqualTo(GameOutcome.WIN);
    }


    @Test
    @DisplayName("블랙잭과 버스트 경우 외에 점수가 같으면 무승부")
    void test_draw_when_scores_are_equal() {
        GameSummary playerSummary = createPlayerSummary(20, false, false);
        GameSummary dealerSummary = createDealerSummary(20, false, false);

        assertThat(GameOutcome.judge(playerSummary, dealerSummary)).isEqualTo(GameOutcome.DRAW);
    }

}
