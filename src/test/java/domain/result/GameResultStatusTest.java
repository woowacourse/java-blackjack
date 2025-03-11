package domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultStatusTest {

    @DisplayName("딜러와 플레이어가 모두 버스트일 경우 플레이어 버스트가 우선이기 때문에, 플레이어의 패배이다")
    @Test
    void test1() {
        //given
        int dealerSum = 22;
        int playerSum = 23;

        //when
        GameResultStatus gameResultStatus = GameResultStatus.calculate(dealerSum, playerSum);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.LOSE);
    }

    @DisplayName("딜러만 버스트일 경우 플레이어의 승리이다")
    @Test
    void test2() {
        //given
        int dealerSum = 22;
        int playerSum = 20;

        //when
        GameResultStatus gameResultStatus = GameResultStatus.calculate(dealerSum, playerSum);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.WIN);
    }

    @DisplayName("플레이어만 버스트일 경우 플레이어의 패배이다")
    @Test
    void test3() {
        //given
        int dealerSum = 20;
        int playerSum = 23;

        //when
        GameResultStatus gameResultStatus = GameResultStatus.calculate(dealerSum, playerSum);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.LOSE);
    }

    @DisplayName("딜러와 플레이어가 모두 버스트가 아니고, 플레이어의 합이 더 높다면 플레이어의 승리이다")
    @Test
    void test4() {
        //given
        int dealerSum = 20;
        int playerSum = 21;

        //when
        GameResultStatus gameResultStatus = GameResultStatus.calculate(dealerSum, playerSum);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.WIN);
    }

    @DisplayName("딜러와 플레이어가 모두 버스트가 아니고, 딜러의 합이 더 높다면 플레이어의 패배이다")
    @Test
    void test5() {
        //given
        int dealerSum = 21;
        int playerSum = 20;

        //when
        GameResultStatus gameResultStatus = GameResultStatus.calculate(dealerSum, playerSum);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.LOSE);
    }

    @DisplayName("딜러와 플레이어가 모두 버스트가 아니고, 플레이어와 딜러의 합이 같다면 무승부이다")
    @Test
    void test6() {
        //given
        int dealerSum = 20;
        int playerSum = 20;

        //when
        GameResultStatus gameResultStatus = GameResultStatus.calculate(dealerSum, playerSum);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.DRAW);
    }

}
