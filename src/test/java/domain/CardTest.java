package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
