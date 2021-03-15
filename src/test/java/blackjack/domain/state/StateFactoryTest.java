package blackjack.domain.state;

import static blackjack.domain.Fixture.FIXTURE_ACE;
import static blackjack.domain.Fixture.FIXTURE_KING;
import static blackjack.domain.Fixture.FIXTURE_THREE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.running.Hit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StateFactoryTest {

    @DisplayName("첫 두장의 카드를 넣고, Hit 상태면 Hit 객체를 반환해야 한다.")
    @Test
    void hit() {
        // given, when
        State state = StateFactory.generateState(FIXTURE_KING, FIXTURE_THREE);

        // then
        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("첫 두장의 카드를 넣고, Blackjack 상태면 Blackjack 객체를 반환해야 한다.")
    @Test
    void blackjack() {
        // given, when
        State state = StateFactory.generateState(FIXTURE_ACE, FIXTURE_KING);

        // then
        assertThat(state).isInstanceOf(Blackjack.class);
    }

}