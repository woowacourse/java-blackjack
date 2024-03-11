package domain.constant;

import static domain.constant.GamerResult.DRAW;
import static domain.constant.GamerResult.LOSE;
import static domain.constant.GamerResult.WIN;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamerResultTest {
    @Test()
    @DisplayName("플레이어가 Bust이면 딜러가 승리한다")
    void playerIsBust() {
        Assertions.assertThat(GamerResult.judgeDealerResult(16, 22))
                .isEqualTo(WIN);
    }

    @Test()
    @DisplayName("딜러가 Bust이면 플레이어가 승리한다")
    void dealerIsBust() {
        Assertions.assertThat(GamerResult.judgeDealerResult(22, 16))
                .isEqualTo(LOSE);
    }

    @Test()
    @DisplayName("플레이어와 딜러 모두 Bust이면 딜러가 승리한다")
    void bothAreBust() {
        Assertions.assertThat(GamerResult.judgeDealerResult(22, 22))
                .isEqualTo(WIN);
    }

    @Test()
    @DisplayName("Bust가 없고 딜러의 점수가 더 높으면 딜러가 승리한다")
    void dealerIsWin() {
        Assertions.assertThat(GamerResult.judgeDealerResult(17, 16))
                .isEqualTo(WIN);
    }

    @Test()
    @DisplayName("Bust가 없고 플레이어의 점수가 더 높으면 플레이어가 승리한다")
    void playerIsWin() {
        Assertions.assertThat(GamerResult.judgeDealerResult(16, 17))
                .isEqualTo(LOSE);
    }

    @Test()
    @DisplayName("Bust가 없고 둘의 점수가 같으면 무승부이다")
    void draw() {
        Assertions.assertThat(GamerResult.judgeDealerResult(16, 16))
                .isEqualTo(DRAW);
    }
}
