package blackjack.domain.player;

import static blackjack.domain.card.CardSpec.ACE;
import static blackjack.domain.card.CardSpec.SIX;
import static blackjack.domain.card.CardSpec.TEN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("16 이하의 카드를 받았을 때 더 받을 수 있는지 확인")
    void createDealer_drawable() {
        Dealer dealer = Dealer.of(
            TEN.card(),
            SIX.card()
        );

        assertThat(dealer.drawable()).isTrue();
    }

    @Test
    @DisplayName("16 초과의 카드를 받았을 때")
    void createDealer_notDrawable() {
        Dealer dealer = Dealer.of(
            ACE.card(),
            SIX.card()
        );

        assertThat(dealer.drawable()).isFalse();
    }
}