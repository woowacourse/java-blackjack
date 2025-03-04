package policy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardPolicy {
    @Test
    @DisplayName("카드의 모양이 맞지 않는다면 예외가 발생한다.")
    void shapeTest(){
        //given
        String shape = "스페이드드";
        String number = "1";

        //when-then
        assertThatThrownBy(() -> new Card(shape,number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드의 모양은 스페이드, 다이아몬드, 하트, 클로버가 있습니다.");
    }

}
