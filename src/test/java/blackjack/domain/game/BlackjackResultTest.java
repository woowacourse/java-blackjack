package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.user.Player;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackResultTest {

    @Test
    @DisplayName("딜러의 승리 횟수를 반환한다")
    void getDealerWinCountTest() {
        Map<Player, GameResult> gameResult = new HashMap<>();
        gameResult.put(new Player("박스터"), GameResult.LOSE);
        gameResult.put(new Player("제이미"), GameResult.LOSE);
        gameResult.put(new Player("코즈"), GameResult.WIN);
        BlackjackResult result = new BlackjackResult(gameResult);

        int dealerWinCount = result.getDealerWinCount();

        assertThat(dealerWinCount).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 패배 횟수를 반환한다")
    void getDealerLoseCountTest() {
        Map<Player, GameResult> gameResult = new HashMap<>();
        gameResult.put(new Player("박스터"), GameResult.WIN);
        gameResult.put(new Player("제이미"), GameResult.LOSE);
        gameResult.put(new Player("코즈"), GameResult.WIN);
        BlackjackResult result = new BlackjackResult(gameResult);

        int dealerLoseCount = result.getDealerLoseCount();

        assertThat(dealerLoseCount).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 무승부 횟수를 반환한다")
    void getDealerTieCountTest() {
        Map<Player, GameResult> gameResult = new HashMap<>();
        gameResult.put(new Player("박스터"), GameResult.WIN);
        gameResult.put(new Player("제이미"), GameResult.LOSE);
        gameResult.put(new Player("코즈"), GameResult.TIE);
        BlackjackResult result = new BlackjackResult(gameResult);

        int tieCount = result.getTieCount();

        assertThat(tieCount).isEqualTo(1);
    }
}
