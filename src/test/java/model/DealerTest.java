package model;

import model.card.Card;
import model.card.Deck;
import model.user.Dealer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static model.card.Shape.DIAMOND;
import static model.card.Value.KING;
import static model.card.Value.SEVEN;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void init() {
        dealer = new Dealer();
    }
    @Test
    @DisplayName("딜러의 카드 총 합이 16 이하인지 판단한다.")
    void canReceiveCardTest() {
        // given
        dealer.receiveCard(new Card(DIAMOND, SEVEN));
        dealer.receiveCard(new Card(DIAMOND, KING));

        // when, then
        assertThat(dealer.canReceiveCard()).isFalse();
    }

    @DisplayName("receiveInitialCards가 두 장의 카드를 주는지 확인한다.")
    @Test
    void receiveInitialCards() {
        // given
        dealer.receiveInitialCards(Deck.create());

        // when, then
        assertThat(dealer.getHand().getCards()).hasSize(2);
    }
}
