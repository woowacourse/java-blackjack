package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    @Test
    @DisplayName("딜러의 점수가 16점 이하면 반드시 카드 한장을 받아온다.")
    void canReceiveCard() {
        // given
        Dealer dealer = new Dealer();
        // when & then
        assertThat(dealer.canReceive(15)).isTrue();
    }

    @Test
    @DisplayName("딜러의 점수가 17점 이상이면 추가로 받아올 수 없다.")
    void cantReceiveCard() {
        // given
        Dealer dealer = new Dealer();
        // when & then
        assertThat(dealer.canReceive(17)).isFalse();
    }
}