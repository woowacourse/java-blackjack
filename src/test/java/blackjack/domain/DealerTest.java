package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @DisplayName("Dealer 객체를 생성한다.")
    @Test
    public void createDealer() {
        Dealer dealer = new Dealer();

        assertThat(dealer).isInstanceOf(Dealer.class);
    }
}
