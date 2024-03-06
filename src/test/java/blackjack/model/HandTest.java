package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {
    @Test
    @DisplayName("딜러와 참여자들에게 카드를 2장씩 나누어 준다")
    void dealTest() {
        // when
        Hand hand = new Hand();

        // then
        assertThat(hand.getCards()).hasSize(2);
    }
}
