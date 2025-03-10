package blackjack.domain.result;

import blackjack.domain.GameRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @ParameterizedTest
    @CsvSource({
            "16, 17, WIN",  // 플레이어가 이기는 경우
            "22, 17, WIN",  // 플레이어가 이기는 경우
            "20, 19, LOSE", // 딜러가 이기는 경우
            "20, 22, LOSE", // 딜러가 이기는 경우
            "21, 21, DRAW"  // 무승부
    })
    @DisplayName("플레이어의 승패를 판단할 수 있다")
    void canDecideResult(int dealerScore, int playerScore, GameResult expectedResult) {
        // given
        dealerScore = GameRule.applyBustRule(dealerScore);
        playerScore = GameRule.applyBustRule(playerScore);

        // when
        GameResult result = GameResult.of(dealerScore, playerScore);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("게임 결과의 반대 결과를 반환할 수 있다")
    void canReturnOppositeGameResult() {
        // given
        GameResult win = GameResult.WIN;
        GameResult lose = GameResult.LOSE;
        GameResult draw = GameResult.DRAW;

        // when
        GameResult opposedWin = win.oppose();
        GameResult opposedLose = lose.oppose();
        GameResult opposedDraw = draw.oppose();

        // then
        assertThat(opposedWin).isEqualTo(GameResult.LOSE);
        assertThat(opposedLose).isEqualTo(GameResult.WIN);
        assertThat(opposedDraw).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("딜러 게임 결과 초기값을 설정할 수 있다")
    void shouldInitializeDealerGameResultWithZeroValues() {
        // given
        // when
        Map<GameResult, Integer> dealerFormat = GameResult.getDealerFormat();

        // then
        assertThat(dealerFormat)
                .isInstanceOf(EnumMap.class)
                .hasSize(3)
                .containsKeys(GameResult.WIN, GameResult.LOSE, GameResult.DRAW)
                .allSatisfy((key, value) -> assertThat(value).isZero());
    }
}
