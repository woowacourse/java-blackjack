package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {

    @Test
    @DisplayName("베팅 금액은 0원 이하면 예외를 반환한다.")
    void lessThanOrEqualsToZero() {
        assertThatThrownBy(() -> new Betting(0)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 0원 이하일 수 없습니다.");
    }

    @Test
    @DisplayName("이기면 베팅 금액 만큼 수익을 얻는다.")
    void win() {
        //given
        Betting betting = new Betting(1000);

        //when
        int earns = betting.calculateEarns(Outcome.WIN, false);

        //then
        assertThat(earns).isEqualTo(1000);
    }

    @Test
    @DisplayName("비기면 본전 치기")
    void draw() {
        //given
        Betting betting = new Betting(1000);

        //when
        int earns = betting.calculateEarns(Outcome.DRAW, false);

        //then
        assertThat(earns).isEqualTo(0);
    }

    @Test
    @DisplayName("지면 베팅 금액을 잃는다.")
    void lose() {
        //given
        Betting betting = new Betting(1000);

        //when
        int earns = betting.calculateEarns(Outcome.LOSE, false);

        //then
        assertThat(earns).isEqualTo(-1000);
    }

    @Test
    @DisplayName("블랙잭으로 이기면 베팅 금액의 1.5배의 수익을 얻는다.")
    void winWithBlackjack() {
        //given
        Betting betting = new Betting(1000);

        //when
        int earns = betting.calculateEarns(Outcome.WIN, true);

        //then
        assertThat(earns).isEqualTo(1500);
    }
}
