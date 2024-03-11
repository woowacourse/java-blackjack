package domain;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(Deck.getTotalSize()).isEqualTo(52);
    }

    @DisplayName("하나의 카드를 뽑는다.")
    @Order(2)
    @Test
    void pickCard() {
        int beforeSize = Deck.getTotalSize();
        Deck.pick();
        int afterSize = Deck.getTotalSize();

        assertThat(beforeSize - afterSize).isEqualTo(1);
    }
}
