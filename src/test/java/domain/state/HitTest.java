package domain.state;

import domain.Hand;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import static domain.state.StateFixtures.handOf;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HitTest {

    @Test
    void draw후_21초과면_Bust로_전이한다() {
        Hit hit = new Hit(handOf(
                new Card(Suit.CLUBS, Rank.KING),
                new Card(Suit.DIAMONDS, Rank.QUEEN)
        ));

        ParticipantState next = hit.draw(new Card(Suit.HEARTS, Rank.NUM2));

        assertTrue(next.isBust());
    }

    @Test
    void draw후_두장합21이면_BlackJack으로_전이한다() {
        Hit hit = new Hit(handOf(new Card(Suit.CLUBS, Rank.ACE)));

        ParticipantState next = hit.draw(new Card(Suit.DIAMONDS, Rank.KING));

        assertTrue(next.isBlackJack());
    }

    @Test
    void draw후_세장이상_21이면_Stay로_전이한다() {
        Hit hit = new Hit(handOf(
                new Card(Suit.CLUBS, Rank.NUM5),
                new Card(Suit.DIAMONDS, Rank.NUM6)
        ));

        ParticipantState next = hit.draw(new Card(Suit.HEARTS, Rank.NUM10));

        assertAll(
                () -> assertTrue(next.isFinished()),
                () -> assertFalse(next.isBlackJack()),
                () -> assertFalse(next.isBust())
        );
    }

    @Test
    void draw후_21미만이면_Hit_상태를_유지한다() {
        Hit hit = new Hit(handOf(new Card(Suit.CLUBS, Rank.NUM2)));

        ParticipantState next = hit.draw(new Card(Suit.DIAMONDS, Rank.NUM3));

        assertSame(hit, next);
    }

    @Test
    void onStay호출시_Stay로_전이한다() {
        Hit hit = new Hit(new Hand());

        ParticipantState next = hit.onStay();

        assertAll(
                () -> assertTrue(next.isFinished()),
                () -> assertFalse(next.isBlackJack()),
                () -> assertFalse(next.isBust())
        );
    }

    @Test
    void Hit상태에서는_승패를_계산할수없다() {
        Hit hit = new Hit(new Hand());
        ParticipantState dealerState = new Stay(handOf(
                new Card(Suit.CLUBS, Rank.NUM10),
                new Card(Suit.HEARTS, Rank.NUM7)
        ));

        assertThrows(IllegalStateException.class, () -> hit.calculateWinningStatus(dealerState));
    }

    @Test
    void Hit상태는_종료상태가_아니다() {
        Hit hit = new Hit(new Hand());

        assertAll(
                () -> assertFalse(hit.isFinished()),
                () -> assertFalse(hit.isBlackJack()),
                () -> assertFalse(hit.isBust())
        );
    }
}
