package view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Money;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class InputViewTest {

    private final InputView inputView = new InputView();

    @Test
    void 이름을_쉼표_기준으로_파싱한다() {
        String input = "pobi,jason";

        List<String> names = inputView.parseNames(input);

        assertThat(names).contains("pobi", "jason");
    }

    @Test
    void 이름에_포함된_공백을_제거한다() {
        String input = " pobi , jason ";

        List<String> names = inputView.parseNames(input);

        assertThat(names).contains("pobi", "jason");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 입력값이_null이거나_빈_문자열이면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> inputView.parseNames(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(InputView.ERROR_NAMES_EMPTY);
    }

    @Test
    void 이름이_비어있으_예외가_발생한다() {
        String input = ",";

        assertThatThrownBy(() -> inputView.parseNames(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Nested
    class MoneyInputValidation {
        @Test
        void 입력한_돈이_숫자가_아니면_예외를_발생한다() {
            assertThatThrownBy(() -> inputView.parseMoney("abc123"))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 입력한_돈이_음수인_경우_예외를_발생한다() {
            assertThatThrownBy(() -> inputView.parseMoney("-100"))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 유효한_금액을_입력하면_정상적으로_파싱된다() {
            Money money = inputView.parseMoney("10000");

            double amount = money.amount();

            assertThat(amount).isEqualTo(10000);
        }
    }
}
