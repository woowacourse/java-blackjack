import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BustTest {

    @Test
    void player_busts_when_sum_exceeds_21() {
        Player player = new Player("Alice");
        player.receiveOneCard(new Card("2", "하트"));
        player.receiveOneCard(new Card("Q", "스페이드"));
        player.receiveOneCard(new Card("10", "클로버"));

        assertThat(player.isBust()).isEqualTo(true);
    }

    @Test
    void dealer_busts_when_sum_exceeds_21() {
        Dealer dealer = new Dealer();
        dealer.receiveOneCard(new Card("2", "하트"));
        dealer.receiveOneCard(new Card("Q", "스페이드"));
        dealer.receiveOneCard(new Card("10", "클로버"));

        assertThat(dealer.isBust()).isEqualTo(true);
    }

    @Test
    void player_not_busts_when_sum_21_or_less() {
        Player player = new Player("Alice");
        player.receiveOneCard(new Card("A", "하트"));
        player.receiveOneCard(new Card("Q", "스페이드"));
        player.receiveOneCard(new Card("10", "클로버"));

        assertThat(player.isBust()).isEqualTo(false);
    }

    @Test
    void dealer_not_busts_when_sum_21_or_less() {
        Dealer dealer = new Dealer();
        dealer.receiveOneCard(new Card("A", "하트"));
        dealer.receiveOneCard(new Card("Q", "스페이드"));
        dealer.receiveOneCard(new Card("10", "클로버"));

        assertThat(dealer.isBust()).isEqualTo(false);
    }


}
