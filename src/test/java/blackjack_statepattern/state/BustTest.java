package blackjack_statepattern.state;

import static blackjack_statepattern.Fixture.SPADES_ACE;
import static blackjack_statepattern.Fixture.SPADES_JACK;
import static blackjack_statepattern.Fixture.SPADES_TEN;
import static blackjack_statepattern.Fixture.SPADES_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack_statepattern.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BustTest {

    @Test
    @DisplayName("버스트인 상태에서 카드를 받지 못한다.")
    void bustDraw() {
        Bust bust = new Bust(new Cards(SPADES_JACK, SPADES_TWO, SPADES_TEN));
        assertThatThrownBy(
                () -> bust.draw(SPADES_ACE)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("버스트인 상태에서 수익을 계산할 수 있다.")
    void profit() {
        Bust bust = new Bust(new Cards(SPADES_JACK, SPADES_TWO, SPADES_TEN));
        int money = 1000;
        double profit = bust.profit(money);
        assertThat(profit).isEqualTo(money * bust.earningRate());
    }
}
