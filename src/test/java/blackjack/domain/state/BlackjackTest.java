package blackjack.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.Fixtures;
import blackjack.domain.card.PlayingCards;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.result.Match;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackTest {

    @Test
    @DisplayName("블랙잭 확인: 경쟁자도 블랙잭")
    void checkBlackjackDraw() {
        Player guest = new Guest("guest", new Ready(), 100);
        PlayingCards playingCards = new PlayingCards();

        guest.getState().draw(Fixtures.SPADE_JACK);
        guest.getState().draw(Fixtures.HEART_ACE);
        Blackjack blackjack = new Blackjack(playingCards);

        assertThat(blackjack.matchResult(guest)).isEqualTo(Match.DRAW);
    }

    @Test
    @DisplayName("블랙잭 확인: 해당 플레이어만 블랙잭")
    void checkBlackjackOnly() {
        Player guest = new Guest("guest", new Ready(), 100);
        PlayingCards playingCards = new PlayingCards();

        playingCards.addCard(Fixtures.SPADE_JACK);
        playingCards.addCard(Fixtures.HEART_ACE);
        guest.getState().draw(Fixtures.SPADE_JACK);
        Blackjack blackjack = new Blackjack(playingCards);

        assertThat(blackjack.matchResult(guest)).isEqualTo(Match.WIN_BLACKJACK);
    }
}
