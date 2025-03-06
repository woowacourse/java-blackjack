package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackProcessManagerTest {

    @DisplayName("손에 카드 2장 쥐어준다.")
    @Test
    void test1() {
        // given
        BlackjackProcessManager blackjackProcessManager = new BlackjackProcessManager();
        Hand hand = new Hand();

        // when
        blackjackProcessManager.giveCardTo(hand);

        // then

    }
}
