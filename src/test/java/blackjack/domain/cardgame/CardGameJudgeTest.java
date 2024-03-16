package blackjack.domain.cardgame;

public class CardGameJudgeTest {
//    @Test
//    void 딜러와_플레이어_둘다_21을_초과할_경우에_플레이어가_패배한다() {
//        // given
//        CardGameJudge cardGameJudge = new CardGameJudge();
//        Player mangcho = player(
//                new Card(KING, CLOVER),
//                new Card(KING, HEART),
//                new Card(KING, SPADE));
//        Dealer dealer = dealer(
//                new Card(QUEEN, CLOVER),
//                new Card(QUEEN, HEART),
//                new Card(QUEEN, SPADE));
//
//        // when
//        var totalResult = cardGameJudge.judge(dealer, List.of(mangcho))
//                .getTotalResult();
//
//        // then
//        assertThat(totalResult.get(mangcho)).isEqualTo(WinningStatus.LOSE);
//    }
//
//    @Test
//    void 카드_합계가_딜러는_21_이하_플레이어는_21_초과인_경우_플레이어가_패배한다() {
//        // given
//        CardGameJudge cardGameJudge = new CardGameJudge();
//        Player player = player(
//                new Card(KING, CLOVER),
//                new Card(KING, HEART),
//                new Card(KING, DIAMOND));
//        Dealer dealer = dealer(new Card(TWO, HEART));
//
//        // when
//        var result = cardGameJudge.judge(dealer, List.of(player))
//                .getTotalResult();
//
//        // then
//        assertThat(result.get(player)).isEqualTo(WinningStatus.LOSE);
//    }
//
//    @Test
//    void 카드_합계가_딜러는_21_초과_플레이어는_21_이하인_경우_플레이어가_승리한다() {
//        // given
//        CardGameJudge cardGameJudge = new CardGameJudge();
//        Player player = player(new Card(TWO, HEART));
//        Dealer dealer = dealer(
//                new Card(KING, CLOVER),
//                new Card(KING, HEART),
//                new Card(KING, SPADE));
//
//        // when
//        var result = cardGameJudge.judge(dealer, List.of(player))
//                .getTotalResult();
//
//        // then
//        assertThat(result.get(player)).isEqualTo(WinningStatus.WIN);
//    }
//
//    @Test
//    void 카드_합계가_딜러와_플레이어_모두_21_이하인_경우_숫자가_작은_사람이_패배한다() {
//        // given
//        CardGameJudge cardGameJudge = new CardGameJudge();
//        Player player = player(new Card(TWO, HEART));
//        Dealer dealer = dealer(new Card(KING, SPADE));
//
//        // when
//        var result = cardGameJudge.judge(dealer, List.of(player))
//                .getTotalResult();
//
//        // then
//        assertThat(result.get(player)).isEqualTo(WinningStatus.LOSE);
//    }
//
//    @Test
//    void 카드_합계가_딜러와_플레이어_모두_21_이하인_경우_숫자가_큰_사람이_승리한다() {
//        // given
//        CardGameJudge cardGameJudge = new CardGameJudge();
//        Player player = player(new Card(KING, SPADE));
//        Dealer dealer = dealer(new Card(TWO, HEART));
//
//        // when
//        var result = cardGameJudge.judge(dealer, List.of(player))
//                .getTotalResult();
//
//        // then
//        assertThat(result.get(player)).isEqualTo(WinningStatus.WIN);
//    }
//
//    @Test
//    void 카드_합계가_딜러와_플레이어_모두_21_이하이고_동일한_경우_무승부다() {
//        // given
//        CardGameJudge cardGameJudge = new CardGameJudge();
//        Player player = player(new Card(ACE, HEART));
//        Dealer dealer = dealer(new Card(ACE, HEART));
//
//        // when
//        var result = cardGameJudge.judge(dealer, List.of(player))
//                .getTotalResult();
//
//        // then
//        assertThat(result.get(player)).isEqualTo(WinningStatus.PUSH);
//    }
}
