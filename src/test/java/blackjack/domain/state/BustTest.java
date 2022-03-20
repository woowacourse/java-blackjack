package blackjack.domain.state;

import blackjack.domain.Fixtures;
import blackjack.domain.card.PlayingCards;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.result.Match;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BustTest {

    @Test
    @DisplayName("bust 확인: 경쟁자도 bust")
    void checkBustDraw() {
        Player guest = new Guest("guest", new Ready(), 100);
        PlayingCards playingCards = new PlayingCards();

        guest.getState().draw(Fixtures.SPADE_JACK);
        guest.getState().draw(Fixtures.SPADE_EIGHT);
        guest.getState().draw(Fixtures.SPADE_NINE);
        Bust bust = new Bust(playingCards);

        assertThat(bust.matchResult(guest)).isEqualTo(Match.DRAW);
    }

    @Test
    @DisplayName("bust 확인: 경쟁자가 blackjack")
    void checkBustAndLoseBlackjack() {
        Player guest = new Guest("guest", new Ready(), 100);
        PlayingCards playingCards = new PlayingCards();

        guest.getState().draw(Fixtures.SPADE_JACK);
        guest.getState().draw(Fixtures.HEART_ACE);
        Bust bust = new Bust(playingCards);

        assertThat(bust.matchResult(guest)).isEqualTo(Match.LOSE_BLACKJACK);
    }

    @Test
    @DisplayName("bust 확인: 경쟁자가 bust가 아니라면, 점수에 상관없이 패배")
    void checkBustLose() {
        Player guest = new Guest("guest", new Ready(), 100);
        PlayingCards playingCards = new PlayingCards();

        guest.getState().draw(Fixtures.SPADE_JACK);
        guest.getState().draw(Fixtures.SPADE_NINE);
        Bust bust = new Bust(playingCards);

        assertThat(bust.matchResult(guest)).isEqualTo(Match.LOSE);
    }
}
