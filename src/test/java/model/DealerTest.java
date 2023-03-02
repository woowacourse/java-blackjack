package model;

import model.card.Card;
import model.user.Dealer;
import model.user.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static model.card.Shape.DIAMOND;
import static model.card.Value.KING;
import static model.card.Value.SEVEN;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("딜러의 카드 총 합이 16 이하인지 판단한다.")
    void canReceiveCardTest() {
        // given
        final Dealer dealer = new Dealer("딜러", Hand.create());
        dealer.receiveCard(new Card(DIAMOND, SEVEN));
        dealer.receiveCard(new Card(DIAMOND, KING));

        // when, then
        assertThat(dealer.canReceiveCard()).isFalse();
    }

}
