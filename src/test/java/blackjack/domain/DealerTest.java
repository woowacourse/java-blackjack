package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("카드 한 장을 받는다.")
    void getCard(){
        Dealer dealer = createDealer();

        assertThat(dealer.showCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드를 한 장만 공개한다.")
    void openCard() {
        Dealer dealer = createDealer();
        assertThat(dealer.openCards()).contains(new Card(Suit.CLOVER, Denomination.FIVE));
    }

    @Test
    @DisplayName("카드를 모두 공개한다.")
    void showAllCards() {
        Dealer dealer = createDealer();
        dealer.receiveCard(new Card(Suit.CLOVER, Denomination.SIX));

        assertThat(dealer.showCards()).contains(new Card(Suit.CLOVER, Denomination.FIVE),
                new Card(Suit.CLOVER, Denomination.SIX));
    }

    private Dealer createDealer() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.CLOVER, Denomination.FIVE));
        return dealer;
    }
}
