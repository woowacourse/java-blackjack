package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BlackJackStateTest {

    @DisplayName("2장의 합이 21인 경우, 블랙잭 상태를 얻을 수 있다.")
    @Test
    void createBlackJackStateBlackJack() {
        BlackJackState state = BlackJackState.createBlackJackState(21, 2);
        assertThat(state).isEqualTo(BlackJackState.BLACKJACK);
    }

    @DisplayName("카드의 총합이 21을 넘는 경우, 버스트 상태를 얻을 수 있다.")
    @Test
    void createBlackJackStateBust() {
        BlackJackState state = BlackJackState.createBlackJackState(22, 2);
        assertThat(state).isEqualTo(BlackJackState.BUST);
    }

    @DisplayName("카드의 합이 21을 넘지 않은 경우, 스탠드 상태를 얻을 수 있다.")
    @Test
    void createBlackJackStateStand() {
        BlackJackState state = BlackJackState.createBlackJackState(20, 2);
        assertThat(state).isEqualTo(BlackJackState.STAND);
    }
}
