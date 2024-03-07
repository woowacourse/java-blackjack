package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 카드의 합이 17 미만인 경우 카드가 더 필요하다.")
    void needMoreCard() {
        Card card1 = new Card(CardNumber.SEVEN, CardShape.HEART);
        Card card2 = new Card(CardNumber.NINE, CardShape.HEART);

        Dealer dealer = new Dealer(new Deck(Set.of(card1, card2)));
        dealer.addStartCard();

        boolean needMoreCard = dealer.needMoreCard();

        assertThat(needMoreCard).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드의 합이 17 이상인 경우 카드가 더 필요하지 않다.")
    void needNoMoreCard() {
        Card card1 = new Card(CardNumber.NINE, CardShape.HEART);
        Card card2 = new Card(CardNumber.EIGHT, CardShape.HEART);

        Dealer dealer = new Dealer(new Deck(Set.of(card1, card2)));
        dealer.addStartCard();

        boolean needMoreCard = dealer.needMoreCard();

        System.out.println(dealer.calculate().getValue());
        assertThat(needMoreCard).isFalse();
    }
}
