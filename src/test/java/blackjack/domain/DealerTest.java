package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("dealer 객체 생성 확인")
    public void createDealer() {
       Dealer dealer = new Dealer();
        assertThat(dealer).isInstanceOf(Dealer.class);
    }

    @Test
    @DisplayName("카드 받았는지 확인")
    public void checkAddCardToDeck() {
        Dealer dealer = new Dealer();
        Card card = new Card(Suit.SPADE, Rank.FOUR);
        dealer.addCard(card);

        Dealer compareDealer = new Dealer();
        compareDealer.addCard(card);
        assertThat(dealer).isEqualTo(compareDealer);
    }

    @Test
    @DisplayName("덱의 카드가 16이 넘는지 확인")
    public void checkPlayerDeckOverLimit() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Suit.SPADE, Rank.JACK));
        dealer.addCard(new Card(Suit.SPADE, Rank.SEVEN));
        boolean overLimit = dealer.isOverLimit(16);
        assertThat(overLimit).isTrue();
    }

    @Test
    @DisplayName("덱의 카드가 16이 넘지 않는지 확인")
    public void checkPlayerDeckUnderLimit() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Suit.SPADE, Rank.JACK));
        dealer.addCard(new Card(Suit.SPADE, Rank.SIX));
        boolean overLimit = dealer.isOverLimit(16);
        assertThat(overLimit).isFalse();
    }
}