package domain.status;

import static domain.Fixtures.ACE_CLOVER;
import static domain.Fixtures.TEN_CLOVER;
import static domain.Fixtures.TEN_HEART;
import static domain.Fixtures.TEN_SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Cards;
import domain.status.end.Bust;
import domain.status.end.Stand;
import domain.status.intermediate.Hit;
import java.util.List;
import org.junit.jupiter.api.Test;

class HitTest {

    @Test
    void drawBust() {
        Cards cards = new Cards(List.of(TEN_CLOVER, TEN_HEART));
        Status hit = new Hit(cards);
        assertThat(hit.draw(TEN_SPADE)).isInstanceOf(Bust.class);
    }

    @Test
    void drawHit() {
        Cards cards = new Cards(List.of(TEN_CLOVER, TEN_HEART));
        Status hit = new Hit(cards);
        assertThat(hit.draw(ACE_CLOVER)).isInstanceOf(Stand.class);
    }

    @Test
    void selectStand() {
        Status hit = new Hit(new Cards());
        assertThat(hit.selectStand()).isInstanceOf(Stand.class);
    }
}
