package blackjack.domain;

import static blackjack.fixtures.BlackjackFixtures.SPADE_EIGHT;
import static blackjack.fixtures.BlackjackFixtures.SPADE_NINE;
import static blackjack.fixtures.BlackjackFixtures.SPADE_SEVEN;
import blackjack.domain.entry.vo.Name;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BlackjackTableTest {

    @Test
    void test() {
        final Map<Name, State> playerState = new HashMap<>();
        playerState.put(new Name("hoho"), Ready.start(SPADE_EIGHT, SPADE_NINE));
        State state = playerState.get(new Name("hoho"));
        state = state.draw(SPADE_SEVEN);
        playerState.put(new Name("hoho"), state);

        State state1 = playerState.get(new Name("hoho"));
        System.out.println(state1.isFinished());
    }

}
