package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import model.participant.Agreement;
import org.junit.jupiter.api.Test;

public class AgreementTest {
    @Test
    public void 정상_작동() {
        Agreement agreement1 = new Agreement("n");
        Agreement agreement2 = new Agreement("y");

        assertThat(agreement1.value()).isFalse();
        assertThat(agreement2.value()).isTrue();
    }

    @Test
    public void 이상_입력_예외() {
        assertThatThrownBy(() ->  new Agreement("x"))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_CONDITION_INPUT.getMessage());

        assertThatThrownBy(() -> new Agreement(" "))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INPUT_IS_BLANK.getMessage());
    }
}
