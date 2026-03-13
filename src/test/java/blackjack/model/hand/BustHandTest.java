package blackjack.model.hand;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class BustHandTest {

    static final Collection<Card> DEFAULT_EXIST_CARDS = List.of(
            new Card(Rank.KING, Suit.HEART),
            new Card(Rank.QUEEN, Suit.HEART),
            new Card(Rank.JACK, Suit.HEART)
    );
    static final Card DEFAULT_NEW_CARD = new Card(Rank.ACE, Suit.HEART);

    @Test
    void 생성_시_버스트가_아니라면_예외를_던진다() {
        List<Card> notBustCards = List.of();
        Card notBustCard = new Card(Rank.TWO, Suit.HEART);

        assertThatThrownBy(() -> new BustHand(notBustCards, notBustCard))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 카드를_추가해도_현재_상태를_유지한다() {
        // given
        Hand hand = new BustHand(DEFAULT_EXIST_CARDS, DEFAULT_NEW_CARD);

        // when
        hand = hand.hit(new Card(Rank.ACE, Suit.HEART));

        // then
        assertThat(hand).isInstanceOf(BustHand.class);
    }
}
