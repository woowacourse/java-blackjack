package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class InputViewTest {

    @Test
    @DisplayName("입력받은 이름을 List 형태로 반환한다.")
    void readPlayerNames() {
        String names = "몰리,리브";
        InputView inputView = new InputView(() -> names);

        assertThat(inputView.readPlayerName()).containsExactly("몰리", "리브");
    }

    @Test
    @DisplayName("숫자가 아닌 값을 입력하면 예외를 던진다.")
    void readBetAmountByNotNumber() {
        InputView inputView = new InputView(() -> "돈");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputView.readBetAmount("리브"))
                .withMessage("숫자를 입력해 주세요.");
    }

    @Test
    @DisplayName("추가 선택 입력 시 y 또는 n이 아닌 경우 예외를 던진다.")
    void readHitOrNotByInvalidInput() {
        InputView inputView = new InputView(() -> "yes");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputView.readHitOrNot("리브"))
                .withMessage("y 혹은 n만 입력할 수 있습니다.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("추가 선택 입력 시 blank가 들어오면 예외를 던진다.")
    void readHitOrNotByInvalid(String input) {
        InputView inputView = new InputView(() -> input);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputView.readHitOrNot("리브"))
                .withMessage("y 혹은 n만 입력할 수 있습니다.");
    }
}
