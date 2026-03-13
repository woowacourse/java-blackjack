package blackjack.model.hand;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackHandTest {

    static final Collection<Card> DEFAULT_EXIST_CARDS = List.of(new Card(Rank.TEN, Suit.HEART));
    static final Card DEFAULT_NEW_CARD = new Card(Rank.ACE, Suit.HEART);

    @Test
    void 생성_시_블랙잭이_아니라면_예외를_던진다() {
        List<Card> notBlackjackCards = List.of();
        Card notBlackjackCard = new Card(Rank.TWO, Suit.HEART);

        assertThatThrownBy(() -> new BlackjackHand(notBlackjackCards, notBlackjackCard))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 카드를_추가해_버스트가_된다면_전이한다() {
        // given
        Hand hand = new BlackjackHand(DEFAULT_EXIST_CARDS, DEFAULT_NEW_CARD);

        // when
        hand = hand.hit(new Card(Rank.JACK, Suit.HEART));
        hand = hand.hit(new Card(Rank.QUEEN, Suit.HEART));
        hand = hand.hit(new Card(Rank.KING, Suit.HEART));

        // then
        assertThat(hand).isInstanceOf(BustHand.class);
    }

    @Test
    void 블랙잭_수익률을_제공한다() {
        // given
        Hand hand = new BlackjackHand(DEFAULT_EXIST_CARDS, DEFAULT_NEW_CARD);

        // when
        double earningRate = hand.getEarningRate();

        // then
        assertThat(earningRate).isEqualTo(1.5);
    }
}
