package domain.player;

import domain.Bet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerStatusTest {

    @Test
    @DisplayName("이기면 베팅 금액의 1배를 반환한다")
    void calculatePrize() {
        //given
        Bet bet = new Bet(100);
        //when
        double v = DealerStatus.WIN.calculatePrize(bet);
        //then
        assertThat(v).isEqualTo(100);
    }

    @Test
    @DisplayName("지면 베팅 금액의 -1배를 반환한다")
    void calculatePrize2() {
        //given
        Bet bet = new Bet(100);
        //when
        double v = DealerStatus.LOSE.calculatePrize(bet);
        //then
        assertThat(v).isEqualTo(-100);
    }

    @Test
    @DisplayName("비기면 베팅 금액의 0배를 반환한다")
    void calculatePrize3() {
        //given
        Bet bet = new Bet(100);
        //when
        double v = DealerStatus.DRAW.calculatePrize(bet);
        //then
        assertThat(v).isEqualTo(0);
    }

    @Test
    @DisplayName("블랙잭으로 지면 베팅 금액의 -1.5배를 반환한다")
    void calculatePrize4() {
        //given
        Bet bet = new Bet(100);
        //when
        double v = DealerStatus.BLACKJACK_LOSE.calculatePrize(bet);
        //then
        assertThat(v).isEqualTo(-150);
    }

}
