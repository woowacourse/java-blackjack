package blackJack.domain.result;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class WinOrLoseTest {

    @ParameterizedTest(name = "플레이어와 딜러의 점수를 비교하여 승무패를 반환하는 기능 구현")
    @CsvSource({"15, 14, WIN", "15, 15, DRAW", "14, 15, LOSE", "22, 21, LOSE", "21, 22, WIN", "22, 22, LOSE"})
    void calculateWinOrLose(int playerScore, int dealerScore, WinOrLose winOrLose) {
        assertThat(WinOrLose.calculateWinOrLose(playerScore, dealerScore)).isEqualTo(winOrLose);
    }
}
