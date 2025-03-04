package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CardTest {
    @Nested
    class cardConstructor{
        @Test
        void 카드를_생성한다(){
            Card card = new Card(CardScore.ACE, CardType.CLOVER);

            assertThat(card).isInstanceOf(Card.class);
            assertThat(card.getType()).isEqualTo(CardType.CLOVER);
            assertThat(card.getNumber()).isEqualTo(CardScore.ACE);
        }
    }
}
