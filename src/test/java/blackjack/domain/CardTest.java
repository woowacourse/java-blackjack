package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    @DisplayName("Card 객체를 생성한다.")
    @Test
    public void createCard() {
        Card card = new Card(Shape.SPACE, Value.ACE);

        assertThat(card).isInstanceOf(Card.class);
    }
}
