package blackjack.domain.state;

import blackjack.BlackjackApplication;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static blackjack.domain.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FirstTurnTest {
    @Test
    void blackjack() {
        final State blackjack = FirstTurn.draw(Arrays.asList(CLUB_ACE, CLUB_TEN));
        assertThat(blackjack).isInstanceOf(Blackjack.class);
    }

    @Test
    void hit() {
        final State hit = FirstTurn.draw(Arrays.asList(CLUB_TWO, CLUB_TEN));
        assertThat(hit).isInstanceOf(Hit.class);
    }

    @Test
    void equalsTest() {
        // 객체 비교 테스트
        final State blackjack = FirstTurn.draw(Arrays.asList(CLUB_ACE, CLUB_TEN));
        System.out.println(Objects.equals(blackjack, BlackjackApplication.class)); // false
        System.out.println(blackjack.getClass()); // class blackjack.domain.state.Blackjack
        System.out.println(blackjack instanceof Blackjack); // false
        System.out.println(blackjack.equals(Blackjack.class));
    }
}
