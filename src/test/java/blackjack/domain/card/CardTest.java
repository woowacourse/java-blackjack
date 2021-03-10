package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    @DisplayName("카드 생성 성공")
    void createCard() {
        Card card = new Card(Shape.SPADE, Denomination.ACE);
        assertThat(card).isEqualTo(new Card(Shape.SPADE, Denomination.ACE));
    }

    @Test
    @DisplayName("카드 캐싱 테스트")
    void createCardCache() {
        assertThat(Card.values()).hasSize(52);
    }
}
