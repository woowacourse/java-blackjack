package blackjack.view;

import static blackjack.view.InputView.INVALID_FORMAT;
import static blackjack.view.InputView.SHOULD_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class InputViewTest {

    @Test
    @DisplayName("입력받은 이름을 List 형태로 반환한다.")
    void readPlayerNames() {
        InputView inputView = Fixtures.createInputView("몰리,리브");

        assertThat(inputView.readPlayerName()).containsExactly("몰리", "리브");
    }

    @Test
    @DisplayName("숫자가 아닌 값을 입력하면 예외를 던진다.")
    void readBetAmountByNotNumber() {
        InputView inputView = Fixtures.createInputView("돈");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputView.readBetMoney("리브"))
                .withMessage(SHOULD_NUMBER);
    }

    @Test
    @DisplayName("추가 선택 입력 시 y 또는 n이 아닌 경우 예외를 던진다.")
    void readHitOrNotByInvalidInput() {
        InputView inputView = Fixtures.createInputView("yes");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputView.readHitOrNot("리브"))
                .withMessage(INVALID_FORMAT);
    }

    @ParameterizedTest(name = "[{index}] 입력값이 {0}이면 예외를 던진다.")
    @NullAndEmptySource
    @DisplayName("추가 선택 입력 시 공백이거나 null을 입력하면 예외를 던진다.")
    void readHitOrNotByInvalid(String input) {
        InputView inputView = Fixtures.createInputView(input);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputView.readHitOrNot("리브"))
                .withMessage(INVALID_FORMAT);
    }

    static class Fixtures {
        static InputView createInputView(String input) {
            return new InputView(() -> input);
        }
    }
}
