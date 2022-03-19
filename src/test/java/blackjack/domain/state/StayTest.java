package blackjack.domain.state;

import static blackjack.fixtures.BlackjackFixtures.SPADE_EIGHT;
import static blackjack.fixtures.BlackjackFixtures.SPADE_SEVEN;
import static blackjack.fixtures.BlackjackFixtures.SPADE_THREE;
import static blackjack.fixtures.BlackjackFixtures.SPADE_TWO;
import static blackjack.fixtures.BlackjackFixtures.TEN_DEALER;
import static org.assertj.core.api.Assertions.assertThat;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.vo.BettingMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StayTest {

    @Test
    @DisplayName("Stay상태에서 딜러보다 점수가 낮을 경우 -1배의 수익을 얻는다.")
    void lose() {
        State state = Ready.start(SPADE_TWO, SPADE_THREE).stay();

        assertThat(state.profit(new BettingMoney(1000), TEN_DEALER)).isEqualTo(-1000);
    }

    @Test
    @DisplayName("Stay상태에서 딜러와 점수가 같을 경우 -1배의 수익을 얻는다.")
    void draw() {
        State state = Ready.start(SPADE_THREE, SPADE_SEVEN).stay();

        assertThat(state.profit(new BettingMoney(1000), TEN_DEALER)).isEqualTo(-1000);
    }

    @Test
    @DisplayName("Stay상태에서 딜러보다 점수가 높을 경우 1배의 수익을 얻는다.")
    void win() {
        State state = Ready.start(SPADE_SEVEN, SPADE_EIGHT).stay();

        assertThat(state.profit(new BettingMoney(1000), TEN_DEALER)).isEqualTo(1000);
    }

    @Test
    @DisplayName("Stay상태에서 딜러가 21을 넘을 경우 1배의 수익을 얻는다.")
    void bustWin() {
        State state = Ready.start(SPADE_SEVEN, SPADE_EIGHT).stay();

        Dealer tenDealer = TEN_DEALER;
        tenDealer.addCard(SPADE_EIGHT);
        assertThat(state.profit(new BettingMoney(1000), tenDealer)).isEqualTo(1000);
    }

}
