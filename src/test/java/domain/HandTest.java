package domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("카드를 손에 추가한다.")
    @Test
    void 카드를_손에_추가한다() {

        // given
        final List<Card> cards = new ArrayList<>();
        final Card card1 = new Card(Rank.ACE, Shape.CLOVER);
        final Card card2 = new Card(Rank.FIVE, Shape.CLOVER);
        final Hand hand = new Hand(cards);

        // when & then
        assertThatCode(() -> {
            hand.add(card1);
            hand.add(card2);
        }).doesNotThrowAnyException();
    }
}
