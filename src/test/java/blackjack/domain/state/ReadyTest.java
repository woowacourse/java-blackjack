package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReadyTest {

    @DisplayName("draw 를 한 번 실행하여 Ready 상태가 되는 것을 확인한다.")
    @Test
    void draw_ready() {
        State ready = new Ready();

        assertThat(ready.draw(Card.of(Denomination.KING, Suit.SPADE))).isInstanceOf(Ready.class);
    }

    @DisplayName("draw 를 두 번 실행하여 Hit 상태가 되는 것을 확인한다.")
    @Test
    void draw_hit() {
        State ready = new Ready();
        ready = ready.draw(Card.of(Denomination.KING, Suit.SPADE));

        assertThat(ready.draw(Card.of(Denomination.KING, Suit.SPADE))).isInstanceOf(Hit.class);
    }
}