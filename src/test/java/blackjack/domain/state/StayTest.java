package blackjack.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.Fixtures;
import blackjack.domain.card.PlayingCards;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.result.Match;

import static org.assertj.core.api.Assertions.assertThat;

class StayTest {

    @Test
    @DisplayName("결과 확인: 경쟁자가 blackjack")
    void checkCompetitorBlackjack() {
        Player guest = new Guest("guest", new Ready(), 100);
        PlayingCards playingCards = new PlayingCards();

        guest.getState().draw(Fixtures.SPADE_JACK);
        guest.getState().draw(Fixtures.HEART_ACE);
        Stay stay = new Stay(playingCards);

        assertThat(stay.matchResult(guest)).isEqualTo(Match.LOSE_BLACKJACK);
    }

    @Test
    @DisplayName("결과 확인: 경쟁자와 draw")
    void checkDraw() {
        Player guest = new Guest("guest", new Ready(), 100);
        PlayingCards playingCards = new PlayingCards();
        playingCards.addCard(Fixtures.SPADE_TWO);
        playingCards.addCard(Fixtures.HEART_ACE);

        guest.getState().draw(Fixtures.SPADE_TWO);
        guest.getState().draw(Fixtures.HEART_ACE);
        Stay stay = new Stay(playingCards);

        assertThat(stay.matchResult(guest)).isEqualTo(Match.DRAW);
    }

    @Test
    @DisplayName("결과 확인: win")
    void checkWin() {
        Player guest = new Guest("guest", new Ready(), 100);
        PlayingCards playingCards = new PlayingCards();
        playingCards.addCard(Fixtures.SPADE_TWO);
        playingCards.addCard(Fixtures.SPADE_NINE);

        guest.getState().draw(Fixtures.SPADE_TWO);
        guest.getState().draw(Fixtures.SPADE_EIGHT);
        Stay stay = new Stay(playingCards);

        assertThat(stay.matchResult(guest)).isEqualTo(Match.WIN);
    }

    @Test
    @DisplayName("결과 확인: Lose")
    void checkLose() {
        Player guest = new Guest("guest", new Ready(), 100);
        PlayingCards playingCards = new PlayingCards();
        playingCards.addCard(Fixtures.SPADE_TWO);
        playingCards.addCard(Fixtures.SPADE_EIGHT);

        guest.getState().draw(Fixtures.SPADE_TWO);
        guest.getState().draw(Fixtures.SPADE_NINE);
        Stay stay = new Stay(playingCards);

        assertThat(stay.matchResult(guest)).isEqualTo(Match.LOSE);
    }
}
