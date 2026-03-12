package domain;

import exception.ErrorMessage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(strings = {"3.14", "돈", ""})
    void 베팅_금액에_정수가_아닌_값을_입력하면_에러가_발생한다(String input){
        // given
        // when,then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_MONEY.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "0"})
    void 베팅_금액에_0이하인_값을_입력하면_에러가_발생한다(String input){
        // given
        // when,then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NEGATIVE_MONEY.getMessage());
    }
}