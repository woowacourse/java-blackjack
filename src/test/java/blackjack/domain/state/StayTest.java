package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Cards;
import blackjack.domain.state.finished.Stay;
import org.junit.jupiter.api.Test;

class StayTest {

    @Test
    void 수익은_배팅한_금액만큼_받는다() {
        //given
        Stay stay = new Stay(new Cards());
        double bettingMoney = 1000;

        //when
        double profit = stay.profit(bettingMoney);

        //then
        assertThat(profit).isEqualTo(1000);
    }
}

