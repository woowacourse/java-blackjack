package blackjack.domain.state.running;

import static blackjack.domain.Fixtures.ACE;
import static blackjack.domain.Fixtures.EIGHT;
import static blackjack.domain.Fixtures.NINE;
import static blackjack.domain.Fixtures.TEN;
import static blackjack.domain.Fixtures.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Fixtures;
import blackjack.domain.cards.Cards;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.finished.Stay;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTest {
    Fixtures fx = new Fixtures();

    @Test
    @DisplayName("draw 히트여부 기능 테스트")
    void draw_hit() {
        var state = new Hit(new Cards(new ArrayList<>(List.of(ACE, TWO))));
        assertThat(state.draw(EIGHT))
                .isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("draw 카드추가 기능 테스트")
    void draw_hit_containEight() {
        var state = new Hit(new Cards(new ArrayList<>(List.of(ACE, TWO))));
        assertThat(state.draw(EIGHT).cards().getCopy())
                .contains(EIGHT);
    }

    @Test
    @DisplayName("draw 버스트여부 기능 테스트")
    void draw_bust() {
        var state = new Hit(new Cards(new ArrayList<>(List.of(NINE, TEN))));
        assertThat(state.draw(EIGHT))
                .isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("draw stay 변경 테스트")
    void stay() {
        var state = new Hit(new Cards(new ArrayList<>(List.of(TWO, TEN))));
        assertThat(state.stay())
                .isInstanceOf(Stay.class);
    }
}
