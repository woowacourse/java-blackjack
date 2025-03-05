package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {
    @DisplayName("Hand 생성 시에 카드는 두 장을 넣을 수 있다")
    @Test
    void test1() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);

        // when & then
        assertThatCode(() -> new Hand(card1, card2))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드를 추가한다")
    @Test
    void test2() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand(card1, card2);

        Card newCard = new Card(CardSuit.SPADE, CardRank.KING);

        // when
        hand.addCard(newCard);

        // then
        assertThat(hand.getAllCards()).containsExactly(card1, card2, newCard);
    }

    @DisplayName("가능한 카드의 모든 합을 반환한다")
    @Test
    void test3() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.ACE);
        Hand hand = new Hand(card1, card2);

        // when
        List<Integer> totalValues = hand.getPossibleSums();

        // then
        assertThat(totalValues).containsExactlyInAnyOrder(2, 12, 22);
    }

    @DisplayName("모든 카드를 반환한다.")
    @Test
    void test5() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand(card1, card2);

        // when
        List<Card> cards = hand.getAllCards();

        // then
        assertThat(cards).containsExactly(card1, card2);
    }
}
