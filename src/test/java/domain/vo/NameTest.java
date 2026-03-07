package domain.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class NameTest {
    @ParameterizedTest
    @ValueSource(strings = {"pobi", "슈크림", "pa스타"})
    void name_정상생성_테스트(String input) {
        Name name = new Name(input);

        assertThat(name.getName()).isEqualTo(input);
    }

    @Test
    void 이름이_딜러일때_예외 (){
        String input = "딜러";

        Assertions.assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "!", "1000j"})
    void 이름에_영어_한글_외_입력시_예외 (String input) {
        Assertions.assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}