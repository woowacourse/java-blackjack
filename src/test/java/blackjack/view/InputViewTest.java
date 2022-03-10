package blackjack.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static blackjack.view.InputView.inputNames;
import static blackjack.view.InputView.requestHitOrNot;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputViewTest {

    @Test
    @DisplayName("빈 입력으로 이름을 입력한 경우 에러 발생")
    void inputEmptyName(){
        setInput("\n");
        assertThatThrownBy(() -> inputNames())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력은 빈 입력일 수 없습니다.");
    }

    @Test
    @DisplayName("올바르지 않은 구분자로 이름을 입력한 경우 에러 발생")
    void inputWrongDelimiterName() {
        setInput("pobi:woni:jason");
        assertThatThrownBy(() -> inputNames())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력형식에 맞춰 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"123,456,789","!!!,***"})
    @DisplayName("허용되지 않는 문자로 이름을 입력한 경우 에러 발생")
    void inputWrongCharName(String input){
        setInput(input);
        assertThatThrownBy(() -> inputNames())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력형식에 맞춰 입력해주세요.");
    }

    @Test
    @DisplayName("빈 입력으로 hitOrNot을 입력한 경우 에러 발생")
    void inputEmptyHitOrNot(){
        setInput("\n");
        assertThatThrownBy(() -> requestHitOrNot("pobi"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력은 빈 입력일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1","@","a"})
    @DisplayName("허용되지 않다 문자로 hitOrNot을 입력한 경우 에러 발생")
    void inputWrongHitOrNot(String input){
        setInput(input);
        assertThatThrownBy(() -> requestHitOrNot("pobi"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("혹은")
                .hasMessageContaining("만 입력 가능합니다.");
    }

    void setInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }
}
