package domain.gamestate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStateTest {

    @Test
    @DisplayName("승리시 배팅 금액만큼 수익을 얻는다.")
    void calculateWin() {
        //given
        Win win = GameStates.WIN;
        int betAmount = 10000;
        //when
        double gain = win.calculate(betAmount);
        //then
        assertThat(gain).isEqualTo(betAmount);
    }

    @Test
    @DisplayName("블랙잭으로 승리시 배팅 금액의 1.5배만큼 수익을 얻는다.")
    void calculateBlackJack() {
        //given
        Win win = GameStates.BLACKJACK;
        int betAmount = 10000;
        //when
        double gain = win.calculate(betAmount);
        //then
        assertThat(gain).isEqualTo(betAmount * 1.5);
    }

    @Test
    @DisplayName("패배시 배팅 금액만큼 잃는다.")
    void calculateLose() {
        //given
        Lose lose = GameStates.LOSE;
        int betAmount = 10000;
        //when
        double gain = lose.calculate(betAmount);
        //then
        assertThat(gain).isEqualTo(betAmount * -1);
    }

    @Test
    @DisplayName("무승부시 수익은 0이다.")
    void calculateDraw() {
        //given
        Draw draw = GameStates.DRAW;
        int betAmount = 10000;
        //when
        double gain = draw.calculate(betAmount);
        //then
        assertThat(gain).isEqualTo(0);
    }
}
