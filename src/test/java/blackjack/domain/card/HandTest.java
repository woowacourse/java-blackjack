package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("패")
public class HandTest {

    @Test
    @DisplayName("뽑은 카드들의 합을 구한다.")
    void cardsSumTest() {
        // given
        Hand hand = new Hand(List.of());
        List<Card> cards = List.of(Card.DIAMOND_NINE, Card.DIAMOND_FIVE);
        int expectedScore = 9 + 5;

        // when
        hand.addCards(cards);

        // then
        assertThat(hand.findMaximumScore()).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("카드 점수의 합 중 21과 가장 가까운 수를 반환한다.")
    void maxScoreTest() {
        // given
        Hand hand = new Hand(List.of());

        // when
        hand.addCard(Card.SPADE_NINE);
        hand.addCard(Card.CLUB_QUEEN);
        hand.addCard(Card.CLUB_ACE);
        hand.addCard(Card.HEART_ACE);

        // then
        assertThat(hand.findMaximumScore()).isEqualTo(21);
    }
}
