package blackjack;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    @DisplayName("문자열을 통해서 이름을 생성한다.")
    void Name_createInstanceWithString() {
        assertThatCode(() -> {
            Name name = new Name("초롱");
            Assertions.assertEquals(name.asString(), "초롱");
        })
                .doesNotThrowAnyException();
    }
}
