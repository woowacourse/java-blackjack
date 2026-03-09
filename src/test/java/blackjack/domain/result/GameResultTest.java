package blackjack.domain.result;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// GameResultTest - 표현 관련만
class GameResultTest {

    @Test
    void 승의_반전은_패이다() {
        assertThat(GameResult.WIN.reverse()).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 패의_반전은_승이다() {
        assertThat(GameResult.LOSE.reverse()).isEqualTo(GameResult.WIN);
    }

    @Test
    void 무승부의_반전은_무승부이다() {
        assertThat(GameResult.DRAW.reverse()).isEqualTo(GameResult.DRAW);
    }
}