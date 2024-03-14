package blackjack.view.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {
    private InputValidator inputValidator = new InputValidator();

    @ParameterizedTest
    @NullAndEmptySource
    void 입력이_공백이거나_null_이면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> inputValidator.validatePlayerNames(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력은 공백일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"포비,딜러", "포비,y", "포비,n"})
    void 게임_참가자_이름_중_딜러_또는_y_또는_n_이라는_이름을_가지면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> inputValidator.validatePlayerNames(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("딜러와 연관된 이름 또는 게임 명령어와 연관된 이름이 존재할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"10000!", "10000a", "10000원"})
    void 배팅_금액에_대한_입력이_숫자가_아니라면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> inputValidator.validateBattingAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("배팅 금액에 대한 입력은 숫자여야 합니다.");
    }
}
