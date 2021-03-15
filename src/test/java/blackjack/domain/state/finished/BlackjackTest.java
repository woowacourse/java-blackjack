package blackjack.domain.state.finished;

import static blackjack.domain.Fixture.FIXTURE_ACE;
import static blackjack.domain.Fixture.FIXTURE_KING;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @DisplayName("블랙잭 상태에서 카드를 더 뽑으면 예외가 발생한다.")
    @Test
    void draw() {
        // given, when
        State state = StateFactory.generateState(FIXTURE_ACE, FIXTURE_KING);

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            state.draw(FIXTURE_KING);
        });
    }

}