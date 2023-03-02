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
    void addDealerCardsTest() {
        Dealer dealer = new Dealer();
        Card card = new Card(CardSuit.HEART, CardNumber.ACE);

        dealer.addCard(card);

        assertThat(dealer.getCards()).contains(card);
    }

    @Test
    @DisplayName("딜러는 처음에 카드를 한 장만 보여준다")
    void showFirstCardTest() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(CardSuit.HEART, CardNumber.ACE);
        Card card2 = new Card(CardSuit.HEART, CardNumber.ACE);

        dealer.addCard(card1);
        dealer.addCard(card2);

        assertThat(dealer.getFirstCard()).isEqualTo(card1);
    }
}
