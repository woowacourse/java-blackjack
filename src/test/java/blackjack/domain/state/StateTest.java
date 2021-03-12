package blackjack.domain.state;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static blackjack.domain.state.CardFactory.*;
import static org.assertj.core.api.Assertions.assertThat;

class StateTest {
    @DisplayName("처음 두 장의 카드 합이 21일 경우 블랙잭이 되고, 베팅 금액의 1.5 배를 딜러에게 받는다.")
    @Test
    void playerBlackJackRateTest() {
        State state = StateFactory.draw(SPADE_ACE, SPADE_TEN);
        Player player = new Player(state, "pobi");

        State dealerState = StateFactory.draw(HEART_TWO, HEART_ACE);
        Dealer dealer = new Dealer(dealerState);

        BigDecimal profit = player.getState().profit(dealer.getState(), new BigDecimal("10000"));
        assertThat(profit).isEqualTo(new BigDecimal("15000.0"));
    }

    @DisplayName("처음 두 장의 카드 합이 21일 경우 블랙잭이 되는데, 딜러 또한 블랙잭이면 베팅 금액을 딜러에게 받는다. 즉, 수익은 0원")
    @Test
    void playerRateWhenDealerBlackJackTest() {
        State state = StateFactory.draw(SPADE_ACE, SPADE_TEN);
        Player player = new Player(state, "pobi");

        State dealerState = StateFactory.draw(HEART_ACE, HEART_TEN);
        Dealer dealer = new Dealer(dealerState);

        BigDecimal profit = player.getState().profit(dealer.getState(), new BigDecimal("10000"));
        assertThat(profit).isEqualTo(new BigDecimal("0"));
    }

    @DisplayName("플레이어는 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.")
    @Test
    void playerBusterLoose() {
        State state = StateFactory.draw(HEART_TEN, SPADE_TEN);
        Player player = new Player(state, "pobi");
        State draw = player.getState().draw(SPADE_TWO);
        player.changeState(draw);

        State dealerState = StateFactory.draw(HEART_ACE, HEART_TEN);
        Dealer dealer = new Dealer(dealerState);

        BigDecimal profit = player.getState().profit(dealer.getState(), new BigDecimal("10000"));
        assertThat(profit).isEqualTo(new BigDecimal("-10000"));
    }
}