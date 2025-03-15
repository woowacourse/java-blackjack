package domain.game;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultTest {

    @ParameterizedTest
    @CsvSource(value = {"WIN,1", "BLACKJACK_WIN,1", "LOSE,2", "DRAW,1"})
    void 딜러의_게임_결과_횟수를_계산한다(GameResult gameResult, int expected) {
        //when
        List<GameResult> gameResults = List.of(
                GameResult.BLACKJACK_WIN, GameResult.WIN,
                GameResult.LOSE, GameResult.DRAW
        );
        int winCount = gameResult.countGameResultFromDealer(gameResults);

        //then
        Assertions.assertThat(winCount).isEqualTo(expected);
    }
}
