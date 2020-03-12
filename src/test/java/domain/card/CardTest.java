package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드 생성자 테스트")
    void card() {
        assertThat(new Card(Type.CLUB, Symbol.ACE)).isInstanceOf(Card.class);
    }
}
