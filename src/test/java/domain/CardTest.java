package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    @Test
    @DisplayName("Symbol과 Type을 인자로 갖는 Card가 생성된다.")
    void card() {
        assertThat(new Card(Symbol.valueOf("ACE"), Type.valueOf("HEART"))).isNotNull();
    }
}
