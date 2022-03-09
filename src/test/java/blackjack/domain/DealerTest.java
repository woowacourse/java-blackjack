package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러의 카드 총합이 17보다 작을 경우 카드를 계속 받는다.")
    void under16_dealerGetCard() {
        Dealer dealer = new Dealer(List.of(new Card(Symbol.SPADE, CardNumber.TEN), new Card(Symbol.SPADE, CardNumber.SIX)));
        assertThat(dealer.isFinished()).isFalse();
    }

    @Test
    @DisplayName("딜러의 카드 총합이 17 이상일 경우 카드를 받을 수 없다.")
    void over16_dealerCannotGetCard() {
        Dealer dealer = new Dealer(List.of(new Card(Symbol.SPADE, CardNumber.TEN), new Card(Symbol.SPADE, CardNumber.SEVEN)));
        assertThat(dealer.isFinished()).isTrue();
    }
}
