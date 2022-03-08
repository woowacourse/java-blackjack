package model;

import static model.CardFace.ACE;
import static model.CardFace.TWO;
import static model.CardSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    void getCard() {
        final Participator dealer = new Dealer();
        final Card firstCard = new Card(SPADE, ACE);
        final Card secondCard = new Card(SPADE, TWO);
        dealer.receiveCard(firstCard);
        dealer.receiveCard(secondCard);
        assertThat(dealer.getCards()).containsOnly(secondCard);
    }
}
