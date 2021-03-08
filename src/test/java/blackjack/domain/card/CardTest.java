package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    private Card card;

    @BeforeEach
    public void setUp() {
        card = new Card(Shape.SPACE, Value.ACE);
    }

    @DisplayName("Card 객체를 생성한다.")
    @Test
    public void createCard() {
        assertThat(card).isInstanceOf(Card.class);
    }

    @DisplayName("Card 객체의 값을 확인한다.")
    @Test
    public void checkValue() {
        assertThat(card.getValue()).isEqualTo(11);
    }
}
