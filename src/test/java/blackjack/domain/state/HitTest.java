package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.game.PlayingCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HitTest {

    private static final PlayingCards playingCards = new PlayingCards();
    private static final Betting betting = new Betting(1000);

    @DisplayName("draw 를 실행하여 Hit 상태가 되는 것을 확인한다.")
    @Test
    void draw_hit() {
        playingCards.add(List.of(Card.of(Denomination.ACE, Suit.SPADE), Card.of(Denomination.ACE, Suit.SPADE)));
        Hit hit = new Hit(playingCards, betting);

        State state = hit.draw(Card.of(Denomination.ACE, Suit.SPADE));

        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("draw 를 실행하여 Bust 상태가 되는 것을 확인한다.")
    @Test
    void draw_bust() {
        playingCards.add(List.of(Card.of(Denomination.KING, Suit.SPADE), Card.of(Denomination.KING, Suit.SPADE)));
        Hit hit = new Hit(playingCards, betting);

        State state = hit.draw(Card.of(Denomination.KING, Suit.SPADE));

        assertThat(state).isInstanceOf(Bust.class);
    }
}