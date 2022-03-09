package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    @Test
    @DisplayName("카드 뭉치에서 카드 한 장을 꺼낸다.")
    void getCard() {
        Cards cards = Cards.create();

        assertThat(cards.draw()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드 뭉치의 카드가 52장인지 확인한다.")
    void draw52() {
        Cards cards = Cards.create();
        for (int i = 0; i < 52; i++) {
            cards.draw();
        }
        assertThat(cards.draw()).isNull();
    }
}
