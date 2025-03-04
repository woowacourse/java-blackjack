package policy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CardPolicyTest {
    @ParameterizedTest
    @DisplayName("카드의 모양이 맞지 않는다면 예외가 발생한다.")
    @ValueSource(strings = {"스페이드", "다이아몬드", "하트", "클로버"})
    void shapeTest(String shape) {
        //given
        String number = "1";

        //when-then
        assertThatThrownBy(() -> new Card(shape, number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드의 모양은 스페이드, 다이아몬드, 하트, 클로버가 있습니다.");
    }

}
