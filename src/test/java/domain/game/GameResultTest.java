package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.participant.Player;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class GameResultTest {

    private final Map<Player, Winning> playerWinningResult
        = Map.ofEntries(
        Map.entry(new Player("이름1"), Winning.WIN),
        Map.entry(new Player("이름2"), Winning.DRAW),
        Map.entry(new Player("이름3"), Winning.DRAW),
        Map.entry(new Player("이름4"), Winning.LOSE),
        Map.entry(new Player("이름5"), Winning.LOSE),
        Map.entry(new Player("이름6"), Winning.LOSE)
    );

    @Test
    void 딜러의_승무패_횟수를_계산한다() {
        //given
        GameResult gameResult = new GameResult(playerWinningResult);

        //when
        int dealerWinCount = gameResult.countDealerWin();
        int dealerDrawCount = gameResult.countDealerDraw();
        int dealerLoseCount = gameResult.countDealerLose();

        //then
        assertAll(
            () -> assertThat(dealerWinCount).isEqualTo(3),
            () -> assertThat(dealerDrawCount).isEqualTo(2),
            () -> assertThat(dealerLoseCount).isEqualTo(1)
        );
    }
}
