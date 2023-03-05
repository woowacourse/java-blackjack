package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class NameTest {
    @Test
    @DisplayName("이름에 딜러가 들어가는지 테스트")
    void dealerNameFormatTest() {
        assertThatNoException().isThrownBy(() -> new Name("딜러"));
    }

}
