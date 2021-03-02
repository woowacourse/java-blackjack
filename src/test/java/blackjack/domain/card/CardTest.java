package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    @DisplayName("카드 생성 테스트")
    @Test
    void create() {
        Card card = new Card(Type.DIAMOND, Denomination.FOUR);

        assertThat(card).isEqualTo(new Card(Type.DIAMOND, Denomination.FOUR));
    }
}