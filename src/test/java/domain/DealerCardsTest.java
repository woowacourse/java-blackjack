package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerCardsTest {

    @Test
    @DisplayName("카드가 17을 넘는지 확인")
    void isOverSixteen() {
        DealerCards dealerCards = new DealerCards();
        dealerCards.add(new Card(Type.DIAMOND, Symbol.TWO));
        dealerCards.add(new Card(Type.DIAMOND, Symbol.THREE));
        dealerCards.add(new Card(Type.DIAMOND, Symbol.JACK));
        Assertions.assertThat(dealerCards.isOverSixteen())
            .isFalse();

        dealerCards.add(new Card(Type.CLUB, Symbol.TWO));
        Assertions.assertThat(dealerCards.isOverSixteen())
            .isTrue();
    }
}
