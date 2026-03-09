package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 점수가_16_이하이면_카드를_받을_수_있다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SIX));

        assertThat(dealer.canReceiveCard()).isTrue();
    }

    @Test
    void 점수가_17_이상이면_카드를_받을_수_없다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        assertThat(dealer.canReceiveCard()).isFalse();
    }
}
