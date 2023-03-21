package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.Textures.CLOVER_ACE;
import static domain.Textures.SPADE_FOUR;
import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    @Test
    @DisplayName("주어진 카드가 에이스인 지 판별할 수 있다.")
    void isAce() {
        assertThat(CLOVER_ACE.isAce()).isTrue();
        assertThat(SPADE_FOUR.isAce()).isFalse();
    }
}