package blackjack;

import blackjack.domain.ResultOfPlayers;
import blackjack.domain.WinOrLose;
import blackjack.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultOfPlayersTest {

    private ResultOfPlayers resultOfPlayers;

    @BeforeEach
    void init() {
        Map<String, Pair<WinOrLose, Integer>> map = new HashMap<>();
        map.put("nabom", new Pair<>(WinOrLose.WIN, 1000));
        map.put("neozal", new Pair<>(WinOrLose.LOSE, -1000));

        resultOfPlayers = new ResultOfPlayers(map);
    }

    @Test
    @DisplayName("딜러의 결과를 반환한다.")
    void getDealerResult() {
        assertThat(resultOfPlayers.getDealerResult().getKey()).containsExactly(WinOrLose.WIN.getMessage(), WinOrLose.LOSE.getMessage());
    }

    @Test
    @DisplayName("게임 결과를 반환한다.")
    void getGamerResult() {
        Map<String, Pair<String, Integer>> gameResult = resultOfPlayers.getGamerResult();

        assertThat(gameResult.keySet()).isEqualTo(new HashSet<>(Arrays.asList("nabom", "neozal")));
        Collection<Pair<String, Integer>> result = gameResult.values();

        assertThat(result).containsExactly(
                new Pair<>(WinOrLose.LOSE.getMessage(), -1000),
                new Pair<>(WinOrLose.WIN.getMessage(), 1000)
        );

    }

}
