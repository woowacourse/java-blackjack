package domain;

import domain.card.Card;
import domain.card.Cards;
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
    void cardsConstructTest() {
        Assertions.assertThat(cards).isInstanceOf(Cards.class);
    }

    @DisplayName("pop 기능 테스트")
    @Test
    void popTest() {
        Card popCard = cards.giveCard();

        Assertions.assertThat(popCard).isInstanceOf(Card.class);
    }

    @DisplayName("카드 덱을 모두 소모했을 때 예외 처리 테스트")
    @Test
    void popWhenEmptyDeckTest() {
        Assertions.assertThatThrownBy(() -> {
            for (int i = 0; i < 49; i++) {
                Card card = cards.giveCard();
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
