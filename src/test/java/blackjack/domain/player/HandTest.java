package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class HandTest {

    @Test
    @DisplayName("add()는 카드를 하나씩 추가한다.")
    void add_test() {
        // given
        Hand hand = new Hand(List.of(new Card(CardNumber.TWO, CardSymbol.HEART),new Card(CardNumber.THREE, CardSymbol.HEART)));

        Hand expected = hand.add(new Card(CardNumber.TWO, CardSymbol.CLOVER));
        // then
        System.out.println(expected.getHand().size());
        Assertions.assertThat(expected.getHand()).hasSize(2);
    }

}
