package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class HandTest {
    @DisplayName("카드를 뽑는 과정에서 카드의 총 점수를 계산한다.")
    @Nested
    class calculateScoreWhilePicking {
        @DisplayName("에이스 카드가 없는 경우 단순 합산한다.")
        @Test
        void calculateScoreWithNoAce() {
            Hand hand = new Hand();
            hand.saveCard(new Card(Denomination.EIGHT, Suit.CLOVER));
            hand.saveCard(new Card(Denomination.NINE, Suit.CLOVER));

            int totalScore = hand.calculateScore();
            assertThat(totalScore).isEqualTo(17);
        }

        @DisplayName("에이스 카드가 포함된 카드를 받았을 때 1로 계산한다.")
        @Test
        void drawAceCardAndCalculateScoreOne() {
            Hand hand = new Hand();
            hand.saveCard(new Card(Denomination.EIGHT, Suit.CLOVER));
            hand.saveCard(new Card(Denomination.ACE, Suit.CLOVER));
            hand.saveCard(new Card(Denomination.THREE, Suit.CLOVER));

            int totalScore = hand.calculateScore();
            assertThat(totalScore).isEqualTo(12);
        }
    }

    @DisplayName("최종 점수를 구하는 과정에서 카드의 총 점수를 계산한다.")
    @Nested
    class CalculateScoreWhileJudging {
        @DisplayName("에이스 카드가 없는 경우 단순 합산한다.")
        @Test
        void calculateScoreWithNoAce() {
            Hand hand = new Hand();
            hand.saveCard(new Card(Denomination.EIGHT, Suit.CLOVER));
            hand.saveCard(new Card(Denomination.NINE, Suit.CLOVER));

            int totalScore = hand.calculateResultScore();
            assertThat(totalScore).isEqualTo(17);
        }

        @DisplayName("에이스 카드가 11로 계산되었을 때 21을 초과하면 1로 계산한다.")
        @Test
        void calculateScoreWithAceIfBusted() {
            Hand hand = new Hand();
            hand.saveCard(new Card(Denomination.EIGHT, Suit.CLOVER));
            hand.saveCard(new Card(Denomination.ACE, Suit.CLOVER));
            hand.saveCard(new Card(Denomination.THREE, Suit.CLOVER));

            int totalScore = hand.calculateResultScore();
            assertThat(totalScore).isEqualTo(12);
        }
    }
}
