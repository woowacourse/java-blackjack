package blackjack.player.domain.report;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameReportsTest {

    @DisplayName("주입받은 게임결과가 없는경우 Exception")
    @Test
    void noGameReports() {
        assertAll(
                () -> assertThatThrownBy(() -> new GameReports(null))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("게임결과가 존재하지 않습니다."),

                () -> assertThatThrownBy(() -> new GameReports(Collections.emptyList()))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("게임결과가 존재하지 않습니다.")
        );
    }
}