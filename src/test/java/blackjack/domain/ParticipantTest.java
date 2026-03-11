package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ParticipantTest {
    @Test
    void 참여자는_카드를_받을_수_있다() {
        Participant participant = new Player("이산", new Hand());
        Deck deck = new Deck();
        participant.receiveCard(deck.draw());
    }

    @Test
    void 참여자가_히트를_할_수_있는_경우_플레이어() {
        Hand hand = new Hand();
        hand.addCard(new Card(CardPoint.KING, CardPattern.SPADE));
        hand.addCard(new Card(CardPoint.JACK, CardPattern.HEART));
        Participant player = new Player("이산", hand);
        assertTrue(player.canHit());
    }

    @Test
    void 참여자가_히트를_할_수_없는_경우_플레이어() {
        Hand hand = new Hand();
        hand.addCard(new Card(CardPoint.KING, CardPattern.SPADE));
        hand.addCard(new Card(CardPoint.TWO, CardPattern.HEART));
        hand.addCard(new Card(CardPoint.NINE, CardPattern.DIAMOND));
        Participant player = new Player("이산", hand);
        assertFalse(player.canHit());
    }

    @Test
    void 참여자가_히트를_할_수_있는_경우_딜러() {
        Hand hand = new Hand();
        hand.addCard(new Card(CardPoint.KING, CardPattern.SPADE));
        hand.addCard(new Card(CardPoint.SIX, CardPattern.HEART));
        Participant dealer = new Dealer(hand);
        assertTrue(dealer.canHit());
    }

    @Test
    void 참여자가_히트를_할_수_없는_경우_딜러() {
        Hand hand = new Hand();
        hand.addCard(new Card(CardPoint.KING, CardPattern.SPADE));
        hand.addCard(new Card(CardPoint.SEVEN, CardPattern.HEART));
        Participant dealer = new Dealer(hand);
        assertFalse(dealer.canHit());
    }
}
