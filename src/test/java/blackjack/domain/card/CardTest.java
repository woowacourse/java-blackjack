package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드 생성자 테스트")
    @Test
    void create() {
        Card card = new Card(Number.ACE, Kind.HEART);
        assertThat(card).isNotNull();
    }
}
