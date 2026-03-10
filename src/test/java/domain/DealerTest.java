package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    void 딜러의_점수가_16점_이하면_카드를_더_뽑을_수_있다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.ACE, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.FIVE, Suit.HEART));

        assertThat(dealer.canDraw()).isTrue();
    }

    @Test
    void 딜러의_점수가_17점_이상이면_카드를_더_뽑을_수_없다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.SEVEN, Suit.HEART));

        assertThat(dealer.canDraw()).isFalse();
    }
}