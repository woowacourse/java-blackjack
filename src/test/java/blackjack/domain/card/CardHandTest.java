package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardHandTest {

    @DisplayName("가지고있는 카드들의 총합 가능성을 계산한다.")
    @Test
    void testCards() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.HEART, CardRank.TWO));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.EIGHT));

        Set<Integer> possibleSums = cardHand.calculatePossibleSum();

        assertThat(possibleSums).isEqualTo(Set.of(10));
    }

    @DisplayName("총합 가능성 계산 시 에이스가 1인 경우와 11인 경우 모두 계산한다.")
    @Test
    void testCards_ace() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.DIAMOND, CardRank.EIGHT));
        cardHand.add(new Card(CardSuit.HEART, CardRank.ACE));

        Set<Integer> possibleSums = cardHand.calculatePossibleSum();

        assertThat(possibleSums).isEqualTo(Set.of(9, 19));
    }

    @DisplayName("에이스가 여러개인 경우에도 정상적으로 계산한다.")
    @Test
    void testCards_multipleAce() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.DIAMOND, CardRank.EIGHT));
        cardHand.add(new Card(CardSuit.HEART, CardRank.ACE));
        cardHand.add(new Card(CardSuit.HEART, CardRank.ACE));

        Set<Integer> possibleSums = cardHand.calculatePossibleSum();

        assertThat(possibleSums).isEqualTo(Set.of(10, 20, 30));
    }

    @DisplayName("에이스가 4개인 경우에도 정상적으로 계산한다.")
    @Test
    void testCards_multipleAce4() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.HEART, CardRank.ACE));
        cardHand.add(new Card(CardSuit.HEART, CardRank.ACE));
        cardHand.add(new Card(CardSuit.HEART, CardRank.ACE));
        cardHand.add(new Card(CardSuit.HEART, CardRank.ACE));

        Set<Integer> possibleSums = cardHand.calculatePossibleSum();

        assertThat(possibleSums).isEqualTo(Set.of(4, 14, 24, 34, 44));
    }
}
