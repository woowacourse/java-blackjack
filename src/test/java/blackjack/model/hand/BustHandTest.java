package blackjack.model.hand;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.List;
import org.junit.jupiter.api.Test;

class BustHandTest {

    @Test
    void 카드를_추가해도_현재_상태를_유지한다() {
        // given
        Hand hand = new BustHand(List.of());

        // when
        hand = hand.hit(new Card(Rank.ACE, Suit.HEART));

        // then
        assertThat(hand).isInstanceOf(BustHand.class);
    }
}
