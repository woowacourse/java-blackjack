package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class LetterTest {

    @Test
    @DisplayName("현재 카드 글자가 에이스인지 확인한다.")
    void isAceTest() {
        assertThat(Letter.ACE.isAce()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"})
    @DisplayName("글자가 가진 이름과 같은지 확인하는 테스트")
    void getNameTest(final String name) {
        assertThat(Letter.from(name).getName()).isEqualTo(name);
    }

    @ParameterizedTest
    @CsvSource(
            value = {"A:11", "2:2", "3:3", "4:4", "5:5", "6:6", "7:7", "8:8", "9:9", "10:10", "J:10", "Q:10", "K:10"},
            delimiter = ':'
    )
    @DisplayName("글자가 가진 숫자와 같은지 확인하는 테스트")
    void getValueTest(final String name, final int value) {
        assertThat(Letter.from(name).getValue()).isEqualTo(value);
    }
}
