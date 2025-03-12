package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CardHandTest {

    @Nested
    @DisplayName("손패로 만들 수 있는 21이하의 가장 높은 값이 손패의 점수이다.")
    class CardHandScore {

        @DisplayName("Ace가 없는 경우 손패의 합이 그대로 점수가 된다.")
        @Test
        void testCards() {
            // given
            CardHand cardHand = new CardHand();
            cardHand.add(new Card(CardSuit.HEART, CardRank.TWO));
            cardHand.add(new Card(CardSuit.CLUB, CardRank.EIGHT));

            // when
            Score score = cardHand.getScore();

            // then
            assertThat(score.intValue()).isEqualTo(10);
        }

        @DisplayName("Ace를 11로 계산했을 때 21이 넘지 않는다면 11로 계산한다.")
        @Test
        void testCards_ace() {
            // given
            CardHand cardHand = new CardHand();
            cardHand.add(new Card(CardSuit.DIAMOND, CardRank.EIGHT));
            cardHand.add(new Card(CardSuit.HEART, CardRank.ACE));

            // when
            Score score = cardHand.getScore();

            // then
            assertThat(score.intValue()).isEqualTo(19);
        }

        @DisplayName("Ace가 2개라면 2개 중 1개는 1로 계산된다.")
        @Test
        void testCards_multipleAce() {
            // given
            CardHand cardHand = new CardHand();
            cardHand.add(new Card(CardSuit.DIAMOND, CardRank.EIGHT));
            cardHand.add(new Card(CardSuit.HEART, CardRank.ACE));
            cardHand.add(new Card(CardSuit.HEART, CardRank.ACE)); // 1로계산

            // when
            Score score = cardHand.getScore();

            // then
            assertThat(score.intValue()).isEqualTo(20);
        }

        @DisplayName("Ace가 4개일 때 한 개를 제외하고는 1로 계산된다.")
        @Test
        void testCards_multipleAce4() {
            // given
            CardHand cardHand = new CardHand();
            cardHand.add(new Card(CardSuit.HEART, CardRank.ACE));
            cardHand.add(new Card(CardSuit.HEART, CardRank.ACE));
            cardHand.add(new Card(CardSuit.HEART, CardRank.ACE));
            cardHand.add(new Card(CardSuit.HEART, CardRank.ACE));

            // when
            Score score = cardHand.getScore();

            // then
            assertThat(score.intValue()).isEqualTo(14);
        }
    }
}
