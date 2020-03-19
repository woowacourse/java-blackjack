package model.user.data;

import exception.IllegalStringInputException;
import exception.PlayerNamesOverlapException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerNamesTest {

    @Test
    @DisplayName("입력으로 빈값이 들어올 때")
    void input_Empty() {
        assertThatThrownBy(() -> new PlayerNames(" "))
            .isInstanceOf(IllegalStringInputException.class)
            .hasMessageMatching("입력은 한 글자 이상이어야 합니다.");
    }

    @Test
    @DisplayName("입력으로 null값이 들어올 때")
    void input_Null() {
        assertThatThrownBy(() -> new PlayerNames(null))
            .isInstanceOf(IllegalStringInputException.class)
            .hasMessageMatching("Null 값은 입력하실 수 없습니다.");
    }

    @Test
    @DisplayName("쉼표만 있을 때")
    void split_test() {
        assertThatThrownBy(() -> new PlayerNames(", ,"))
            .isInstanceOf(IllegalStringInputException.class)
            .hasMessageMatching("입력은 한 글자 이상이어야 합니다.");
    }

    @ParameterizedTest
    @DisplayName("이름을 잘 포함하고 있는지 확인")
    @CsvSource(value = {"pobi,crong,honux:pobi", "crong:crong", "DD,Tiger:Tiger"}, delimiter = ':')
    void namesList_test(String input, String expected) {
        PlayerNames names = new PlayerNames(input);
        assertThat(names.contains(expected)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("중복 체크")
    @ValueSource(strings = {"pobi,pobi", "crong,crong"})
    void overlap_test(String input) {
        assertThatThrownBy(() -> new PlayerNames(input)
        ).isInstanceOf(PlayerNamesOverlapException.class)
            .hasMessageMatching("사용자 이름은 중복될 수 없습니다.");
    }
}