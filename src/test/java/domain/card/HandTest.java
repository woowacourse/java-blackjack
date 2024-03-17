package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
    @Test
    @DisplayName("카드패의 점수를 알 수 있다.")
    void score() {
        Hand hand = new Hand(new Cards(List.of(new Card(CardShape.SPADE, CardNumber.ACE), new Card(CardShape.SPADE, CardNumber.KING))));
        assertThat(hand.score()).isEqualTo(21);
    }
}
