package domain;

import domain.player.DealerStatus;
import domain.stake.Stake;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StakeTest {

    @Test
    @DisplayName("0미만일 수  없다")
    void minimumTest() {
        assertThatThrownBy(() -> new Stake(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("미만");
    }

    @Test
    @DisplayName("100_000 초과일 수 없다")
    void maximumTest() {
        assertThatThrownBy(() -> new Stake(100_001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("초과");
    }


    @Test
    @DisplayName("이기면 베팅 금액의 1배를 반환한다")
    void calculatePrize() {
        //given
        Stake stake = new Stake(100);
        //when
        double v = stake.getPrize(DealerStatus.WIN);
        //then
        assertThat(v).isEqualTo(100);
    }

    @Test
    @DisplayName("지면 베팅 금액의 -1배를 반환한다")
    void calculatePrize2() {
        //given
        Stake stake = new Stake(100);
        //when
        double v = stake.getPrize(DealerStatus.LOSE);
        //then
        assertThat(v).isEqualTo(-100);
    }

    @Test
    @DisplayName("비기면 베팅 금액의 0배를 반환한다")
    void calculatePrize3() {
        //given
        Stake stake = new Stake(100);
        //when
        double v = stake.getPrize(DealerStatus.DRAW);
        //then
        assertThat(v).isEqualTo(0);
    }

    @Test
    @DisplayName("블랙잭으로 지면 베팅 금액의 -1.5배를 반환한다")
    void calculatePrize4() {
        //given
        Stake stake = new Stake(100);
        //when
        double v = stake.getPrize(DealerStatus.BLACKJACK_LOSE);
        //then
        assertThat(v).isEqualTo(-150);
    }
}
