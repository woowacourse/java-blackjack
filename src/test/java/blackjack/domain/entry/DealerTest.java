package blackjack.domain.entry;

import static org.assertj.core.api.Assertions.assertThat;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("두장의 카드를 지급받아 카드의 합을 계산한다.")
    void getTwoCards() {
        Dealer dealer = new Dealer(new HoldCards(Card.valueOf(Suit.SPADE, CardNumber.KING), Card.valueOf(Suit.SPADE, CardNumber.ACE)));

        assertThat(dealer.countCards()).isEqualTo(21);
    }

    @Test
    @DisplayName("보유한 카드의 합이 16이하인지 판단한다.")
    void shouldHaveMoreCard() {
        Dealer dealer = new Dealer(new HoldCards(Card.valueOf(Suit.SPADE, CardNumber.EIGHT), Card.valueOf(Suit.HEART, CardNumber.EIGHT)));

        assertThat(dealer.canHit()).isTrue();
    }
}
