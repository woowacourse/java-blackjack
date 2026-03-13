package blackjack.model.hand;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class HitHandTest {

    static final Collection<Card> DEFAULT_EXIST_CARDS = List.of();
    static final Card DEFAULT_NEW_CARD = new Card(Rank.ACE, Suit.HEART);

    @Test
    void 카드를_추가해도_버스트가_아니라면_상태를_유지한다() {
        // given
        Hand hand = new HitHand(DEFAULT_EXIST_CARDS, DEFAULT_NEW_CARD);

        // when
        hand = hand.hit(new Card(Rank.ACE, Suit.HEART));

        // then
        assertThat(hand).isInstanceOf(HitHand.class);
    }

    @Test
    void 카드를_추가해_버스트가_된다면_전이한다() {
        // given
        Hand hand = new HitHand(DEFAULT_EXIST_CARDS, DEFAULT_NEW_CARD);

        // when
        hand = hand.hit(new Card(Rank.JACK, Suit.HEART));
        hand = hand.hit(new Card(Rank.QUEEN, Suit.HEART));
        hand = hand.hit(new Card(Rank.KING, Suit.HEART));

        // then
        assertThat(hand).isInstanceOf(BustHand.class);
    }
}
