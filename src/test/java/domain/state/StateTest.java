package domain.state;

import domain.Hand;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StateTest {
    @Test
    void 종료상태에서는_draw를_호출하면_예외가_발생한다() {
        Card card = new Card(Suit.CLUBS, Rank.ACE);

        assertAll(
                () -> assertThrows(IllegalStateException.class, () -> new Stay(new Hand()).draw(card)),
                () -> assertThrows(IllegalStateException.class, () -> new Bust(new Hand()).draw(card)),
                () -> assertThrows(IllegalStateException.class, () -> new BlackJack(new Hand()).draw(card))
        );
    }

    @Test
    void 종료상태에서_onStay는_자기자신을_반환한다() {
        PlayerState stay = new Stay(new Hand());
        PlayerState bust = new Bust(new Hand());
        PlayerState blackJack = new BlackJack(new Hand());

        assertAll(
                () -> assertSame(stay, stay.onStay()),
                () -> assertSame(bust, bust.onStay()),
                () -> assertSame(blackJack, blackJack.onStay())
        );
    }

    @Test
    void 종료상태_플래그를_정확히_반환한다() {
        PlayerState stay = new Stay(new Hand());
        PlayerState bust = new Bust(new Hand());
        PlayerState blackJack = new BlackJack(new Hand());

        assertAll(
                () -> assertTrue(stay.isFinished()),
                () -> assertTrue(bust.isFinished()),
                () -> assertTrue(blackJack.isFinished()),
                () -> assertTrue(bust.isBust()),
                () -> assertFalse(bust.isBlackJack()),
                () -> assertTrue(blackJack.isBlackJack()),
                () -> assertFalse(blackJack.isBust())
        );
    }

    @Test
    void draw후_21초과면_Bust로_전이한다() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Rank.KING));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
        Hit hit = new Hit(hand);

        PlayerState next = hit.draw(new Card(Suit.HEARTS, Rank.NUM2));

        assertTrue(next.isBust());
    }

    @Test
    void draw후_두장합21이면_BlackJack으로_전이한다() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Rank.ACE));
        Hit hit = new Hit(hand);

        PlayerState next = hit.draw(new Card(Suit.DIAMONDS, Rank.KING));

        assertTrue(next.isBlackJack());
    }

    @Test
    void draw후_세장이상_21이면_Stay로_전이한다() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Rank.NUM5));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.NUM6));
        Hit hit = new Hit(hand);

        PlayerState next = hit.draw(new Card(Suit.HEARTS, Rank.NUM10));

        assertAll(
                () -> assertTrue(next.isFinished()),
                () -> assertFalse(next.isBlackJack()),
                () -> assertFalse(next.isBust())
        );
    }

    @Test
    void draw후_21미만이면_Hit_상태를_유지한다() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Rank.NUM2));
        Hit hit = new Hit(hand);

        PlayerState next = hit.draw(new Card(Suit.DIAMONDS, Rank.NUM3));

        assertSame(hit, next);
    }

    @Test
    void onStay호출시_Stay로_전이한다() {
        Hit hit = new Hit(new Hand());

        PlayerState next = hit.onStay();

        assertAll(
                () -> assertTrue(next.isFinished()),
                () -> assertFalse(next.isBlackJack()),
                () -> assertFalse(next.isBust())
        );
    }
}
