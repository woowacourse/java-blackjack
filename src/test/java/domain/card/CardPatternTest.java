package domain.card;

import java.util.NoSuchElementException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CardPatternTest {

    @DisplayName("입력이 스페이드, 하트, 다이아몬드, 클로버이면 CardPattern을 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "스페이드, SPADE",
            "하트, HEART",
            "다이아몬드, DIAMOND",
            "클로버, CLUB"
    })
    void patternTest_inputSpade_returnCardPattern(String value, CardPattern cardPattern) {
        Assertions.assertThat(CardPattern.matchCardPattern(value))
                .isEqualTo(cardPattern);
    }

    @DisplayName("입력이 스페이드, 하트, 다이아몬드, 클로버가 아니면 NoSuchElementException을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"포비", "리사", "브리"})
    void patternTest_inputWrongWord_throwNoSuchElementException(String value) {
        Assertions.assertThatThrownBy(() -> CardPattern.matchCardPattern(value))
                .isInstanceOf(NoSuchElementException.class);
    }
}
