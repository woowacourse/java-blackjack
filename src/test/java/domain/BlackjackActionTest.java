package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackjackActionTest {
    @ParameterizedTest
    @CsvSource(value = {"y:HIT", "n:STAY"}, delimiter = ':')
    void y_n의_값을_통해_행위를_알_수_있다(String expression, BlackjackAction expected) {
        BlackjackAction actual = BlackjackAction.from(expression);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"HIT:true", "STAY:false"}, delimiter = ':')
    void 카드_추가_지급_여부를_판단한다(BlackjackAction blackjackAction, boolean expected) {
        boolean actual = blackjackAction.isHit();

        assertThat(actual).isEqualTo(expected);
    }
}
