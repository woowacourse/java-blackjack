package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("합계가 17 이상이 될 때 까지 카드를 뽑는다.")
    @Test
    void drawTest() {
        // given
        Dealer dealer = new Dealer();
        Decks decks = new Decks();

        // when
        dealer.hit(decks);

        // then
        assertThat(dealer.calculateTotalScore()).isGreaterThanOrEqualTo(17);
    }
}
