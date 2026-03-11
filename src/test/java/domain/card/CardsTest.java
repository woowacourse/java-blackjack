package domain.card;

import domain.Rank;
import domain.Score;
import domain.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static domain.Rank.BLACKJACK_MAX_NUMBER;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CardsTest {
    @Test
    void 카드_더미가_정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> new Hand());
    }

    @Test
    void 카드가_정상적으로_추가되어야_한다() {
        //given
        Card card = new Card(Suit.SPADE, Rank.ACE);
        Hand hand = new Hand();

        //when
        hand.add(card);

        //then
        Assertions.assertEquals(hand.size(), 1);
    }

    @Test
    void 카드_합계가_정상적으로_수행되어야_한다() {
        //given
        Card card1 = new Card(Suit.SPADE, Rank.ACE);
        Card card2 = new Card(Suit.SPADE, Rank.TEN);
        Hand hand = new Hand();

        //when
        hand.add(card1);
        hand.add(card2);

        //then
        Assertions.assertEquals(hand.getTotalSum(), BLACKJACK_MAX_NUMBER);
    }

    @Test
    void 에이스가_여러개인_경우_카드_합계가_정상적으로_수행되어야_한다() {
        //given
        Card card1 = new Card(Suit.SPADE, Rank.ACE);
        Card card2 = new Card(Suit.HEART, Rank.ACE);
        Card card3 = new Card(Suit.CLUB, Rank.ACE);
        Card card4 = new Card(Suit.SPADE, Rank.TEN);
        Hand hand = new Hand();

        //when
        hand.add(card1);
        hand.add(card2);
        hand.add(card3);
        hand.add(card4);

        //then
        Assertions.assertEquals(hand.getTotalSum(), new Score(13));
    }
}
