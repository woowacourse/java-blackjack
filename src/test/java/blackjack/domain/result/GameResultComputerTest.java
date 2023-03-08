package blackjack.domain.result;

import static blackjack.domain.result.JudgeResult.LOSE;
import static blackjack.domain.result.JudgeResult.PUSH;
import static blackjack.domain.result.JudgeResult.WIN;
import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GameResultComputerTest {

    @DisplayName("딜러의 판정 결과 통계를 순서대로 반환한다.")
    @Test
    void should_ReturnInJudgeResultOrder_CountDealerJudgeResults() {
        final GameResultComputer gameResultComputer = new GameResultComputer(Map.of(
                "player1", WIN,
                "player2", LOSE,
                "player3", LOSE,
                "player4", PUSH
        ));

        assertThat(gameResultComputer.countDealerJudgeResults())
                .containsExactly(entry(WIN, 2), entry(PUSH, 1), entry(LOSE, 1));
    }
}
