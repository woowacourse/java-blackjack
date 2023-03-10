package domain.status;

import static domain.Fixtures.ACE_CLOVER;
import static domain.Fixtures.TEN_CLOVER;
import static domain.Fixtures.TEN_HEART;
import static domain.Fixtures.TEN_SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.Test;

class HitTest {

    @Test
    void drawBust() {
        Cards cards = new Cards(List.of(TEN_CLOVER, TEN_HEART));
        Hit hit = new Hit(cards);
        assertThat(hit.draw(TEN_SPADE)).isInstanceOf(Bust.class);
    }

    @Test
    void drawHit() {
        Cards cards = new Cards(List.of(TEN_CLOVER, TEN_HEART));
        Hit hit = new Hit(cards);
        assertThat(hit.draw(ACE_CLOVER)).isInstanceOf(Hit.class);
    }

    @Test
    void selectStand() {
        Hit hit = new Hit(new Cards());
        assertThat(hit.selectStand()).isInstanceOf(Stand.class);
    }
}
