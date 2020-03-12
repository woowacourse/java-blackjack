package blackjack.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputHandlerTest {
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("이름이 입력이 아예 안됐을 떄")
    void nullInputTest(String input) {
        assertThatThrownBy(() -> InputHandler.parseName(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1개 이상");
    }

    @ParameterizedTest
    @DisplayName("입력한 이름으로 파싱 테스트")
    @CsvSource(value = {
            "pobi,jamie,cu:3",
            "포비:1",
            "포비, 제이미, 시카, 코일:4",
            "pobi,jamie,rebecca,hiro,kyle,hodol,geunie,tami:8",
            "@!,#$%,!^&,%&*,알트:5"
    }, delimiter = ':')
    void parseTest(String input, int expected) {
        assertThat(InputHandler.parseName(input).size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("중복된 이름이 있는 경우 테스트")
    void duplicatedNameTest() {
        assertThatThrownBy(() -> InputHandler.parseName("pobi,jason,pororo,pobi"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }
}
