package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Outcome;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutcomeTranslatorTest {

    @DisplayName("OutcomeTranslator는 Outcome의 멤버를 모두 알고 있다.")
    @Test
    void knowAllValuesOfOutcome() {
        final List<Outcome> expected = List.of(Outcome.values());
        final List<Outcome> actual = Arrays.stream(OutcomeTranslator.values())
                .map(OutcomeTranslator::getOutcome)
                .toList();

        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("주어진 Outcome에 매칭되는 viewName을 찾는다.")
    @Test
    void findViewName() {
        final String expected = "승";

        final String actual = OutcomeTranslator.translate(Outcome.WIN);

        assertThat(actual).isEqualTo(expected);
    }
}
