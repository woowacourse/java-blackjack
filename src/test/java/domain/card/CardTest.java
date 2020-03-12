package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("에이스 카드 여부 확인")
    void card() {
        Card cardAce = new Card(Type.CLUB, Symbol.ACE);
        Card cardNotAce = new Card(Type.CLUB, Symbol.TEN);

        assertThat(cardAce.isAce()).isTrue();
        assertThat(cardNotAce.isAce()).isFalse();
    }
}
