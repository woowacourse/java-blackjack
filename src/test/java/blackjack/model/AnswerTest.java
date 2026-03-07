package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AnswerTest {

    @ParameterizedTest
    @ValueSource(strings = {"Y", "N", "h", " ", ""})
    void 부적절한_라벨이라면_예외를_던진다(String illegalLabel) {
        assertThatThrownBy(() -> Answer.from(illegalLabel))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
