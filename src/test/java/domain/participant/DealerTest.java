package domain.participant;

import domain.card.Card;
import org.junit.jupiter.api.Test;

import static domain.card.Rank.*;
import static domain.card.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    void 점수_합계가_16점_이하이면_True를_반환한다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(SIX, SPADE));
        dealer.receiveCard(new Card(QUEEN, SPADE));

        assertThat(dealer.canReceiveCard()).isTrue();
    }

    @Test
    void 점수_합계가_16점_초과이면_False를_반환한다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(JACK, SPADE));
        dealer.receiveCard(new Card(QUEEN, SPADE));

        assertThat(dealer.canReceiveCard()).isFalse();
    }

    @Test
    void 핸드에서_첫_번째_카드를_오픈한다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(ACE, SPADE));
        dealer.receiveCard(new Card(TEN, SPADE));

        assertThat(dealer.getOpenCard()).isEqualTo("A스페이드");
    }

    @Test
    void 보유한_카드가_2장이고_점수가_21점이면_블랙잭이다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(ACE, SPADE));
        dealer.receiveCard(new Card(TEN, SPADE));

        assertThat(dealer.isBlackjack()).isTrue();
    }

    @Test
    void 보유한_카드가_2장이_아니고_점수가_21점이면_블랙잭이_아니다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(SEVEN, SPADE));
        dealer.receiveCard(new Card(SEVEN, HEART));
        dealer.receiveCard(new Card(SEVEN, CLUB));

        assertThat(dealer.isBlackjack()).isFalse();
    }
}