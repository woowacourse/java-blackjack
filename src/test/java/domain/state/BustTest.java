package domain.state;

import domain.Hand;
import domain.WinningStatus;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import static domain.state.StateFixtures.handOf;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BustTest {

    @Test
    void Bust상태에서는_draw를_호출하면_예외가_발생한다() {
        Bust bust = new Bust(new Hand());
        Card card = new Card(Suit.CLUBS, Rank.ACE);

        assertThrows(IllegalStateException.class, () -> bust.draw(card));
    }

    @Test
    void Bust상태에서_onStay는_자기자신을_반환한다() {
        Bust bust = new Bust(new Hand());

        assertSame(bust, bust.onStay());
    }

    @Test
    void Bust상태_플래그를_정확히_반환한다() {
        Bust bust = new Bust(new Hand());

        assertAll(
                () -> assertTrue(bust.isFinished()),
                () -> assertTrue(bust.isBust()),
                () -> assertFalse(bust.isBlackJack())
        );
    }

    @Test
    void Bust상태는_상대상태와_무관하게_항상_패배다() {
        Bust bust = new Bust(handOf(
                new Card(Suit.CLUBS, Rank.KING),
                new Card(Suit.DIAMONDS, Rank.QUEEN),
                new Card(Suit.HEARTS, Rank.NUM2)
        ));
        ParticipantState dealerStay = new Stay(handOf(
                new Card(Suit.SPADES, Rank.NUM10),
                new Card(Suit.HEARTS, Rank.NUM9)
        ));
        ParticipantState dealerBlackJack = new BlackJack(handOf(
                new Card(Suit.SPADES, Rank.ACE),
                new Card(Suit.SPADES, Rank.KING)
        ));
        ParticipantState dealerBust = new Bust(handOf(
                new Card(Suit.CLUBS, Rank.KING),
                new Card(Suit.DIAMONDS, Rank.QUEEN),
                new Card(Suit.HEARTS, Rank.NUM2)
        ));

        assertAll(
                () -> assertEquals(WinningStatus.LOSE, bust.calculateWinningStatus(dealerStay)),
                () -> assertEquals(WinningStatus.LOSE, bust.calculateWinningStatus(dealerBlackJack)),
                () -> assertEquals(WinningStatus.LOSE, bust.calculateWinningStatus(dealerBust))
        );
    }
}
