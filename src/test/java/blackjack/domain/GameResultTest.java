package blackjack.domain;

//@DisplayName("게임 결과")
//class GameResultTest {
//
//    @DisplayName("게임 결과에 따라 받을 금액을 반환한다.")
//    @Test
//    void getProfit() {
//        BigDecimal betting = BigDecimal.valueOf(1000);
//
//        assertAll(
//                () -> assertThat(GameResult.WIN.getProfit(betting, true)).isEqualTo(BigDecimal.valueOf(1500.0)),
//                () -> assertThat(GameResult.WIN.getProfit(betting, false)).isEqualTo(BigDecimal.valueOf(1000)),
//                () -> assertThat(GameResult.DRAW.getProfit(betting, true)).isEqualTo(BigDecimal.valueOf(0)),
//                () -> assertThat(GameResult.LOSE.getProfit(betting, true)).isEqualTo(BigDecimal.valueOf(-1000))
//        );
//    }
//}