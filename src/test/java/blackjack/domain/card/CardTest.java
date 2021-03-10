package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    private Card card;

    public void setUp() {
        card = new Card(Shape.SPACE, Value.ACE);
    }

    @DisplayName("Card 객체를 생성한다.")
    @Test
    public void createCard() {
        setUp();

        assertThat(card).isInstanceOf(Card.class);
    }

    @DisplayName("Card 객체의 값을 확인한다.")
    @Test
    public void checkValue() {
        setUp();

        assertThat(card.getValue()).isEqualTo(11);
    }

    @DisplayName("카드에 에이스가 있는지 확인한다. - 에이스가 있는 경우")
    @Test
    public void hasAceTrue() {
        setUp();

        assertThat(card.hasAce()).isTrue();
    }

    @DisplayName("카드에 에이스가 있는지 확인한다. - 에이스가 없는 경우")
    @Test
    public void hasAceFalse() {
        Card card = new Card(Shape.CLOVER, Value.FIVE);

        assertThat(card.hasAce()).isFalse();
    }
}
