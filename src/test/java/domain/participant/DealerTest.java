package domain.participant;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Denomination;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    void 점수가_최소_점수_정책보다_작으면_true를_반환한다() {
        Dealer dealer = new Dealer();

        dealer.receiveAdditionalCard(카드(Denomination.TEN));
        dealer.receiveAdditionalCard(카드(Denomination.SIX));

        boolean result = dealer.isNotFinished();
        assertThat(result).isTrue();
    }

    @Test
    void 점수가_최소_점수_정책보다_크거나_같으면_false를_반환한다() {
        Dealer dealer = new Dealer();

        dealer.receiveAdditionalCard(카드(Denomination.NINE));
        dealer.receiveAdditionalCard(카드(Denomination.EIGHT));

        boolean result = dealer.isNotFinished();
        assertThat(result).isFalse();
    }
}
