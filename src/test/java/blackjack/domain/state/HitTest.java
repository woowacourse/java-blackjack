package blackjack.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.Fixtures;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCards;
import blackjack.domain.card.Suit;

import static org.assertj.core.api.Assertions.assertThat;

class HitTest {

    @Test
    @DisplayName("hit 뽑기 상태 확인: hit")
    void checkDrawHit() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.addCard(Fixtures.SPADE_TWO);
        playingCards.addCard(Fixtures.SPADE_ACE);
        Hit hit = new Hit(playingCards);

        assertThat(hit.draw(new PlayingCard(Suit.SPADE, Denomination.FOUR)).getClass()).isEqualTo(Hit.class);
    }

    @Test
    @DisplayName("hit 뽑기 상태 확인: stay")
    void checkDrawStay() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.addCard(Fixtures.SPADE_EIGHT);
        playingCards.addCard(Fixtures.SPADE_TWO);
        Hit hit = new Hit(playingCards);

        assertThat(hit.draw(Fixtures.CLUB_ACE).getClass()).isEqualTo(Stay.class);
    }

    @Test
    @DisplayName("hit 뽑기 상태 확인: bust")
    void checkDrawBust() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.addCard(Fixtures.SPADE_EIGHT);
        playingCards.addCard(Fixtures.SPADE_NINE);
        Hit hit = new Hit(playingCards);

        assertThat(hit.draw(Fixtures.SPADE_JACK).getClass()).isEqualTo(Bust.class);
    }
}
