package blakjack.domain.state.running;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.state.State;
import blakjack.domain.state.finished.Bust;
import blakjack.domain.state.finished.Stay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blakjack.domain.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;

public class HitTest {
    @Test
    @DisplayName("카드를 뽑아 bust 가 아닌 경우 Hit 반환")
    void notBust() {
        final PrivateArea privateArea = new PrivateArea("칙촉");
        final Chip chip = new Chip(1000);

        State init = new Init(privateArea, chip);
        init = init.draw(CLUB_NINE);
        init = init.draw(HEART_ACE);

        assertThat(init.draw(CLUB_NINE)).isExactlyInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드를 뽑아 bust 되는 경우")
    void bust() {
        final PrivateArea privateArea = new PrivateArea("칙촉");
        final Chip chip = new Chip(1000);

        State init = new Init(privateArea, chip);
        init = init.draw(CLUB_TEN);
        init = init.draw(CLUB_TEN);

        assertThat(init.draw(CLUB_TEN)).isExactlyInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("stay 할 경우 Stay 객체 반환")
    void stay() {
        final PrivateArea privateArea = new PrivateArea("칙촉");
        final Chip chip = new Chip(1000);

        State init = new Init(privateArea, chip);
        init = init.draw(CLUB_NINE);
        init = init.draw(HEART_ACE);

        assertThat(init.stay()).isExactlyInstanceOf(Stay.class);
    }
}
