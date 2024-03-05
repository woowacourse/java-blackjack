package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class CardTest {

    @DisplayName("문자(String)와 모양(String)을 가지고 있다.")
    @Test
    void create_success() {
        String letter = "3";
        String mark = "다이아몬드";

        assertThatCode( () -> new Card(letter,mark))
                .doesNotThrowAnyException();
    }

    @DisplayName("숫자로 계산한 값을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"2:2", "10:10", "A:1", "J:10", "K:10", "Q:10"}, delimiter = ':')
    void getValue(String letter, int value) {
        String mark = "다이아몬드";

        Card card = new Card(letter, mark);

        assertThat(card.getValue()).isEqualTo(value);
    }
}
