package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import exception.GameException;
import org.junit.jupiter.api.Test;

public class TestAgreement {

    @Test
    public void 정상_작동() {
        Agreement agreement1 = new Agreement("n");
        Agreement agreement2 = new Agreement("y");

        assertThat(agreement1.get()).isFalse();
        assertThat(agreement2.get()).isTrue();
    }

    @Test
    public void 이상_입력_예외() {
        assertThatThrownBy(() -> new Agreement("x"))
                .isExactlyInstanceOf(GameException.class)
                .hasMessage("유효한 형식의 입력으로 넣어주세요(y 또는 n)");
    }
}
