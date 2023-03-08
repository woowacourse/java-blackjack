package domain.result;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Test
    @DisplayName("게임 결과를 받아서 해당 이름을 반환한다.")
    void findGameResultName() {
        Assertions.assertThat(GameResult.findGameResultName(GameResult.WIN)).isEqualTo("승");
        Assertions.assertThat(GameResult.findGameResultName(GameResult.TIE)).isEqualTo("무");
        Assertions.assertThat(GameResult.findGameResultName(GameResult.LOSE)).isEqualTo("패");
    }
}
