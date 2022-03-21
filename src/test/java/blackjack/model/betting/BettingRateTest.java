package blackjack.model.betting;

import static blackjack.model.state.CardFixture.CLOVER_EIGHT;
import static blackjack.model.state.CardFixture.CLOVER_JACK;
import static blackjack.model.state.CardFixture.CLOVER_KING;
import static blackjack.model.state.CardFixture.CLOVER_TWO;
import static blackjack.model.state.CardFixture.DIAMOND_JACK;
import static blackjack.model.state.CardFixture.HEART_EIGHT;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.state.State;
import blackjack.model.state.running.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingRateTest {

    @DisplayName("Dealer의 State 점수합과 Player의 State 점수합이 같으면 DRAW를 반환한다.")
    @Test
    void draw_same_score() {
        State dealerState = new Ready().add(CLOVER_JACK).add(CLOVER_EIGHT);
        State playerState = new Ready().add(DIAMOND_JACK).add(HEART_EIGHT);

        assertThat(BettingRate.compareTo(dealerState, playerState)).isEqualTo(BettingRate.DRAW);
    }

    @DisplayName("Dealer의 State 점수합이 Player의 State 점수합보다 높으면 WIN을 반환한다.")
    @Test
    void win_dealer() {
        State dealerState = new Ready().add(CLOVER_JACK).add(CLOVER_KING);
        State playerState = new Ready().add(DIAMOND_JACK).add(HEART_EIGHT);

        assertThat(BettingRate.compareTo(dealerState, playerState)).isEqualTo(BettingRate.WIN);
    }

    @DisplayName("Dealer의 State 점수합이 Player의 State 점수합보다 낮으면 LOSE를 반환한다.")
    @Test
    void lose_dealer() {
        State dealerState = new Ready().add(CLOVER_JACK).add(CLOVER_TWO);
        State playerState = new Ready().add(DIAMOND_JACK).add(HEART_EIGHT);

        assertThat(BettingRate.compareTo(dealerState, playerState)).isEqualTo(BettingRate.LOSE);
    }
}