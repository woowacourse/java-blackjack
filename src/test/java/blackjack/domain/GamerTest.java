package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.gamer.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamerTest {

    @Test
    @DisplayName("버스트 체크")
    void checkBustTest() {
        Dealer dealer = Dealer.init();
        while (dealer.getCards().calculateScore() <= 21) {
            dealer.hit();
        }

        assertThat(dealer.isBust()).isTrue();
    }
}