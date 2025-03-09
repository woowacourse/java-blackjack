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

        @Test
        void 에이스의_점수를_계산한다1() {
            //given
            final int limit = 21;
            final int score = 20;

            //when
            final Card card = new Card(CLOVER, ACE);
            final int aceScore = card.calculateAceScore(score, limit);

            //then
            assertThat(aceScore).isEqualTo(1);
        }

        @Test
        void 에이스의_점수를_계산한다2() {
            //given
            final int limit = 21;
            final int score = 10;

            //when
            final Card card = new Card(CLOVER, ACE);
            final int aceScore = card.calculateAceScore(score, limit);

            //then
            assertThat(aceScore).isEqualTo(11);
        }
    }
}
