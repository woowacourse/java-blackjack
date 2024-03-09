package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.participant.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "가나다a1", "abcde"})
    @DisplayName("성공: 1~5자의 이름 입력")
    void construct_NoException_OneToFiveLetters(String rawName) {
        assertThatCode(() -> new Name(rawName))
            .doesNotThrowAnyException();

        assertThat(new Name(rawName).getName()).isEqualTo(rawName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "가나다a1", "abcde"})
    @DisplayName("성공: 이름 확인")
    void construct_Equals_rawName(String rawName) {
        Name name = new Name(rawName);
        assertThat(name.getName()).isEqualTo(rawName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "123456"})
    @DisplayName("실패: 글자수 위반 - 경계값(0, 6)")
    void construct_Exception_IllegalNameLength(String rawName) {
        assertThatThrownBy(() -> new Name(rawName))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 1~5자의 이름만 입력 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a!", "!@#$%", "  "})
    @DisplayName("실패: 이름 조건 위반 - 비허용 문자 입력")
    void construct_Exception_IllegalLetters(String rawName) {
        assertThatThrownBy(() -> new Name(rawName))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 한글, 영어, 숫자만 입력 가능합니다.");
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("실패: 이름 조건 위반 - 비허용 문자 입력")
    void construct_Exception_null(String rawName) {
        assertThatThrownBy(() -> new Name(rawName))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] null을 입력할 수 없습니다.");
    }

}
