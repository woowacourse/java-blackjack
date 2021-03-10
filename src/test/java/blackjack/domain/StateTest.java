package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import blackjack.domain.state.Blackjack;
import blackjack.domain.state.Bust;
import blackjack.domain.state.Hit;
import blackjack.domain.state.Stay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StateTest {

    @Test
    @DisplayName("4가지 기본 상태 확인")
    void name() {
        Cards cards = new Cards();
        Hit hit = new Hit(cards);
        Stay stay = new Stay(cards);
        Blackjack blackjack = new Blackjack(cards);
        Bust bust = new Bust(cards);

        assertThat(stay.isFinished());
        assertThat(blackjack.isFinished());
        assertThat(bust.isFinished());
        assertFalse(hit.isFinished());

    }
}
