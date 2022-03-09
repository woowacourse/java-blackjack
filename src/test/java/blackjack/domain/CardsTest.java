package blackjack.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @Nested
    @DisplayName("getTotalNumber는")
    class GetTotalNumber {

        @Test
        @DisplayName("가지고 있는 카드의 총합을 반환한다.")
        void returnTotalNumber() {
            Cards cards = new Cards(
                List.of(Card.of(CardPattern.CLOVER, CardNumber.J), Card.of(CardPattern.HEART, CardNumber.EIGHT)));

            Assertions.assertThat(cards.getTotalNumber()).isEqualTo(18);
        }
    }

    @Nested
    @DisplayName("add는")
    class Add {

        @Test
        @DisplayName("카드를 추가한다.")
        void addCard() {
            Cards cards = new Cards(
                List.of(Card.of(CardPattern.CLOVER, CardNumber.J), Card.of(CardPattern.HEART, CardNumber.EIGHT)));
            cards.add(Card.of(CardPattern.CLOVER, CardNumber.FOUR));

            Assertions.assertThat(cards.getTotalNumber()).isEqualTo(22);
        }
    }
}
