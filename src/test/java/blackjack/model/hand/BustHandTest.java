package blackjack.model.hand;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.List;
import org.junit.jupiter.api.Test;

class BustHandTest {

    @Test
    void 생성_시_버스트가_아니라면_예외를_던진다() {
        List<Card> notBustCards = List.of();
        Card notBustCard = new Card(Rank.TWO, Suit.HEART);

        assertThatThrownBy(() -> new BustHand(notBustCards, notBustCard))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 현재_상태에_대한_수익률을_제공한다() {
        // given
        List<Card> bustCards = List.of(
                new Card(Rank.KING, Suit.HEART),
                new Card(Rank.QUEEN, Suit.HEART),
                new Card(Rank.JACK, Suit.HEART)
        );
        Card newCard = new Card(Rank.TEN, Suit.HEART);

        BustHand hand = new BustHand(bustCards, newCard);

        // when
        double earningRate = hand.getEarningRate();

        // then
        assertThat(earningRate).isEqualTo(1);
    }
}
