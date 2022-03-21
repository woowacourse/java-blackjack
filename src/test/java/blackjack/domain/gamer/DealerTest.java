package blackjack.domain.gamer;

import static blackjack.CardConstant.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 카드 번호합 17 이상이면 더 뽑을 수 없다.")
    void dealerDrawable() {
        Dealer dealer = new Dealer(CLOVER_TEN, CLOVER_SEVEN);
        assertThat(dealer.isDrawable()).isFalse();
    }

    @Test
    @DisplayName("딜러는 2장보다 더 받은 카드 개수를 반환한다.")
    void findHitCount() {
        Dealer dealer = new Dealer(CLOVER_TEN, CLOVER_SEVEN, CLOVER_SEVEN);
        assertThat(dealer.findHitCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러는 처음 받은 카드만 보여줄 수 있다..")
    void openCardFirst() {
        Dealer dealer = new Dealer(CLOVER_TEN, CLOVER_SEVEN);
        assertThat(dealer.openCardFirst().get(0)).isEqualTo(CLOVER_TEN);
    }
}