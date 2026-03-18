package domain.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandStateTest {
    @Test
    @DisplayName("초기 2장으로 21이면 BLACKJACK 상태다")
    void blackjackWithInitialCards() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand.addCard(new Card(Suit.HEART, Rank.KING));
        HandState handState = hand.getHandState();

        assertEquals(HandState.BLACKJACK, handState);
        assertTrue(handState.isBlackjack());
    }

    @Test
    @DisplayName("초기 2장이 아닌 21점은 HIT 상태다")
    void hitWithNonInitialCards() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADE, Rank.SEVEN));
        hand.addCard(new Card(Suit.HEART, Rank.SEVEN));
        hand.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        HandState handState = hand.getHandState();

        assertEquals(HandState.HIT, handState);
        assertTrue(handState.isHit());
        assertFalse(handState.isBlackjack());
    }

    @Test
    @DisplayName("21점을 초과하면 BUST 상태다")
    void bustState() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADE, Rank.KING));
        hand.addCard(new Card(Suit.HEART, Rank.QUEEN));
        hand.addCard(new Card(Suit.DIAMOND, Rank.TWO));
        HandState handState = hand.getHandState();

        assertEquals(HandState.BUST, handState);
        assertTrue(handState.isBust());
    }

    @Test
    @DisplayName("HIT 상태에서 동점이면 무승부다")
    void drawWhenSameScoreInHit() {
        Outcome outcome = HandState.HIT.against(HandState.HIT, 20, 20);

        assertEquals(Outcome.DRAW, outcome);
    }

    @Test
    @DisplayName("딜러가 BLACKJACK이면 플레이어 21(비-Blackjack)은 패배한다")
    void loseWhenDealerBlackjackAndPlayerTwentyOneButNotBlackjack() {
        Outcome outcome = HandState.HIT.against(HandState.BLACKJACK, 21, 21);
        assertEquals(Outcome.LOSE, outcome);
    }
}
