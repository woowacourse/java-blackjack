import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
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

    @Test
    void is_dealer_done_when_total_score_is_17_or_more() {
        Dealer dealer1 = new Dealer();
        dealer1.receiveOneCard(new Card("10", "하트"));
        dealer1.receiveOneCard(new Card("7", "스페이드"));
        assertThat(dealer1.isDealerDone()).isEqualTo(true);

        Dealer dealer2 = new Dealer();
        dealer2.receiveOneCard(new Card("2", "하트"));
        dealer2.receiveOneCard(new Card("7", "스페이드"));
        assertThat(dealer2.isDealerDone()).isEqualTo(false);
    }
}
