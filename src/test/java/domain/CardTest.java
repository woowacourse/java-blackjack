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

        @Test
        void 에이스의_점수를_계산한다() {
            //given
            final int limit = 21;
            final int score = 20;

            //when
            final Card card = new Card(CardScore.ACE, CardType.CLOVER);
            final int aceScore = card.calculateAceScore(score, limit);

            //then
            assertThat(aceScore).isEqualTo(1);
        }
    }
}
