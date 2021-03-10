package blackjack.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTest {
    private InputView getInputView(String inputText) {
        InputStream inputStream = new ByteArrayInputStream(inputText.getBytes());
        System.setIn(System.in);
        Scanner scanner = new Scanner(inputStream);
        InputView inputView = new InputView(scanner);
        return inputView;
    }

    @DisplayName("보장된 이름 (특수문자를 제외한, 대소문자의 문자)을 입력하면, 플레이어이름이 등록된다.")
    @Test
    void player_name_input_test() {
        //given
        String inputText = "pobi,brown,jason";
        InputView inputView = getInputView(inputText);

        //when
        List<String> playerNames = inputView.getPlayerNames();

        //then
        assertThat(playerNames).containsExactly("pobi", "brown", "jason");
    }

    @DisplayName("보장된 이름 (특수문자를 제외한, 대소문자의 문자)이 아니면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"#pobi", "br own", "!jason"})
    void player_name_input_exception_test(String inputText) {
        //given
        InputView inputView = getInputView(inputText);

        //when
        assertThatThrownBy(() -> inputView.getPlayerNames())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("이름을 잘못 입력하였습니다. (입력값 : %s)", inputText));
    }

    @DisplayName("배팅 금액 입력을 숫자 이외의 문자로 하였을 경우 예외처리한다.")
    @Test
    void batting_amount_test() {
        String name = "pobi";
        InputView inputView = getInputView("배팅금액");
        assertThatThrownBy(() -> inputView.getBattingAmount(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(name + "의 배팅 금액을 잘못 입력하셨습니다.");
    }
}