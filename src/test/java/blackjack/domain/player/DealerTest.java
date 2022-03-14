package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Denomination;

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
        PlayingCard playingCard = new PlayingCard(Suit.SPADE, Denomination.FOUR);
        dealer.addCard(playingCard);

        Dealer compareDealer = new Dealer();
        compareDealer.addCard(playingCard);

        assertThat(dealer).isEqualTo(compareDealer);
    }

    @Test
    @DisplayName("덱의 카드가 16이 넘는지 확인")
    public void checkPlayerDeckOverLimit() {
        Dealer dealer = new Dealer();
        dealer.addCard(new PlayingCard(Suit.SPADE, Denomination.JACK));
        dealer.addCard(new PlayingCard(Suit.SPADE, Denomination.SEVEN));
        boolean overLimit = dealer.isOverMoreCardLimit();

        assertThat(overLimit).isTrue();
    }

    @Test
    @DisplayName("덱의 카드가 16이 넘지 않는지 확인")
    public void checkPlayerDeckUnderLimit() {
        Dealer dealer = new Dealer();
        dealer.addCard(new PlayingCard(Suit.SPADE, Denomination.JACK));
        dealer.addCard(new PlayingCard(Suit.SPADE, Denomination.SIX));
        boolean overLimit = dealer.isOverMoreCardLimit();

        assertThat(overLimit).isFalse();
    }
}
