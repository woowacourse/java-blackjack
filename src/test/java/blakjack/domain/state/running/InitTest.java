package blakjack.domain.state.running;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.state.State;
import blakjack.domain.state.finished.Blackjack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blakjack.domain.Fixture.CLUB_TEN;
import static blakjack.domain.Fixture.HEART_ACE;
import static org.assertj.core.api.Assertions.assertThat;

public class InitTest {
    @Test
    @DisplayName("게임시작 후 카드를 한 장 받는 경우")
    void firstCard() {
        final PrivateArea privateArea = new PrivateArea("칙촉");
        final Chip chip = new Chip(1000);

        final State init = new Init(privateArea, chip);

        assertThat(init.draw(HEART_ACE)).isExactlyInstanceOf(Init.class);
    }

    @Test
    @DisplayName("게임시작 후 블랙잭인 경우")
    void secondCard() {
        final PrivateArea privateArea = new PrivateArea("칙촉");
        final Chip chip = new Chip(1000);

        State init = new Init(privateArea, chip);
        init = init.draw(HEART_ACE);

        assertThat(init.draw(CLUB_TEN)).isExactlyInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("게임시작 후 두 장을 받고 블랙잭이 아닌 경우에는 히트")
    void secondCard2() {
        final PrivateArea privateArea = new PrivateArea("칙촉");
        final Chip chip = new Chip(1000);

        State init = new Init(privateArea, chip);
        init = init.draw(HEART_ACE);

        assertThat(init.draw(HEART_ACE)).isExactlyInstanceOf(Hit.class);
    }
}
