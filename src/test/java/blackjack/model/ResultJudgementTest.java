package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class ResultJudgementTest {

    private static final int LOWER_SCORE = 10;
    private static final int DEFAULT_SCORE = 15;
    private static final int HIGHER_SCORE = 20;
    private static final int BUST_SCORE = 22;
    private static final int BUST_THRESHOLD = 21;

    private final BustPolicy bustPolicy = new BustPolicyImpl(BUST_THRESHOLD);
    private final ResultJudgement resultJudgement = new ResultJudgement(bustPolicy);

    @Test
    void 둘_다_버스트가_아니면서_플레이어가_점수가_더_높다면_플레이어가_승리한다() {
        // given
        Score playerScore = new Score(HIGHER_SCORE);
        Score dealerScore = new Score(LOWER_SCORE);
        // when
        PlayerBlackjackResult result = resultJudgement.judge(playerScore, dealerScore);
        // then
        assertThat(result).isEqualTo(PlayerBlackjackResult.WIN);
    }

    @Test
    void 둘_다_버스트가_아니면서_플레이어가_점수가_더_낮다면_플레이어가_패배한다() {
        // given
        Score playerScore = new Score(LOWER_SCORE);
        Score dealerScore = new Score(HIGHER_SCORE);
        // when
        PlayerBlackjackResult result = resultJudgement.judge(playerScore, dealerScore);
        // then
        assertThat(result).isEqualTo(PlayerBlackjackResult.LOSE);
    }

    @Test
    void 둘_다_버스트가_아니면서_점수가_같다면_무승부한다() {
        // given
        Score playerScore = new Score(DEFAULT_SCORE);
        Score dealerScore = new Score(DEFAULT_SCORE);
        // when
        PlayerBlackjackResult result = resultJudgement.judge(playerScore, dealerScore);
        // then
        assertThat(result).isEqualTo(PlayerBlackjackResult.PUSH);
    }

    @Test
    void 플레이어가_버스트라면_플레이어가_패배한다() {
        // given
        Score playerScore = new Score(BUST_SCORE);
        Score dealerScore = new Score(DEFAULT_SCORE);
        // when
        PlayerBlackjackResult result = resultJudgement.judge(playerScore, dealerScore);
        // then
        assertThat(result).isEqualTo(PlayerBlackjackResult.LOSE);
    }

    @Test
    void 딜러만_버스트라면_플레이어가_승리한다() {
        // given
        Score playerScore = new Score(DEFAULT_SCORE);
        Score dealerScore = new Score(BUST_SCORE);
        // when
        PlayerBlackjackResult result = resultJudgement.judge(playerScore, dealerScore);
        // then
        assertThat(result).isEqualTo(PlayerBlackjackResult.WIN);
    }

    @Test
    void 둘_다_버스트라면_플레이어가_패배한다() {
        // given
        Score playerScore = new Score(BUST_SCORE);
        Score dealerScore = new Score(BUST_SCORE);
        // when
        PlayerBlackjackResult result = resultJudgement.judge(playerScore, dealerScore);
        // then
        assertThat(result).isEqualTo(PlayerBlackjackResult.LOSE);
    }
}
