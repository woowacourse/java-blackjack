package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutcomeTest {

    @DisplayName("승패 결과들을 반전한다.")
    @Test
    void reverseWinningResult() {
        final List<Outcome> outcomes = List.of(Outcome.WIN, Outcome.LOSE, Outcome.PUSH);

        final List<Outcome> reverseOutcomes = Outcome.reverse(outcomes);

        assertThat(reverseOutcomes).containsExactly(Outcome.LOSE, Outcome.WIN, Outcome.PUSH);

    }
}
