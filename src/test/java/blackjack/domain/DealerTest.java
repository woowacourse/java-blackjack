package blackjack.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 카드의 합이 16이하인 경우 카드가 더 필요하다.")
    void needMoreCard() {
        Card card1 = new Card(CardNumber.NINE, CardShape.HEART);
        Card card2 = new Card(CardNumber.SEVEN, CardShape.HEART);
        Dealer dealer = new Dealer(new Cards(List.of(card1, card2)));

        boolean needMoreCard = dealer.needMoreCard();

        Assertions.assertThat(needMoreCard).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드의 합이 16초과인 경우 카드가 더 필요하지 않다.")
    void needNoMoreCard() {
        Card card1 = new Card(CardNumber.NINE, CardShape.HEART);
        Card card2 = new Card(CardNumber.EIGHT, CardShape.HEART);
        Dealer dealer = new Dealer(new Cards(List.of(card1, card2)));

        boolean needMoreCard = dealer.needMoreCard();

        Assertions.assertThat(needMoreCard).isFalse();
    }
}
