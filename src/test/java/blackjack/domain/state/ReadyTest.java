package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReadyTest {

    private static final Betting betting = new Betting(1000);

    @DisplayName("draw 를 한 번 실행하여 Ready 상태가 되는 것을 확인한다.")
    @Test
    void draw_ready() {
        State ready = new Ready(betting);

        assertThat(ready.draw(Card.of(Denomination.KING, Suit.SPADE))).isInstanceOf(Ready.class);
    }

    @DisplayName("draw 를 두 번 실행하여 Hit 상태가 되는 것을 확인한다.")
    @Test
    void draw_hit() {
        State ready = new Ready(betting);
        ready = ready.draw(Card.of(Denomination.KING, Suit.SPADE));

        assertThat(ready.draw(Card.of(Denomination.KING, Suit.SPADE))).isInstanceOf(Hit.class);
    }

    @DisplayName("draw 를 두 번 실행하여 Blackjack 상태가 되는 것을 확인한다.")
    @Test
    void draw_blackjack() {
        State ready = new Ready(betting);
        ready = ready.draw(Card.of(Denomination.KING, Suit.SPADE));

        assertThat(ready.draw(Card.of(Denomination.ACE, Suit.SPADE))).isInstanceOf(Blackjack.class);
    }
}