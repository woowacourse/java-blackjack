package blackjack.domain.cardholder;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("카드를 추가한다")
    @Test
    void test2() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand();
        hand.takeCard(card1);
        hand.takeCard(card2);

        Card newCard = new Card(CardSuit.SPADE, CardRank.KING);

        // when
        hand.takeCard(newCard);

        // then
        assertThat(hand.getAllCards()).containsExactly(card1, card2, newCard);
    }

    @DisplayName("가능한 카드의 모든 합을 반환한다")
    @Test
    void test3() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.ACE);
        Hand hand = new Hand();
        hand.takeCard(card1);
        hand.takeCard(card2);

        // when
        List<Integer> totalValues = hand.calculatePossibleSums();

        // then
        assertThat(totalValues).containsExactlyInAnyOrder(2, 12, 22);
    }

    @DisplayName("모든 카드를 반환한다.")
    @Test
    void test5() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand();
        hand.takeCard(card1);
        hand.takeCard(card2);

        // when
        List<Card> cards = hand.getAllCards();

        // then
        assertThat(cards).containsExactly(card1, card2);
    }
}
