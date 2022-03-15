package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Denomination;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class GuestTest {

    @Test
    @DisplayName("guest 객체 생성 확인")
    void createGuest() {
        Guest guest = new Guest("guest");
        assertThat(guest).isInstanceOf(Guest.class);
    }

    @Test
    @DisplayName("카드 받았는지 확인")
    void checkAddCardToDeck() {
        Guest guest = new Guest("guest");
        PlayingCard playingCard = new PlayingCard(Suit.SPADE, Denomination.FOUR);
        guest.addCard(playingCard);

        Guest compareGuest = new Guest("compare_guest");
        compareGuest.addCard(playingCard);
        assertThat(guest).isEqualTo(compareGuest);
    }

    @Test
    @DisplayName("덱의 카드가 21이 넘는지 확인")
    void checkPlayerDeckOverLimit() {
        Guest guest = new Guest("guest");
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.JACK));
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.QUEEN));
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.TWO));
        boolean overLimit = guest.isBust();

        assertThat(overLimit).isTrue();
    }

    @Test
    @DisplayName("덱의 카드가 21이 넘지 않는지 확인")
    void checkPlayerDeckUnderLimit() {
        Guest guest = new Guest("guest");
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.JACK));
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.QUEEN));
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.ACE));
        boolean overLimit = guest.isBust();

        assertThat(overLimit).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE:TWO:JACK:false", "SPADE:JACK:TWO:true"}, delimiter = ':')
    @DisplayName("플레이어 승패 확인")
    void checkPlayerResult(Suit suit, Denomination denomination, Denomination secondDenomination, boolean expected) {
        Guest guest = new Guest("guest");
        guest.addCard(new PlayingCard(suit, denomination));

        Dealer dealer = new Dealer();
        dealer.addCard(new PlayingCard(suit, secondDenomination));
        assertThat(guest.isWin(dealer)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE:JACK:JACK", "SPADE:ACE:ACE"}, delimiter = ':')
    @DisplayName("무승부 확인")
    void checkPlayerDraw(Suit suit, Denomination denomination, Denomination secondDenomination) {
        Guest guest = new Guest("guest");
        guest.addCard(new PlayingCard(suit, denomination));

        Dealer dealer = new Dealer();
        dealer.addCard(new PlayingCard(suit, secondDenomination));
        assertThat(dealer.isDraw(guest)).isTrue();
    }

    @Test
    @DisplayName("Hit 확인: 넘치지 않은 경우")
    void checkCanHit() {
        Guest guest = new Guest("guest");
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.JACK));
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.QUEEN));

        assertThat(guest.isCanHit()).isTrue();
    }

    @Test
    @DisplayName("Hit 확인: 넘친 경우")
    void checkCantHit() {
        Guest guest = new Guest("guest");
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.JACK));
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.QUEEN));
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.ACE));
        guest.addCard(new PlayingCard(Suit.CLUB, Denomination.ACE));

        assertThat(guest.isCanHit()).isFalse();
    }
}
