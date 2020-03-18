package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    @DisplayName("카드 생성자 테스트")
    void card() {
        Assertions.assertThat(new Card(Type.CLUB, Symbol.ACE))
                .isInstanceOf(Card.class);
    }
}
