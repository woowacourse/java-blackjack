package domain;

import java.util.LinkedHashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    GameResult gameResult;

    @BeforeEach
    void setUp() {
        Map<String, Boolean> result = new LinkedHashMap<>();
        result.put("hogee", true);
        result.put("pola", false);
        result.put("jazz", true);
        result.put("kaki", true);
        gameResult = new GameResult(result);
    }

    @DisplayName("딜러의 승 갯수를 카운트한다.")
    @Test
    void countDealerWinTest() {
        Assertions.assertThat(gameResult.countDealerWin()).isEqualTo(1);
    }

    @DisplayName("딜러의 패 갯수를 카운트한다.")
    @Test
    void countDealerLoseTest() {
        Assertions.assertThat(gameResult.countDealerLose()).isEqualTo(3);
    }

    @DisplayName("true 가 들어가면 승, false가 들어가면 패를 리턴한다.")
    @Test
    void getWinOrLoseTest() {
        gameResult.getWinOrLose(true);
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(gameResult.getWinOrLose(true)).isEqualTo("승"),
                () -> Assertions.assertThat(gameResult.getWinOrLose(false)).isEqualTo("패"
        );
    }
}
