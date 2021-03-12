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
        Player player = new Player(state, "pobi", new BigDecimal("10000"));

        State dealerState = StateFactory.draw(HEART_TWO, HEART_ACE);
        Dealer dealer = new Dealer(dealerState);

        BigDecimal profit = player.getState().profit(dealer.getState(), player.getBettingMoney());
        assertThat(profit).isEqualTo(new BigDecimal("15000.0"));
    }

    @DisplayName("처음 두 장의 카드 합이 21일 경우 블랙잭이 되는데, 딜러 또한 블랙잭이면 베팅 금액을 딜러에게 받는다. 즉, 수익은 0원")
    @Test
    void playerRaeWhenDealerBlackJackTest() {
        State state = StateFactory.draw(SPADE_ACE, SPADE_TEN);
        Player player = new Player(state, "pobi", new BigDecimal("10000"));

        State dealerState = StateFactory.draw(HEART_ACE, HEART_TEN);
        Dealer dealer = new Dealer(dealerState);

        BigDecimal profit = player.getState().profit(dealer.getState(), player.getBettingMoney());
        assertThat(profit).isEqualTo(new BigDecimal("0"));
    }

    @DisplayName("플레이어는 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.")
    @Test
    void playerBusterLoose() {
        State state = StateFactory.draw(HEART_TEN, SPADE_TEN);
        Player player = new Player(state, "pobi", new BigDecimal("10000"));
        State draw = player.getState().draw(SPADE_TWO);
        player.changeState(draw);

        State dealerState = StateFactory.draw(HEART_ACE, HEART_TEN);
        Dealer dealer = new Dealer(dealerState);

        BigDecimal profit = player.getState().profit(dealer.getState(), player.getBettingMoney());
        assertThat(profit).isEqualTo(new BigDecimal("-10000"));
    }

    @DisplayName("딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.")
    @Test
    void dealerLooseWhenBuster() {
        State state = StateFactory.draw(HEART_TEN, SPADE_TEN);
        Player player = new Player(state, "pobi", new BigDecimal("10000"));
        State stay = player.getState().stay();
        player.changeState(stay);

        State dealerState = StateFactory.draw(HEART_TWO, HEART_TEN);
        Dealer dealer = new Dealer(dealerState);
        State draw = dealer.getState().draw(SPADE_TEN);
        dealer.changeState(draw);
        BigDecimal profit = player.getState().profit(dealer.getState(), player.getBettingMoney());

        assertThat(profit).isEqualTo(new BigDecimal("10000"));
    }

    @DisplayName("딜러와 플레이어가 21이하이고, 둘다 블랙잭이 아닐 때 점수가 높은 쪽이 배팅한 금액만큼을 가지게 된다.")
    @Test
    void resultTest() {
        State state = StateFactory.draw(HEART_TWO, SPADE_TEN);
        Player player = new Player(state, "pobi", new BigDecimal("10000"));
        State stay = player.getState().stay();
        player.changeState(stay);

        State drawState = StateFactory.draw(HEART_TEN, SPADE_TEN);
        Player drawPlayer = new Player(drawState, "tobi", new BigDecimal("10000"));
        State drawStay = drawPlayer.getState().stay();
        drawPlayer.changeState(drawStay);

        State dealerState = StateFactory.draw(HEART_JACK, HEART_TEN);
        Dealer dealer = new Dealer(dealerState);
        BigDecimal profit = player.getState().profit(dealer.getState(), player.getBettingMoney());
        BigDecimal profit2 = drawPlayer.getState().profit(dealer.getState(), drawPlayer.getBettingMoney());

        assertThat(profit).isEqualTo(new BigDecimal("-10000"));
        assertThat(profit2).isEqualTo(new BigDecimal("0"));

    }
}