package domain.status;

import static domain.Fixtures.ACE_HEART;
import static domain.Fixtures.KING_CLOVER;
import static domain.Fixtures.KING_HEART;
import static domain.Fixtures.KING_SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Cards;
import domain.status.end.BlackJack;
import domain.status.initial.Ready;
import domain.status.intermediate.Hit;
import java.util.List;
import org.junit.jupiter.api.Test;

class ReadyTest {
    @Test
    void initialDrawBlackJack() {
        Cards cards = new Cards(List.of(ACE_HEART, KING_HEART));
        Status ready = new Ready();
        assertThat(ready.initialDraw(cards)).isInstanceOf(BlackJack.class);
    }

    @Test
    void initialDrawHit() {
        Cards cards = new Cards(List.of(KING_CLOVER, KING_SPADE));
        Status ready = new Ready();
        assertThat(ready.initialDraw(cards)).isInstanceOf(Hit.class);
    }
}
