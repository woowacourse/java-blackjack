package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DealerTest {

    Deck deck = new Deck();

    @Test
    void 딜러가_카드를_받는다() {

        Dealer dealer = new Dealer();

        dealer.recieveCard(deck);

        assertThat(dealer.getCardCount()).isEqualTo(1);

    }





}
