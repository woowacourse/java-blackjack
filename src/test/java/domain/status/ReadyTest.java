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
    void firstDrawBlackJack() {
        Cards cards = new Cards(List.of(ACE_HEART, KING_HEART));
        Ready ready = new Ready();
        assertThat(ready.firstDraw(cards)).isInstanceOf(BlackJack.class);
    }

    @Test
    void firstDrawHit() {
        Cards cards = new Cards(List.of(KING_CLOVER, KING_SPADE));
        Ready ready = new Ready();
        assertThat(ready.firstDraw(cards)).isInstanceOf(Hit.class);
    }
}
