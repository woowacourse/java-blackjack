package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("getName 메소드 테스트")
    @Test
    void getName() {
        User dealer = new Dealer();
        assertThat(dealer.getName()).isEqualTo("딜러");
    }
}