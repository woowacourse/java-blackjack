package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("두장의 카드를 지급받아 카드의 합을 계산한다.")
    void getTwoCards() {
        Card card1 = Card.valueOf(Suit.SPADE, Number.KING);
        Card card2 = Card.valueOf(Suit.SPADE, Number.ACE);
        Dealer dealer = new Dealer(card1, card2);

        assertThat(dealer.countCards()).isEqualTo(21);
    }

    @Test
    @DisplayName("보유한 카드의 합이 16이하인지 판단한다.")
    void shouldHaveMoreCard() {
        Card card1 = Card.valueOf(Suit.SPADE, Number.EIGHT);
        Card card2 = Card.valueOf(Suit.HEART, Number.EIGHT);
        Dealer dealer = new Dealer(card1, card2);

        assertThat(dealer.shouldHaveMoreCard()).isTrue();
    }

    @Test
    @DisplayName("한장의 카드를 추가한다.")
    void putCard() {
        Card card1 = Card.valueOf(Suit.SPADE, Number.NINE);
        Card card2 = Card.valueOf(Suit.SPADE, Number.ACE);
        Dealer dealer = new Dealer(card1, card2);
        dealer.putCard(Card.valueOf(Suit.SPADE, Number.ACE));

        assertThat(dealer.countCards()).isEqualTo(21);
    }
}
