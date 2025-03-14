//package model.card;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//class RankTest {
//
//    @Test
//    @DisplayName("카드 점수가 조정이 되는 지")
//    void adjustRankScore() {
//
//        // given
//        Rank rank = Rank.ACE;
//        final int originScore = rank.getScore();
//        final int originExpected = 11;
//
//        // when
//        rank.findNextScore();
//        final int newScore = rank.getScore();
//        final int newExpected = 1;
//
//        // then
//        Assertions.assertThat(originScore).isEqualTo(originExpected);
//        Assertions.assertThat(newScore).isEqualTo(newExpected);
//    }
//}
