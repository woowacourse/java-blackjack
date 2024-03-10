package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("핸드에 카드를 추가한다")
    @Test
    void testAppend() {
        // given
        Card card = new Card(CardRank.THREE, CardSuit.DIAMOND);

        Hand hand = new Hand(new ArrayList<>());

        // when
        hand.append(card);

        // then
        assertThat(hand.getCards()).containsExactly(card);
    }

    @DisplayName("임계값에 가장 가까운 핸드의 합을 계산한다.")
    @Test
    void testCalculateScoreSumClosestToThreshold() {
        // given
        Card card1 = new Card(CardRank.TWO, CardSuit.HEART);
        Card card2 = new Card(CardRank.EIGHT, CardSuit.SPADE);
        Card card3 = new Card(CardRank.ACE, CardSuit.CLUB);

        Hand hand = new Hand(List.of(card1, card2, card3));

        // when
        int actual = hand.calculateScoreTotalClosestToThreshold(21);

        // then
        assertThat(actual).isEqualTo(21);
    }
}
