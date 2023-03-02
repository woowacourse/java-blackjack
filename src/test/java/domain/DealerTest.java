package domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("이름이 딜러인 딜러를 생성한다.")
    void createDealer() {
        Dealer dealer = new Dealer();
        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("카드를 받는다.")
    void addCard() {
        Dealer dealer = new Dealer();

        Card card = new Card(CardNumber.ACE,CardPattern.SPADE);
        dealer.addCard(card);
        assertThat(dealer.getCard(0)).isEqualTo(card);
    }


}
