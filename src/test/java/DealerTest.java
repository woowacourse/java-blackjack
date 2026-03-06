import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void calculate_dealer_total_score() {
        Dealer dealer1 = new Dealer();
        dealer1.receiveOneCard(new Card("A", "하트"));
        dealer1.receiveOneCard(new Card("Q", "스페이드"));
        assertThat(dealer1.calculateTotalScore()).isEqualTo(21);

        Dealer dealer2 = new Dealer();
        dealer2.receiveOneCard(new Card("3", "하트"));
        dealer2.receiveOneCard(new Card("10", "스페이드"));
        assertThat(dealer2.calculateTotalScore()).isEqualTo(13);
    }
}
