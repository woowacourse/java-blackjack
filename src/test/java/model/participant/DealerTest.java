package model.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.deck.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        List<Card> testCards = new ArrayList<>(List.of(
                new Card(CardNumber.FOUR, CardShape.DIAMOND),
                new Card(CardNumber.THREE, CardShape.CLOVER),
                new Card(CardNumber.SIX, CardShape.SPADE),
                new Card(CardNumber.FIVE, CardShape.HEART)
        ));
        Deck deck = new Deck(testCards);

        dealer = new Dealer(deck);
    }

    @Test
    void dealerInitialCardsCount() {
        assertEquals(2, dealer.getCards().getCardsSize() - dealer.getAdditionalDrawCount());
    }

    @Test
    void dealerDraws_AdditionalCards() {
        assertEquals(2, dealer.getAdditionalDrawCount());
    }

    @Test
    void dealerStopsDrawing_AtThreshold() {
        assertTrue(dealer.calculateScore() > 16);
    }

    @Test
    void dealer_GetFirstCard() {
        assertEquals(CardNumber.FIVE, dealer.getFirstCard().number());
    }
}