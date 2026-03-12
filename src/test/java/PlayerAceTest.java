import domain.Card;
import domain.Hand;
import domain.Rank;
import domain.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayerAceTest {
    @Test
    @DisplayName("에이스가 포함된 점수는 카드 추가 시 자동 보정된다")
    void checkBustWithOneAce() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand.addCard(new Card(Suit.HEART, Rank.NINE));
        hand.addCard(new Card(Suit.CLUB, Rank.FIVE));

        assertFalse(hand.checkBust());
        assertEquals(15, hand.getScore().getScore());
    }

    @Test
    @DisplayName("에이스 여러 장도 카드 추가 시점에 점수가 정상 보정된다")
    void checkBustWithManyAces() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand.addCard(new Card(Suit.HEART, Rank.ACE));
        hand.addCard(new Card(Suit.CLUB, Rank.NINE));
        hand.addCard(new Card(Suit.DIAMOND, Rank.KING));

        assertFalse(hand.checkBust());
        assertEquals(21, hand.getScore().getScore());
    }
}
