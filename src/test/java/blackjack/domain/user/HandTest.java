package blackjack.domain.user;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @Test
    @DisplayName("비어있는 카드와 점수를 갖는 핸드를 생성한다.")
    public void createEmptyHand() {
        // when
        Hand hand = new Hand();

        // then
        Assertions.assertThat(hand).isNotNull();
    }
}