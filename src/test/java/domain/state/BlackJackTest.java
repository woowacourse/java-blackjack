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

class BlackJackTest {

    @Test
    void BlackJack상태에서는_draw를_호출하면_예외가_발생한다() {
        BlackJack blackJack = new BlackJack(new Hand());
        Card card = new Card(Suit.CLUBS, Rank.ACE);

        assertThrows(IllegalStateException.class, () -> blackJack.draw(card));
    }

    @Test
    void BlackJack상태에서_onStay는_자기자신을_반환한다() {
        BlackJack blackJack = new BlackJack(new Hand());

        assertSame(blackJack, blackJack.onStay());
    }

    @Test
    void BlackJack상태_플래그를_정확히_반환한다() {
        BlackJack blackJack = new BlackJack(new Hand());

        assertAll(
                () -> assertTrue(blackJack.isFinished()),
                () -> assertTrue(blackJack.isBlackJack()),
                () -> assertFalse(blackJack.isBust())
        );
    }

    @Test
    void 상대도_블랙잭이면_무승부다() {
        BlackJack blackJack = new BlackJack(handOf(
                new Card(Suit.CLUBS, Rank.ACE),
                new Card(Suit.DIAMONDS, Rank.KING)
        ));
        ParticipantState dealerState = new BlackJack(handOf(
                new Card(Suit.SPADES, Rank.ACE),
                new Card(Suit.HEARTS, Rank.KING)
        ));

        WinningStatus result = blackJack.calculateWinningStatus(dealerState);

        assertEquals(WinningStatus.DRAW, result);
    }

    @Test
    void 상대가_블랙잭이_아니면_블랙잭_승리다() {
        BlackJack blackJack = new BlackJack(handOf(
                new Card(Suit.CLUBS, Rank.ACE),
                new Card(Suit.DIAMONDS, Rank.KING)
        ));
        ParticipantState dealerState = new Stay(handOf(
                new Card(Suit.SPADES, Rank.NUM10),
                new Card(Suit.HEARTS, Rank.NUM9)
        ));

        WinningStatus result = blackJack.calculateWinningStatus(dealerState);

        assertEquals(WinningStatus.BLACKJACK_WIN, result);
    }
}
