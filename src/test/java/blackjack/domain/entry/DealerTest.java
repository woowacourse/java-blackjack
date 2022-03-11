package blackjack.domain.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("두장의 카드를 지급받아 카드의 합을 계산한다.")
    void getTwoCards() {
        Dealer dealer = new Dealer(new HoldCards(Card.valueOf(Suit.SPADE, Number.KING), Card.valueOf(Suit.SPADE, Number.ACE)));

        assertThat(dealer.countCards()).isEqualTo(21);
    }

    @Test
    @DisplayName("보유한 카드의 합이 16이하인지 판단한다.")
    void shouldHaveMoreCard() {
        Dealer dealer = new Dealer(new HoldCards(Card.valueOf(Suit.SPADE, Number.EIGHT), Card.valueOf(Suit.HEART, Number.EIGHT)));

        assertThat(dealer.shouldHaveMoreCard()).isTrue();
    }
}
