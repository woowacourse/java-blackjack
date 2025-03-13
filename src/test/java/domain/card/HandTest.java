package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import card.CardNumberType;
import card.CardType;
import card.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @DisplayName("카드 묶음의 전체 카드를 추가할 수 있다")
    @Test
    void test13() {
        //given
        List<Card> testCards = List.of(
                new Card(CardNumberType.ACE, CardType.HEART),
                new Card(CardNumberType.ACE, CardType.DIAMOND)
        );
        Hand hand = new Hand(testCards);
        Hand emptyHand = Hand.createEmpty();

        //when
        emptyHand.addAll(hand);

        //then
        assertThat(emptyHand.getCards()).hasSize(2);
        assertThat(emptyHand.getCards()).containsExactlyInAnyOrderElementsOf(testCards);
    }

    @DisplayName("새로 배분된 카드를 저장한다")
    @Test
    void test2() {
        //given
        Hand hand = Hand.createEmpty();
        Card testCard = new Card(CardNumberType.SIX, CardType.CLOVER);

        //when
        hand.add(testCard);

        //then
        assertThat(hand.getCards()).contains(testCard);
    }
}
