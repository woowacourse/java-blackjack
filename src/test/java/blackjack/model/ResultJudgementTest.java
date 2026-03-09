package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class ResultJudgementTest {

    static final int LOWER_SCORE = 10;
    static final int DEFAULT_SCORE = 15;
    static final int HIGHER_SCORE = 20;
    static final int BUST_SCORE = 30;

    final ResultJudgement resultJudgement = new ResultJudgement();

    @Test
    void 둘_다_버스트가_아니면서_플레이어가_점수가_더_높다면_플레이어가_승리한다() {
        // given
        int playerScore = HIGHER_SCORE;
        int dealerScore = LOWER_SCORE;

        // when
        BlackjackResult result = resultJudgement.judge(playerScore, dealerScore);

        // then
        assertThat(result).isEqualTo(BlackjackResult.WIN);
    }

    @Test
    void 둘_다_버스트가_아니면서_플레이어가_점수가_더_낮다면_플레이어가_패배한다() {
        // given
        int playerScore = LOWER_SCORE;
        int dealerScore = HIGHER_SCORE;

        // when
        BlackjackResult result = resultJudgement.judge(playerScore, dealerScore);

        // then
        assertThat(result).isEqualTo(BlackjackResult.LOSE);
    }

    @Test
    void 둘_다_버스트가_아니면서_점수가_같다면_무승부한다() {
        // given
        int playerScore = DEFAULT_SCORE;
        int dealerScore = DEFAULT_SCORE;

        // when
        BlackjackResult result = resultJudgement.judge(playerScore, dealerScore);

        // then
        assertThat(result).isEqualTo(BlackjackResult.PUSH);
    }

    @Test
    void 플레이어가_버스트라면_플레이어가_패배한다() {
        // given
        int playerScore = BUST_SCORE;
        int dealerScore = DEFAULT_SCORE;

        // when
        BlackjackResult result = resultJudgement.judge(playerScore, dealerScore);

        // then
        assertThat(result).isEqualTo(BlackjackResult.LOSE);
    }

    @Test
    void 딜러만_버스트라면_플레이어가_승리한다() {
        // given
        int playerScore = DEFAULT_SCORE;
        int dealerScore = BUST_SCORE;

        // when
        BlackjackResult result = resultJudgement.judge(playerScore, dealerScore);

        // then
        assertThat(result).isEqualTo(BlackjackResult.WIN);
    }

    @Test
    void 둘_다_버스트라면_플레이어가_패배한다() {
        // given
        int playerScore = BUST_SCORE;
        int dealerScore = BUST_SCORE;

        // when
        BlackjackResult result = resultJudgement.judge(playerScore, dealerScore);

        // then
        assertThat(result).isEqualTo(BlackjackResult.LOSE);
    }
}
