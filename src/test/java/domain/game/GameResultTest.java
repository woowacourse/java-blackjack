package domain.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {
    @Test
    @DisplayName("게임 최종 결과 테스트")
    void 게임_최종_결과_검증_딜러_승() {
        Assertions.assertThat(GameResult.determine(20, 16)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("게임 최종 결과 테스트")
    void 게임_최종_결과_검증_딜러_패() {
        Assertions.assertThat(GameResult.determine(18, 21)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("게임 최종 결과 테스트")
    void 게임_최종_결과_검증_무승부() {
        Assertions.assertThat(GameResult.determine(20, 20)).isEqualTo(GameResult.DRAW);
    }

}