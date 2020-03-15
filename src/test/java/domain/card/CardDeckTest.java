package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
    private CardDeck cards;

    @BeforeEach
    private void setUp() {
        cards = new CardDeck();
    }

    @DisplayName("Cards 객체 생성 테스트")
    @Test
    void CardsTest() {
        Assertions.assertThat(cards).isInstanceOf(CardDeck.class);
    }

    @DisplayName("객체의 복사 테스트")
    @Test
    void deepDuplicateTest() {
        Assertions.assertThat(cards).extracting("cardsDeck").asList().size().isEqualTo(Card.getCards().size());
    }

    @DisplayName("hit 기능 테스트")
    @Test
    void hitTest() {
        Card popCard = cards.hit();

        Assertions.assertThat(popCard).isInstanceOf(Card.class);
        Assertions.assertThat(cards).extracting("cardsDeck").asList().size().isNotEqualTo(Card.getCards().size());
    }

    @DisplayName("카드 덱을 모두 소모했을 때 예외 처리 테스트")
    @Test
    void hitWhenEmptyDeckTest() {
        Assertions.assertThatThrownBy(() -> {
            for (int i = 0; i < 53; i++) {
                Card card = cards.hit();
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
