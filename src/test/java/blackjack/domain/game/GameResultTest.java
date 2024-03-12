package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.game.GameResult.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게임 결과")
public class GameResultTest {

    @Test
    @DisplayName("는 반대로도 출력된다.")
    void reverse() {
        // given &  when & then
        assertThat(WIN.reverse()).isEqualTo(LOSE);
        assertThat(LOSE.reverse()).isEqualTo(WIN);
        assertThat(DRAW.reverse()).isEqualTo(DRAW);
    }
}
