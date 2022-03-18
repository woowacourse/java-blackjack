package model;

import static model.card.CardFace.ACE;
import static model.card.CardFace.TWO;
import static model.card.CardSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import model.card.Card;
import model.card.CardFace;
import model.participator.Dealer;
import model.participator.Participator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DealerTest {
    @Test
    void getCard() {
        final Participator dealer = new Dealer();
        final Card firstCard = new Card(SPADE, ACE);
        final Card secondCard = new Card(SPADE, TWO);
        dealer.receiveCard(firstCard);
        dealer.receiveCard(secondCard);

        assertThat(dealer.getCards()).containsOnly(firstCard);
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE,FIVE,true", "ACE,SIX,false"})
    void canReceiveCard(CardFace face1, CardFace face2, boolean expected) {
        final Participator dealer = new Dealer();
        final Card firstCard = new Card(SPADE, face1);
        final Card secondCard = new Card(SPADE, face2);

        dealer.receiveCard(firstCard);
        dealer.receiveCard(secondCard);

        assertThat(dealer.canReceiveCard()).isEqualTo(expected);
    }
}
