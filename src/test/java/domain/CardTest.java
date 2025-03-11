package domain;

import static domain.card.CardScore.*;
import static domain.card.CardType.*;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardScore;
import domain.card.CardType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
