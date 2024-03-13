package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("16 넘기 전까지 카드를 드로우한 후 드로우한 횟수를 반환한다")
    void drawAndGetCount() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        Assertions.assertThat(dealer.drawAndGetCount(deck))
                .isEqualTo(2);
    }
}
