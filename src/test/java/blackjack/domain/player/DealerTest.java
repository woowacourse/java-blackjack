package blackjack.domain.player;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.MockDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.HoldCards;

public class DealerTest {

    @Nested
    @DisplayName("canHit은")
    class CanHit {
        @Test
        @DisplayName("카드의 총합이 16보다 같거나작으면 true")
        void it_return_true() {
            MockDeck deck = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.SIX)));

            Dealer dealer = new Dealer(HoldCards.drawTwoCards(deck));
            Assertions.assertThat(dealer.canHit()).isTrue();
        }

        @Test
        @DisplayName("카드의 총합이 16보다 크면 false")
        void it_return_false() {
            MockDeck deck = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.SEVEN)));

            Dealer dealer = new Dealer(HoldCards.drawTwoCards(deck));
            Assertions.assertThat(dealer.canHit()).isFalse();
        }
    }
}
