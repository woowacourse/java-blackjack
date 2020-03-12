package controller;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.card.CardDeck;
import domain.participant.Dealer;

class BlackJackGameControllerTest {

    @Test
    void dealerHit() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();

        BlackJackGameController.dealerHit(dealer, cardDeck);

        assertThat(dealer.calculateScore() > 16).isTrue();
    }
}