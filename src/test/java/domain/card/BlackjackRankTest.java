package domain.card;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class BlackjackRankTest {

    @Test
    void 블랙잭_게임에서_ACE는_1점_혹은_11점이다() {
        // given & when
        List<Integer> scores = BlackjackRank.getScoresByRank(Rank.ACE);
        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(scores).contains(1);
            softAssertions.assertThat(scores).contains(11);
        });
    }

    @Test
    void 블랙잭_게임에서_숫자2는_2점이다() {
        // given & when
        List<Integer> scores = BlackjackRank.getScoresByRank(Rank.TWO);
        // then
        Assertions.assertThat(scores).contains(2);
    }

    @Test
    void 블랙잭_게임에서_숫자3는_3점이다() {
        // given & when
        List<Integer> scores = BlackjackRank.getScoresByRank(Rank.THREE);
        // then
        Assertions.assertThat(scores).contains(3);
    }

    @Test
    void 블랙잭_게임에서_숫자4는_4점이다() {
        // given & when
        List<Integer> scores = BlackjackRank.getScoresByRank(Rank.FOUR);
        // then
        Assertions.assertThat(scores).contains(4);
    }

    @Test
    void 블랙잭_게임에서_숫자5는_5점이다() {
        // given & when
        List<Integer> scores = BlackjackRank.getScoresByRank(Rank.FIVE);
        // then
        Assertions.assertThat(scores).contains(5);
    }

    @Test
    void 블랙잭_게임에서_숫자6는_6점이다() {
        // given & when
        List<Integer> scores = BlackjackRank.getScoresByRank(Rank.SIX);
        // then
        Assertions.assertThat(scores).contains(6);
    }

    @Test
    void 블랙잭_게임에서_숫자7는_7점이다() {
        // given & when
        List<Integer> scores = BlackjackRank.getScoresByRank(Rank.SEVEN);
        // then
        Assertions.assertThat(scores).contains(7);
    }

    @Test
    void 블랙잭_게임에서_숫자8는_8점이다() {
        // given & when
        List<Integer> scores = BlackjackRank.getScoresByRank(Rank.EIGHT);
        // then
        Assertions.assertThat(scores).contains(8);
    }

    @Test
    void 블랙잭_게임에서_숫자9는_9점이다() {
        // given & when
        List<Integer> scores = BlackjackRank.getScoresByRank(Rank.NINE);
        // then
        Assertions.assertThat(scores).contains(9);
    }

    @Test
    void 블랙잭_게임에서_숫자10는_10점이다() {
        // given & when
        List<Integer> scores = BlackjackRank.getScoresByRank(Rank.TEN);
        // then
        Assertions.assertThat(scores).contains(10);
    }

    @Test
    void 블랙잭_게임에서_JACK은_10점이다() {
        // given & when
        List<Integer> scores = BlackjackRank.getScoresByRank(Rank.JACK);
        // then
        Assertions.assertThat(scores).contains(10);
    }

    @Test
    void 블랙잭_게임에서_QUEEN은_10점이다() {
        // given & when
        List<Integer> scores = BlackjackRank.getScoresByRank(Rank.QUEEN);
        // then
        Assertions.assertThat(scores).contains(10);
    }

    @Test
    void 블랙잭_게임에서_KING은_10점이다() {
        // given & when
        List<Integer> scores = BlackjackRank.getScoresByRank(Rank.KING);
        // then
        Assertions.assertThat(scores).contains(10);
    }
}