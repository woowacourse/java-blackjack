package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("카드 한 장을 받는다.")
    void getCard(){
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.CLOVER, Denomination.FIVE));

        assertThat(dealer.cards().size()).isEqualTo(1);
    }
}
