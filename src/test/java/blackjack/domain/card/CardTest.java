package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void 생성_및_비교() {
        Card card1 = Card.of(Denomination.FIVE, Shape.CLUBS);
        Card card2 = Card.of(Denomination.FIVE, Shape.CLUBS);

        assertThat(card1).isEqualTo(card2);
    }

}
