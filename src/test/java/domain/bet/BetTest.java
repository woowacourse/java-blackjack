package domain.bet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BetTest {

    @Test
    @DisplayName("금액을 받아 Bet를 생성할 수 있다.")
    void test1() {
        assertThat(new Bet(10000)).isInstanceOf(Bet.class);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이면 베팅 금액의 1.5배를 리턴한다.")
    void test2() {
        Bet bet = new Bet(10000);

        assertThat(bet.applyBlackJackBonus()).isEqualTo(15000);
    }

    @Test
    @DisplayName("딜러가 버스트 시 살아남은 플레이어라면 베팅 금액의 2배를 리턴한다.")
    void test3() {
        Bet bet = new Bet(10000);

        assertThat(bet.applyDealerBustBonus()).isEqualTo(20000);
    }
}