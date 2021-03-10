package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("16 이하의 카드를 받았을 때 더 받을 수 있는지 확인")
    void createDealer_drawable() {
        Dealer dealer = Dealer.of(
            Card.of(CardNumber.TEN, Symbol.CLOVER),
            Card.of(CardNumber.SIX, Symbol.CLOVER)
        );

        assertThat(dealer.drawable()).isTrue();
    }

    @Test
    @DisplayName("16 초과의 카드를 받았을 때")
    void createDealer_notDrawable() {
        Dealer dealer = Dealer.of(
            Card.of(CardNumber.ACE, Symbol.CLOVER),
            Card.of(CardNumber.SIX, Symbol.CLOVER)
        );

        assertThat(dealer.drawable()).isFalse();
    }
}