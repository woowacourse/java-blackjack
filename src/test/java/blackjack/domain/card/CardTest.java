package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class CardTest {

    @Test
    @DisplayName("캐싱 및 동일성 테스트")
    void caching() {
        assertThat(Card.valueOf(Shape.DIAMOND, CardValue.ACE))
            .isSameAs((Card.valueOf(Shape.DIAMOND, CardValue.ACE)));
    }

    @Test
    @DisplayName("모든 카드가 존재하는지 테스트")
    void getAllCards() {
        assertThat(Card.getAllCards().size()).isEqualTo(52);
    }
}
