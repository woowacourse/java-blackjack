package domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("이름이 딜러인 딜러를 생성한다.")
    void createDealer() {
        Dealer dealer = new Dealer();
        assertThat(dealer.getName().getName()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("카드를 받는다.")
    void addCard() {
        Dealer dealer = new Dealer();

        Card card = new Card(CardNumber.ACE,CardPattern.SPADE);
        dealer.addCard(card);
        assertThat(dealer.getCard(0)).isEqualTo(card);
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16을 초과하는지 확인하기")
    void isOverStandardTest() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(CardNumber.ACE,CardPattern.SPADE);
        Card card2 = new Card(CardNumber.ACE,CardPattern.SPADE);
        Card card3 = new Card(CardNumber.SEVEN,CardPattern.SPADE);
        dealer.addCard(card1);
        dealer.addCard(card2);
        dealer.addCard(card3);

        assertThat(dealer.isOverStandard()).isTrue();
    }


}
