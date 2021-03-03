package blackjack.participant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    GameResult gameResult;

    @BeforeEach
    void setUp() {
        gameResult = new GameResult();
    }

    @Test
    void win() {
        gameResult.win();
        assertThat(gameResult.getWinCount()).isEqualTo(1);
    }

    @Test
    void draw() {
        gameResult.draw();
        assertThat(gameResult.getDrawCount()).isEqualTo(1);
    }

    @Test
    void lose() {
        gameResult.lose();
        assertThat(gameResult.getLoseCount()).isEqualTo(1);
    }

    @Test
    void gameResultPlus() {
        GameResult gameResult1 = new GameResult();
        gameResult1.lose();
        gameResult1.lose();
        gameResult.plus(gameResult1);
        assertThat(gameResult.getLoseCount()).isEqualTo(2);
    }

    @Test
    void gameResultReverse() {
        GameResult gameResult1 = new GameResult();
        gameResult1.lose();
        gameResult1.lose();

        assertThat(gameResult1.reverse()
                              .getWinCount()).isEqualTo(2);
    }
}