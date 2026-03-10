package domain.player;

import domain.card.Card;
import domain.card.TrumpNumber;
import domain.card.TrumpSuit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandCardsTest {

    @Test
    void 일반_카드_합_계산() {
        HandCards handCards = new HandCards();

        handCards.addCard(new Card(TrumpSuit.HEART, TrumpNumber.TEN));
        handCards.addCard(new Card(TrumpSuit.SPADE, TrumpNumber.NINE));

        assertEquals(19, handCards.getCardScoreSum());
    }

    @Test
    void Ace를_11로_계산하는_경우() {
        HandCards handCards = new HandCards();

        handCards.addCard(new Card(TrumpSuit.HEART, TrumpNumber.ACE));
        handCards.addCard(new Card(TrumpSuit.SPADE, TrumpNumber.NINE));

        assertEquals(20, handCards.getCardScoreSum());
    }

    @Test
    void Ace를_1로_계산하는_경우() {
        HandCards handCards = new HandCards();

        handCards.addCard(new Card(TrumpSuit.HEART, TrumpNumber.ACE));
        handCards.addCard(new Card(TrumpSuit.SPADE, TrumpNumber.TEN));
        handCards.addCard(new Card(TrumpSuit.CLUB, TrumpNumber.NINE));

        assertEquals(20, handCards.getCardScoreSum());
    }

    @Test
    void 정확히_21이_되는_경우() {
        HandCards handCards = new HandCards();

        handCards.addCard(new Card(TrumpSuit.HEART, TrumpNumber.ACE));
        handCards.addCard(new Card(TrumpSuit.SPADE, TrumpNumber.TEN));

        assertEquals(21, handCards.getCardScoreSum());
    }
}
