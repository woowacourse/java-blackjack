package blackjack.model;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Test
    @DisplayName("플레이어만 블랙잭이면 플레이어 승리")
    void test_player_win_when_player_is_blackjack() {
        Player player = new Player("pobi");
        Dealer dealer = new Dealer();

        GameSummary playerSummary = new GameSummary(player, 21, false, true);
        GameSummary dealerSummary = new GameSummary(dealer, 20, false, false);

        assertThat(GameResult.judge(playerSummary, dealerSummary)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 블랙잭이면 무승부")
    void test_draw_when_both_are_blackjack() {
        Player player = new Player("pobi");
        Dealer dealer = new Dealer();

        GameSummary playerSummary = new GameSummary(player, 21, false, true);
        GameSummary dealerSummary = new GameSummary(dealer, 21, false, true);

        assertThat(GameResult.judge(playerSummary, dealerSummary)).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("딜러만 블랙잭이면 플레이어 패배")
    void test_player_lose_when_dealer_is_blackjack() {
        Player player = new Player("pobi");
        Dealer dealer = new Dealer();

        GameSummary playerSummary = new GameSummary(player, 21, false, false);
        GameSummary dealerSummary = new GameSummary(dealer, 21, false, true);

        assertThat(GameResult.judge(playerSummary, dealerSummary)).isEqualTo(GameResult.LOSE);
    }


    @Test
    @DisplayName("플레이어가 버스트면 플레이어 패배")
    void test_player_lose_when_player_is_bust() {
        Player player = new Player("pobi");
        Dealer dealer = new Dealer();

        GameSummary playerSummary = new GameSummary(player, 22, true, false);
        GameSummary dealerSummary = new GameSummary(dealer, 20, false, false);

        assertThat(GameResult.judge(playerSummary, dealerSummary)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러만 버스트면 플레이어 승리")
    void test_player_win_when_dealer_is_bust() {
        Player player = new Player("pobi");
        Dealer dealer = new Dealer();

        GameSummary playerSummary = new GameSummary(player, 20, false, false);
        GameSummary dealerSummary = new GameSummary(dealer, 22, true, false);

        assertThat(GameResult.judge(playerSummary, dealerSummary)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("블랙잭과 버스트 경우 외에 점수가 더 높으면 승리")
    void test_win_when_player_score_is_higher() {
        Player player = new Player("pobi");
        Dealer dealer = new Dealer();

        GameSummary playerSummary = new GameSummary(player, 20, false, false);
        GameSummary dealerSummary = new GameSummary(dealer, 19, false, false);

        assertThat(GameResult.judge(playerSummary, dealerSummary)).isEqualTo(GameResult.WIN);
    }


    @Test
    @DisplayName("블랙잭과 버스트 경우 외에 점수가 같으면 무승부")
    void test_draw_when_scores_are_equal() {
        Player player = new Player("pobi");
        Dealer dealer = new Dealer();

        GameSummary playerSummary = new GameSummary(player, 20, false, false);
        GameSummary dealerSummary = new GameSummary(dealer, 20, false, false);

        assertThat(GameResult.judge(playerSummary, dealerSummary)).isEqualTo(GameResult.DRAW);
    }

}
