package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DrawOrStayTest {

    @Test
    @DisplayName("y또는 n이외의 값을 입력시 예외처리하는지 테스트")
    void validateDrawInput() {
        assertThatThrownBy(() -> DrawOrStay.from("okay"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DrawOrStay.INPUT_FORM_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("y또는 n을 받을 때 정상적으로 생성되는지 테스트")
    void initialTest() {
        final String inputY = "y";
        final String inputN = "n";

        assertSoftly(softly -> {
            softly.assertThat(DrawOrStay.from(inputY).isDraw()).isTrue();
            softly.assertThat(DrawOrStay.from(inputN).isDraw()).isFalse();
        });

    }
}
