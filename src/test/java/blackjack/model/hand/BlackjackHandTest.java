package blackjack.model.hand;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackHandTest {

    @Test
    void 카드를_추가해도_현재_상태를_유지한다() {
        // given
        Hand hand = new BlackjackHand(List.of());

        // when
        hand = hand.hit(new Card(Rank.JACK, Suit.HEART));
        hand = hand.hit(new Card(Rank.QUEEN, Suit.HEART));
        hand = hand.hit(new Card(Rank.KING, Suit.HEART));

        // then
        assertThat(hand).isInstanceOf(BlackjackHand.class);
    }

    @Test
    void 블랙잭_수익률을_제공한다() {
        // given
        Hand hand = new BlackjackHand(List.of());

        // when
        double earningRate = hand.getEarningRate();

        // then
        assertThat(earningRate).isEqualTo(1.5);
    }
}
