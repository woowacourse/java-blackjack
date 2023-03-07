package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {
    private Cards cards;

    @BeforeEach
    void setup() {
        this.cards = new Cards();
    }

    @DisplayName("초기 카드 두 장의 합이 21인 경우, 블랙잭이다.")
    @Test
    void isBlackjackSuccessTest() {
        cards.add(Card.of(CardShape.CLUB, CardRank.ACE));
        cards.add(Card.of(CardShape.CLUB, CardRank.TEN));

        Assertions.assertThat(cards.isBlackjack())
                .isTrue();
    }

}
