package blackjack.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.Fixtures;
import blackjack.domain.card.PlayingCards;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @Test
    @DisplayName("ready 기뽑기 상태 확인: ready")
    void checkDrawReady() {
        PlayingCards playingCards = new PlayingCards();
        Ready ready = new Ready(playingCards);

        assertThat(ready.draw(Fixtures.HEART_ACE).getClass()).isEqualTo(Ready.class);
    }

    @Test
    @DisplayName("ready 뽑기 상태 확인: hit")
    void checkDrawHit() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.addCard(Fixtures.SPADE_TWO);
        Ready ready = new Ready(playingCards);

        assertThat(ready.draw(Fixtures.HEART_ACE).getClass()).isEqualTo(Hit.class);
    }

    @Test
    @DisplayName("ready 뽑기 상태 확인: blackjack")
    void checkDrawBlackjack() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.addCard(Fixtures.SPADE_ACE);
        Ready ready = new Ready(playingCards);

        assertThat(ready.draw(Fixtures.SPADE_JACK).getClass()).isEqualTo(Blackjack.class);
    }
}
