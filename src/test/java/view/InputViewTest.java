package view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {

    private InputView inputView;

    @BeforeEach
    void setInputView() {
        inputView = InputView.getInstance();
    }

    @Test
    @DisplayName("유저 이름 공백 입력 에러 테스트")
    void inputPlayerName() {
        //given
        InputStream in = new ByteArrayInputStream("".getBytes());
        System.setIn(in);

        //then
        assertThatThrownBy(() -> inputView.inputPlayerName()).isInstanceOf(Exception.class);
    }

}