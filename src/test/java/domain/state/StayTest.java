package domain.state;

import domain.Hand;
import domain.WinningStatus;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import static domain.state.StateFixtures.handOf;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StayTest {

    @Test
    void Stay상태에서는_draw를_호출하면_예외가_발생한다() {
        Stay stay = new Stay(new Hand());
        Card card = new Card(Suit.CLUBS, Rank.ACE);

        assertThatThrownBy(() -> stay.draw(card))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("STAY 상태에서는 hit할 수 없습니다.");
    }

    @Test
    void Stay상태에서_onStay는_자기자신을_반환한다() {
        Stay stay = new Stay(new Hand());

        assertSame(stay, stay.onStay());
    }

    @Test
    void Stay상태_플래그를_정확히_반환한다() {
        Stay stay = new Stay(new Hand());

        assertAll(
                () -> assertTrue(stay.isFinished()),
                () -> assertFalse(stay.isBlackJack()),
                () -> assertFalse(stay.isBust())
        );
    }

    @Test
    void 상대가_버스트면_승리한다() {
        Stay stay = new Stay(handOf(
                new Card(Suit.CLUBS, Rank.NUM10),
                new Card(Suit.HEARTS, Rank.NUM8)
        ));
        ParticipantState dealerState = new Bust(handOf(
                new Card(Suit.CLUBS, Rank.KING),
                new Card(Suit.DIAMONDS, Rank.QUEEN),
                new Card(Suit.HEARTS, Rank.NUM2)
        ));

        WinningStatus result = stay.calculateWinningStatus(dealerState);

        assertEquals(WinningStatus.WIN, result);
    }

    @Test
    void 상대가_블랙잭이면_패배한다() {
        Stay stay = new Stay(handOf(
                new Card(Suit.CLUBS, Rank.NUM10),
                new Card(Suit.HEARTS, Rank.NUM8)
        ));
        ParticipantState dealerState = new BlackJack(handOf(
                new Card(Suit.SPADES, Rank.ACE),
                new Card(Suit.SPADES, Rank.KING)
        ));

        WinningStatus result = stay.calculateWinningStatus(dealerState);

        assertEquals(WinningStatus.LOSE, result);
    }

    @Test
    void 점수가_더_크면_승리한다() {
        Stay stay = new Stay(handOf(
                new Card(Suit.CLUBS, Rank.NUM10),
                new Card(Suit.HEARTS, Rank.NUM9)
        ));
        ParticipantState dealerState = new Stay(handOf(
                new Card(Suit.SPADES, Rank.NUM10),
                new Card(Suit.DIAMONDS, Rank.NUM8)
        ));

        WinningStatus result = stay.calculateWinningStatus(dealerState);

        assertEquals(WinningStatus.WIN, result);
    }

    @Test
    void 점수가_같으면_무승부다() {
        Stay stay = new Stay(handOf(
                new Card(Suit.CLUBS, Rank.NUM10),
                new Card(Suit.HEARTS, Rank.NUM8)
        ));
        ParticipantState dealerState = new Stay(handOf(
                new Card(Suit.SPADES, Rank.NUM9),
                new Card(Suit.DIAMONDS, Rank.NUM9)
        ));

        WinningStatus result = stay.calculateWinningStatus(dealerState);

        assertEquals(WinningStatus.DRAW, result);
    }

    @Test
    void 점수가_더_작으면_패배한다() {
        Stay stay = new Stay(handOf(
                new Card(Suit.CLUBS, Rank.NUM10),
                new Card(Suit.HEARTS, Rank.NUM7)
        ));
        ParticipantState dealerState = new Stay(handOf(
                new Card(Suit.SPADES, Rank.NUM10),
                new Card(Suit.DIAMONDS, Rank.NUM8)
        ));

        WinningStatus result = stay.calculateWinningStatus(dealerState);

        assertEquals(WinningStatus.LOSE, result);
    }
}
