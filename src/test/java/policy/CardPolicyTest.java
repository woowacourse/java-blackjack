package policy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CardPolicyTest {
    @Test
    @DisplayName("카드의 모양이 맞지 않는다면 예외가 발생한다.")
    void shapeTest() {
        //given
        String shape = "스페이트";
        String number = "2";

        //when-then
        assertThatThrownBy(() -> new Card(shape, number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드의 모양은 스페이드, 다이아몬드, 하트, 클로버가 있습니다.");
    }

    @Test
    @DisplayName("정책에 벗어난 숫자는 예외가 발생한다.")
    void numberTest() {
        // given
        String shape = "스페이드";
        String number = "1";

        // when - then
        assertThatThrownBy(() -> new Card(shape, number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 트럼프 카드에 맞는 숫자를 입력해 주세요.");
    }

}
