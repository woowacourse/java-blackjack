package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("생성자 NULL일 경우 예외")
    @Test
    void create() {
        assertThatThrownBy(() -> new Card(null, null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("잘못");
        assertThatThrownBy(() -> new Card(Type.CLUB, null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("잘못");
        assertThatThrownBy(() -> new Card(null, Symbol.ACE))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("잘못");
    }

    @DisplayName("에이스 카드 여부 확인")
    @Test
    void card() {
        Card cardAce = new Card(Type.CLUB, Symbol.ACE);
        Card cardNotAce = new Card(Type.CLUB, Symbol.TEN);

        assertThat(cardAce.isAce()).isTrue();
        assertThat(cardNotAce.isAce()).isFalse();
    }
}
