package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HoldingCardTest {

    @Test
    @DisplayName("합이 21초과일 경우 버스트다")
    void over21_isBust() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(Symbol.CLOVER, CardNumber.QUEEN), new Card(Symbol.CLOVER, CardNumber.JACK)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(new Card(Symbol.CLOVER, CardNumber.FIVE));
        Assertions.assertThat(holdingCard.isBust()).isTrue();
    }

    @Test
    @DisplayName("합이 21보다 작을 경우 버스트가 아니다.")
    void under21_isNotBust() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(Symbol.CLOVER, CardNumber.EIGHT), new Card(Symbol.CLOVER, CardNumber.SEVEN)));
        HoldingCard holdingCard = new HoldingCard(cards);
        holdingCard.add(new Card(Symbol.CLOVER, CardNumber.SIX));
        Assertions.assertThat(holdingCard.isBust()).isFalse();
    }

}
