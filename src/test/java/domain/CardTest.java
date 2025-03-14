package domain;

import domain.card.Card;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static domain.card.CardScore.ACE;
import static domain.card.CardType.CLOVER;
import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    @Nested
    class cardConstructor {
        @Test
        void 카드를_생성한다() {
            Card card = new Card(CLOVER, ACE);

            assertThat(card).isInstanceOf(Card.class);
            assertThat(card).isEqualTo(new Card(CLOVER, ACE));
        }
    }
}
