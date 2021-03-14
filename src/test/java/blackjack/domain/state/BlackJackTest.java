package blackjack.domain.state;

import blackjack.domain.Fixture;
import blackjack.state.BlackJack;
import blackjack.state.State;
import blackjack.state.StateFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackTest {

    @DisplayName("초기 BlackJack 상태인지 확인")
    @Test
    void isBlackJack() {
        State state = StateFactory.initialPlayerDraw(Fixture.CLUBS_KING, Fixture.CLUBS_ACE);

        assertThat(state).isInstanceOf(BlackJack.class);
    }
}
