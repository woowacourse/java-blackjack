package blackjack;

import static org.assertj.core.api.Assertions.*;
import blackjack.domain.participants.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameTest {
    @Test
    @DisplayName("이름이 빈 문자열인지 확인")
    void validateEmptyString() {
        assertThatThrownBy(() -> new Name(""))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
