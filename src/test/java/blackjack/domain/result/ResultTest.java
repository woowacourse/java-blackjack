package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ResultTest {

    @Test
    @DisplayName("유저와 딜러 모두 21점이 넘는 경우 유저는 패배한다.")
    void dealerAndUserExceedTest() {
        assertThat(Result.checkUserResult(22, 22)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러가 21점이 넘고 유저는 넘지 않는 경우 유저는 승리한다.")
    void dealerExceedTest() {
        assertThat(Result.checkUserResult(21, 22)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("딜러가 21점이 넘지 않고 유저가 21을 넘는 경우 유저는 패배한다.")
    void userExceedTest() {
        assertThat(Result.checkUserResult(22, 21)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러가 21점이 넘지 않고 유저가 딜러 점수보다 낮은 경우 유저는 패배한다.")
    void userScoreLoseTest() {
        assertThat(Result.checkUserResult(20, 21)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러가 21점이 넘지 않고 유저가 딜러 점수보다 높은 경우 유저는 승리한다.")
    void userScoreWinTest() {
        assertThat(Result.checkUserResult(21, 20)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("딜러가 21점이 넘지 않고 유저가 딜러 점수와 같은 경우 무승부이다.")
    void userScoreDrawTest() {
        assertThat(Result.checkUserResult(21, 21)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("입력으로 Lose를 받을 때 Win이 나온다.")
    void swapLoseToWinTest() {
        assertThat(Result.swap(Result.LOSE)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("입력으로 Win를 받을 때 Lose가 나온다.")
    void swapWinToLoseTest() {
        assertThat(Result.swap(Result.WIN)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("입력으로 DRAW를 받을 때 DRAW이 나온다.")
    void swapDrawToDrawTest() {
        assertThat(Result.swap(Result.DRAW)).isEqualTo(Result.DRAW);
    }
}
