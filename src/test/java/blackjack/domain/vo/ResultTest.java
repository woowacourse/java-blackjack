package blackjack.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {


    @DisplayName("둘 다 21을 초과하지 않을 경우, 딜러가 플레이어보다 점수가 높으면 플레이어가 패배한다.")
    @Test
    public void judgeResult_PlayerAndDealerScoreUnder21_PlayerLose() {
        Result result = Result.judge(20, 19);

        assertThat(result).isEqualTo(Result.LOSE);
    }

    @DisplayName("딜러가 21을 초과할 때 플레이어가 21을 초과하면 플레이어 패배")
    @Test
    public void judgeResult_PlayerAndDealerScoreOver21() {
        Result result = Result.judge(23, 22);

        assertThat(result).isEqualTo(Result.LOSE);
    }

    @DisplayName("딜러가 21을 초과할 때 플레이어가 21을 초과하지 않으면 플레이어 승")
    @Test
    public void judgeResult_DealerScoreOver21() {
        Result result = Result.judge(23, 19);

        assertThat(result).isEqualTo(Result.WIN);
    }
}
