package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {

    static final int ADJUST_VALUE = 10;

    final Card card = new Card(Rank.ACE, Suit.CLUB);
    final AceAdjustPolicy aceAdjustPolicy = new AceAdjustPolicy(ADJUST_VALUE, new BustPolicyImpl());

    @Nested
    @DisplayName("1장 이상의 카드를 받아서 손패에 추가한다.")
    class AddCards {
        @Test
        @DisplayName("한장의 카드를 받아서 손패에 추가한다.")
        void addOneCard() {
            // given
            Hand hand = new Hand(aceAdjustPolicy);
            List<Card> existCards = hand.getCards();

            // when
            hand.addCard(card);

            // then
            List<Card> addedCards = hand.getCards();
            assertThat(addedCards.size()).isEqualTo(existCards.size() + 1);
        }
    }

    @Test
    @DisplayName("카드 점수의 합을 계산한다")
    void calculateTotalScore() {
        // given
        Hand hand = new Hand(aceAdjustPolicy);
        List<Card> cards = List.of(
                new Card(Rank.TWO, Suit.CLUB),
                new Card(Rank.THREE, Suit.CLUB)
        );
        hand.addCards(cards);

        int expectedScore = cards.stream()
                .mapToInt(Card::getValue)
                .sum();

        // when
        int actualScore = hand.calculateScore();

        // then
        assertThat(actualScore).isEqualTo(expectedScore);
    }
}
