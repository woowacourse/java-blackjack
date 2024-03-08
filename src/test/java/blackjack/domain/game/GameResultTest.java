package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.game.GameResult.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게임 결과")
public class GameResultTest {

    @Test
    @DisplayName("게임 결과가 반대로도 출력되는지 확인한다.")
    void reverseTest() {
        // given &  when & then
        assertThat(WIN.reverse()).isEqualTo(LOSE);
        assertThat(LOSE.reverse()).isEqualTo(WIN);
        assertThat(DRAW.reverse()).isEqualTo(DRAW);
    }
}
