package blackjack.model;


class GameOutcomeTest {

//    @Test
//    @DisplayName("플레이어만 블랙잭이면 플레이어 블랙잭 승리")
//    void test_player_win_when_player_is_blackjack() {
//        GameSummary playerSummary = new GameSummary("pobi", 21, false, true);
//        GameSummary dealerSummary = new GameSummary("딜러", 20, false, false);
//
//        assertThat(GameOutcome.judge(playerSummary, dealerSummary)).isEqualTo(GameOutcome.BLACKJACK_WIN);
//    }
//
//    @Test
//    @DisplayName("플레이어와 딜러 모두 블랙잭이면 무승부")
//    void test_draw_when_both_are_blackjack() {
//        GameSummary playerSummary = new GameSummary("pobi", 21, false, true);
//        GameSummary dealerSummary = new GameSummary("딜러", 21, false, true);
//
//        assertThat(GameOutcome.judge(playerSummary, dealerSummary)).isEqualTo(GameOutcome.DRAW);
//    }
//
//    @Test
//    @DisplayName("딜러만 블랙잭이면 플레이어 패배")
//    void test_player_lose_when_dealer_is_blackjack() {
//        GameSummary playerSummary = new GameSummary("pobi", 21, false, false);
//        GameSummary dealerSummary = new GameSummary("딜러", 21, false, true);
//
//        assertThat(GameOutcome.judge(playerSummary, dealerSummary)).isEqualTo(GameOutcome.LOSE);
//    }
//
//
//    @Test
//    @DisplayName("플레이어가 버스트면 플레이어 패배")
//    void test_player_lose_when_player_is_bust() {
//        GameSummary playerSummary = new GameSummary("pobi", 22, true, false);
//        GameSummary dealerSummary = new GameSummary("딜러", 20, false, false);
//
//        assertThat(GameOutcome.judge(playerSummary, dealerSummary)).isEqualTo(GameOutcome.LOSE);
//    }
//
//    @Test
//    @DisplayName("딜러만 버스트면 플레이어 승리")
//    void test_player_win_when_dealer_is_bust() {
//        GameSummary playerSummary = new GameSummary("pobi", 20, false, false);
//        GameSummary dealerSummary = new GameSummary("딜러", 22, true, false);
//
//        assertThat(GameOutcome.judge(playerSummary, dealerSummary)).isEqualTo(GameOutcome.WIN);
//    }
//
//    @Test
//    @DisplayName("블랙잭과 버스트 경우 외에 점수가 더 높으면 승리")
//    void test_win_when_player_score_is_higher() {
//        GameSummary playerSummary = new GameSummary("pobi", 20, false, false);
//        GameSummary dealerSummary = new GameSummary("딜러", 19, false, false);
//
//        assertThat(GameOutcome.judge(playerSummary, dealerSummary)).isEqualTo(GameOutcome.WIN);
//    }
//
//
//    @Test
//    @DisplayName("블랙잭과 버스트 경우 외에 점수가 같으면 무승부")
//    void test_draw_when_scores_are_equal() {
//        GameSummary playerSummary = new GameSummary("pobi", 20, false, false);
//        GameSummary dealerSummary = new GameSummary("딜러", 20, false, false);
//
//        assertThat(GameOutcome.judge(playerSummary, dealerSummary)).isEqualTo(GameOutcome.DRAW);
//    }

}
