package blackjack;

import blackjack.domain.WinOrLose;
import blackjack.domain.result.ResultOfGamer;
import blackjack.domain.result.ResultOfPlayers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class GameResultOfPlayersTest {

    private ResultOfPlayers resultOfPlayers;

    @BeforeEach
    void init() {
        resultOfPlayers = new ResultOfPlayers(
                Arrays.asList(
                        new ResultOfGamer("nabom", WinOrLose.WIN, 1000),
                        new ResultOfGamer("neozal", WinOrLose.LOSE, -1000)
                )
        );
    }

    @Test
    @DisplayName("딜러의 결과를 반환한다.")
    void getDealerResult() {
        assertThat(resultOfPlayers.getResultOfDealer().getWinOrLosesAsString()).containsExactly(WinOrLose.LOSE.getMessage(), WinOrLose.WIN.getMessage());
    }

    @Test
    @DisplayName("게임 결과를 반환한다.")
    void getGamerResult() {
        List<ResultOfGamer> resultOfGamers = resultOfPlayers.getResultOfGamers();

        assertThat(resultOfGamers.stream().map(ResultOfGamer::getName).collect(toList())).isEqualTo(Arrays.asList("nabom", "neozal"));
        assertThat(resultOfGamers.stream().map(ResultOfGamer::getRevenue).collect(toList())).isEqualTo(Arrays.asList(1000L, -1000L));
        assertThat(resultOfGamers.stream().map(ResultOfGamer::getWinOrLose).collect(toList())).isEqualTo(Arrays.asList(WinOrLose.WIN, WinOrLose.LOSE));
    }

}
