package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LetterTest {

    @ParameterizedTest(name = "문자에 따른 점수를 알 수 있다")
    @CsvSource({"LETTER_A,1", "LETTER_2,2", "LETTER_3,3", "LETTER_4,4", "LETTER_5,5", "LETTER_6,6", "LETTER_7,7",
            "LETTER_8,8", "LETTER_9,9", "LETTER_10,10", "LETTER_J,10", "LETTER_Q,10", "LETTER_K,10",})
    void test_letter_score(Letter letter, int expectedScore) {
        assertThat(letter.score()).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("A인지 알 수 있다")
    void test_is_A() {
        assertThat(Letter.ACE.isA()).isTrue();
    }
}
