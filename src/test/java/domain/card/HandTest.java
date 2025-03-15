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

    @DisplayName("새로 배분된 카드를 저장한다")
    @Test
    void test2() {
        //given
        Hand hand = Hand.createEmpty();
        Card testCard = new Card(CardNumberType.SIX, CardType.CLOVER);

        //when
        Hand addedHand = hand.add(testCard);

        //then
        assertThat(addedHand).isEqualTo(new Hand(List.of(new Card(CardNumberType.SIX, CardType.CLOVER))));
    }
}
