package domain.participant;

import domain.Card;
import org.junit.jupiter.api.Test;

import static domain.constant.Rank.*;
import static domain.constant.Suit.*;
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
}