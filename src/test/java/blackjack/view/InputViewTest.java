package blackjack.view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class InputViewTest {

    @DisplayName("플레이어들의 이름에 null 을 입력했을 때 예외 발생을 확인한다.")
    @Test
    void players_name_null_exception() {
        Enterable enterable = () -> null;

        assertThatThrownBy(() -> InputView.inputPlayerNames(enterable))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 값을 입력할 수 없습니다.");
    }

    @DisplayName("플레이어들의 이름에 빈 값을 입력했을 때 예외 발생을 확인한다.")
    @Test
    void players_name_empty_exception() {
        Enterable enterable = () -> "";

        assertThatThrownBy(() -> InputView.inputPlayerNames(enterable))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 값을 입력할 수 없습니다.");
    }

    @DisplayName("베팅 금액에 null 을 입력했을 때 예외 발생을 확인한다.")
    @Test
    void betting_null_exception() {
        Enterable enterable = () -> null;

        assertThatThrownBy(() -> InputView.inputBetting(enterable))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 값을 입력할 수 없습니다.");
    }

    @DisplayName("베팅 금액에 빈 값을 입력했을 때 예외 발생을 확인한다.")
    @Test
    void betting_empty_exception() {
        Enterable enterable = () -> "";

        assertThatThrownBy(() -> InputView.inputBetting(enterable))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 값을 입력할 수 없습니다.");
    }

    @DisplayName("베팅 금액에 문자를 입력했을 때 예외 발생을 확인한다.")
    @Test
    void betting_not_number_exception() {
        Enterable enterable = () -> "betting";

        assertThatThrownBy(() -> InputView.inputBetting(enterable))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자를 입력해주세요.");
    }

    @DisplayName("카드 추가 대답에 null 을 입력했을 때 예외 발생을 확인한다.")
    @Test
    void receive_more_card_answer_null_exception() {
        Enterable enterable = () -> null;

        Assertions.assertThatThrownBy(() -> InputView.inputDrawingAnswer(enterable))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 값을 입력할 수 없습니다.");
    }

    @DisplayName("카드 추가 대답에  빈 값을 입력했을 때 예외 발생을 확인한다.")
    @Test
    void receive_more_card_answer_empty_exception() {
        Enterable enterable = () -> "";

        Assertions.assertThatThrownBy(() -> InputView.inputDrawingAnswer(enterable))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 값을 입력할 수 없습니다.");
    }
}
