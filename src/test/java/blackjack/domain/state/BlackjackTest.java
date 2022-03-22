package blackjack.domain.state;

import static blackjack.domain.TestBlackjackUtils.createCardHand;
import static blackjack.domain.TestCardFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @DisplayName("서로 블랙잭인 경우 1배를 반환한다.")
    @Test
    void drawWithBlackjack() {
        State playerState = new Blackjack(createCardHand(aceCard, kingCard));
        State dealerState = new Blackjack(createCardHand(aceCard, tenCard));

        double earningRate = playerState.earningRate(dealerState);

        assertThat(earningRate).isEqualTo(1.0);
    }

    @DisplayName("플레이어만 블랙잭인 경우 1.5배를 반환한다.")
    @Test
    void winBlackjack() {
        State playerState = new Blackjack(createCardHand(aceCard, kingCard));
        State dealerState = new Blackjack(createCardHand(twoCard, tenCard));

        double earningRate = playerState.earningRate(dealerState);

        assertThat(earningRate).isEqualTo(1.5);
    }

    @DisplayName("블랙잭인 경우 Running 상태가 아니다.")
    @Test
    void isNotRunningToBlackjack() {
        State state = new Blackjack(createCardHand(aceCard, kingCard));

        assertThat(state.isRunning()).isFalse();
    }
}
