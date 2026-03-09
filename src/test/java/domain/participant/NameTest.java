package domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {" ", ""})
    void 이름이_공백이면_예외처리(String input) {
        Assertions.assertThatThrownBy(() -> Name.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 이름이_10글자_이상이면_예외처리() {
        String input = "1234567890!";

        Assertions.assertThatThrownBy(() -> Name.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
