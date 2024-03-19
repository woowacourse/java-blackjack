package domain.participant;

import static domain.card.Denomination.EIGHT;
import static domain.card.Denomination.NINE;
import static domain.card.Denomination.SIX;
import static domain.card.Denomination.TEN;
import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    void 점수가_최소_점수_정책보다_작으면_true를_반환한다() {
        Dealer dealer = new Dealer();
        dealer.receiveAdditionalCard(카드(TEN));
        dealer.receiveAdditionalCard(카드(SIX));

        boolean result = dealer.isNotFinished();

        assertThat(result).isTrue();
    }

    @Test
    void 점수가_최소_점수_정책보다_크거나_같으면_false를_반환한다() {
        Dealer dealer = new Dealer();
        dealer.receiveAdditionalCard(카드(NINE));
        dealer.receiveAdditionalCard(카드(EIGHT));

        boolean result = dealer.isNotFinished();

        assertThat(result).isFalse();
    }
}
