package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("딜러를 생성하면 빈 카드들이 생성된다.")
    void createDealerTest() {
        Dealer dealer = new Dealer();

        assertThat(dealer.getCards()).hasSize(0);
    }

    @Test
    @DisplayName("카드를 받아 딜러 카드에 추가한다")
    void addPlayerCardsTest() {
        Dealer dealer = new Dealer();
        Card card = new Card(CardSuit.HEART, CardNumber.ACE);

        dealer.addCard(card);

        assertThat(dealer.getCards()).contains(card);
    }
}
