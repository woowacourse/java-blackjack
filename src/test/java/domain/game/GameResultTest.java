package domain.game;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    private static List<GameResult> provideGameResult() {
        return List.of(GameResult.BLACKJACK_WIN, GameResult.WIN, GameResult.LOSE, GameResult.DRAW);
    }

    @Test
    void 딜러의_승리_횟수를_계산한다() {
        //when
        int winCount = GameResult.WIN.countGameResultFromDealer(provideGameResult());

        //then
        Assertions.assertThat(winCount).isEqualTo(1);

    }

    @Test
    void 딜러의_패배_횟수를_계산한다() {
        //when
        int winCount = GameResult.LOSE.countGameResultFromDealer(provideGameResult());

        //then
        Assertions.assertThat(winCount).isEqualTo(2);

    }

    @Test
    void 딜러의_무승부_횟수를_계산한다() {
        //when
        int winCount = GameResult.DRAW.countGameResultFromDealer(provideGameResult());

        //then
        Assertions.assertThat(winCount).isEqualTo(1);
    }
}
