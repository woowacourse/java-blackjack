package blackjack.domain.state;

import static blackjack.domain.TestBlackjackUtils.createCardHand;
import static blackjack.domain.TestCardFixture.aceCard;
import static blackjack.domain.TestCardFixture.kingCard;
import static blackjack.domain.TestCardFixture.tenCard;
import static blackjack.domain.TestCardFixture.twoCard;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustTest {

    @DisplayName("버스트 상태면 -1배를 반환한다.")
    @Test
    void isBust() {
        State playerState = new Bust(createCardHand(aceCard, kingCard, tenCard));
        State dealerState = new Bust(createCardHand(twoCard, tenCard, tenCard));

        double earningRate = playerState.earningRate(dealerState);

        assertThat(earningRate).isEqualTo(-1);
    }

    @DisplayName("버스트인 경우 Running 상태가 아니다.")
    @Test
    void isNotRunningToBust() {
        State state = new Bust(createCardHand(aceCard, kingCard, tenCard));

        assertThat(state.isRunning()).isFalse();
    }
}
