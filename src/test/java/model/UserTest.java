package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    Deck deck = new Deck(CardFactory.createCardList());
    CardHand cardHand = deck.initialDraw();
    @Test
    @DisplayName("딜러 이름 테스트")
    void dealer_Name() {
        User dealer = new Dealer(cardHand);
        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("카드 셋팅")
    void setCard_Test() {
        User dealer = new Dealer(cardHand);
        dealer.drawCard(new Card(Symbol.ACE, Type.DIAMOND));
        assertThat(dealer.getCardHand().contains(new Card(Symbol.ACE, Type.DIAMOND))).isTrue();
    }
}
