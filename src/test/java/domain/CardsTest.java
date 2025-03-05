package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("기존 카드의 합이 21이 넘는다면 예외를 발생시킨다")
    @Test
    void test1() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.SIX, CardType.CLOVER),
                new Card(CardNumberType.EIGHT, CardType.DIAMOND),
                new Card(CardNumberType.JACK, CardType.DIAMOND));
        Cards cards = new Cards(testCards);

        //when & then
        Assertions.assertThatThrownBy(
                () -> cards.checkMaxSum()
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
