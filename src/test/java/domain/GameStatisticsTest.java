package domain;

import domain.participant.Player;
import domain.result.GameResult;
import domain.result.GameStatistics;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameStatisticsTest {
    @Test
    public void 플레이어별_이름과_최종_게임_결과_문구가_매핑되어_반환된다() {
        // given
        Map<Player, GameResult> testMap = Map.of(new Player("토리", 1000), GameResult.WIN,
                new Player("이안", 2000), GameResult.LOSE,
                new Player("에덴", 3000), GameResult.DRAW,
                new Player("녀녕", 10000), GameResult.BLACKJACK);

        Map<GameResult, Integer> testDealerMap = Map.of(GameResult.WIN, 1,
                GameResult.LOSE, 2, GameResult.DRAW, 1);

        GameStatistics gameStatistics = new GameStatistics(testMap, testDealerMap);
        // when
        Map<String, String> playerResult = gameStatistics.getPlayerResult();

        // then
        Assertions.assertThat(playerResult)
                .containsEntry("이안", "패")
                .containsEntry("토리", "승")
                .containsEntry("에덴", "무")
                .containsEntry("녀녕", "승");
    }

    @Test
    void 플레이어_결과를_반전하여_딜러의_최종_결과를_합산한다() {
        // given
        Map<Player, GameResult> testMap = Map.of(new Player("토리", 1000), GameResult.WIN,
                new Player("이안", 2000), GameResult.LOSE,
                new Player("에덴", 3000), GameResult.DRAW,
                new Player("녀녕", 10000), GameResult.BLACKJACK);
        Map<GameResult, Integer> testDealerMap = Map.of(GameResult.WIN, 1,
                GameResult.LOSE, 2, GameResult.DRAW, 1);

        GameStatistics statistics = new GameStatistics(testMap, testDealerMap);

        // when
        Map<String, Integer> dealerResult = statistics.getDealerResult();

        // then
        Assertions.assertThat(dealerResult.get("승")).isEqualTo(1);
        Assertions.assertThat(dealerResult.get("무")).isEqualTo(1);
        Assertions.assertThat(dealerResult.get("패")).isEqualTo(2);
    }
}
