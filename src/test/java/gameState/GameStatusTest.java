package gameState;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStatusTest {

    @DisplayName("비길 시 최종 수익은 배팅 금액과 같습니다.")
    @Test
    void draw() {
        Draw draw = new Draw(new BettingAmount(1000));
        Assertions.assertThat(draw.getFinalBenefit()).isEqualTo(1000);
    }

    @DisplayName("이길 시 최종 수익은 배팅 금액을 2배와 같습니다.")

    @Test
    void win() {
        Win win = new Win(new BettingAmount(1000));
        Assertions.assertThat(win.getFinalBenefit()).isEqualTo(2000);
    }

    @DisplayName("지면 최종 수익은 0원입니다.")
    @Test
    void lose() {
        Lose lose = new Lose(new BettingAmount(1000));
        Assertions.assertThat(lose.getFinalBenefit()).isEqualTo(0);
    }

    @DisplayName("플레이 중에 최종 수익을 조회시 오류를 던진다.")
    @Test
    void playing() {
        Playing playing = new Playing(new BettingAmount(1000));
        Assertions.assertThatThrownBy(playing::getFinalBenefit)
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("아직 게임 중 입니다.");
    }

    @DisplayName("배팅한 금액을 반환한다.")
    @Test
    void getBetAmount() {
        Playing playing = new Playing(new BettingAmount(1000));
        Assertions.assertThat(playing.getBettingAmount().getAmount()).isEqualTo(1000);
    }

}
