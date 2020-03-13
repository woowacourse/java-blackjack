package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    private Cards cards;

    @BeforeEach
    private void setUp() {
        cards = new Cards();
    }

    @DisplayName("Cards 객체 생성 테스트")
    @Test
    void CardsTest() {
        Assertions.assertThat(cards).isInstanceOf(Cards.class);
    }

    @DisplayName("객체의 복사 테스트")
    @Test
    void deepDuplicateTest() {
        Assertions.assertThat(cards).extracting("cardsDeck").asList().size().isEqualTo(Card.getCards().size());
    }

    @DisplayName("pop 기능 테스트")
    @Test
    void popTest() {
        Card popCard = cards.pop();

        Assertions.assertThat(popCard).isInstanceOf(Card.class);
        Assertions.assertThat(cards).extracting("cardsDeck").asList().size().isNotEqualTo(Card.getCards().size());
    }

    @DisplayName("카드 덱을 모두 소모했을 때 예외 처리 테스트")
    @Test
    void popWhenEmptyDeckTest() {
        Assertions.assertThatThrownBy(() -> {
            for (int i = 0; i < 53; i++) {
                Card card = cards.pop();
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
