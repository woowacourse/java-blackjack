package blackjack.domain.player.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HitTest {
    @Test
    @DisplayName("draw 시 점수가 19점이면 여전히 hit 상태")
    void hit() {
        Hit hit = new Hit(Card.of(Suit.DIAMOND, Letter.EIGHT), Card.of(Suit.CLOVER, Letter.JACK));

        assertThat(hit.draw(Card.of(Suit.CLOVER, Letter.ACE))).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("draw 시 bust 되면 bust 상태로 변한다")
    void bust() {
        Hit hit = new Hit(Card.of(Suit.DIAMOND, Letter.KING), Card.of(Suit.DIAMOND, Letter.JACK));

        assertThat(hit.draw(Card.of(Suit.DIAMOND, Letter.JACK))).isInstanceOf(Bust.class);
    }
}
