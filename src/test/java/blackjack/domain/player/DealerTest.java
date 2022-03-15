package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Denomination;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    @DisplayName("카드를 더 받을 수 있는지 확인: 16을 넘는 경우")
    public void checkPlayerDeckOverLimit() {
        Dealer dealer = new Dealer();
        dealer.addCard(new PlayingCard(Suit.SPADE, Denomination.JACK));
        dealer.addCard(new PlayingCard(Suit.SPADE, Denomination.SEVEN));
        boolean overLimit = dealer.isCanHit();

        assertThat(overLimit).isFalse();
    }

    @Test
    @DisplayName("카드를 더 받을 수 있는지 확인: 16을 넘지 않는 경우")
    public void checkPlayerDeckUnderLimit() {
        Dealer dealer = new Dealer();
        dealer.addCard(new PlayingCard(Suit.SPADE, Denomination.JACK));
        dealer.addCard(new PlayingCard(Suit.SPADE, Denomination.FIVE));
        boolean overLimit = dealer.isCanHit();

        assertThat(overLimit).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE:TWO:JACK:true", "SPADE:JACK:TWO:false"}, delimiter = ':')
    @DisplayName("딜러 승패 확인")
    void checkDealerResult(Suit suit, Denomination denomination, Denomination secondDenomination, boolean expected) {
        Guest guest = new Guest("guest");
        guest.addCard(new PlayingCard(suit, denomination));

        Dealer dealer = new Dealer();
        dealer.addCard(new PlayingCard(suit, secondDenomination));
        assertThat(dealer.isWin(guest)).isEqualTo(expected);
    }
}
