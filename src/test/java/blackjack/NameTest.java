package blackjack;

import blackjack.domain.participant.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NameTest {
    @DisplayName("이름 생성 테스트")
    @Test
    void createNameTest() {
        Assertions.assertDoesNotThrow(
                () -> new Name("fafi")
        );

        assertThatThrownBy(
                () -> new Name("")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
