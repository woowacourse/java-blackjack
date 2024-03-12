package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.constants.Score;
import domain.constants.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class DeckTest {
    @DisplayName("블랙잭에 필요한 카드들을 생성해둔다.")
    @Order(1)
    @Test
    void createCards() {
        assertThat(Deck.getSize()).isEqualTo(52);
    }

    @DisplayName("하나의 카드를 뽑는다.")
    @Order(2)
    @Test
    void pickCard() {
        Card card = Deck.pick(0);

        assertThat(card).isEqualTo(new Card(Score.ACE, Shape.SPADE));
    }
}
