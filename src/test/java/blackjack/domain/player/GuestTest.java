package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Denomination;

import static org.assertj.core.api.Assertions.assertThat;

class GuestTest {

    @Test
    @DisplayName("guest 객체 생성 확인")
    public void createGuest() {
        Guest guest = new Guest("guest");
        assertThat(guest).isInstanceOf(Guest.class);
    }

    @Test
    @DisplayName("카드 받았는지 확인")
    public void checkAddCardToDeck() {
        Guest guest = new Guest("guest");
        PlayingCard playingCard = new PlayingCard(Suit.SPADE, Denomination.FOUR);
        guest.addCard(playingCard);

        Guest compareGuest = new Guest("compare_guest");
        compareGuest.addCard(playingCard);
        assertThat(guest).isEqualTo(compareGuest);
    }

    @Test
    @DisplayName("덱의 카드가 21이 넘는지 확인")
    public void checkPlayerDeckOverLimit() {
        Guest guest = new Guest("guest");
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.JACK));
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.QUEEN));
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.TWO));
        boolean overLimit = guest.isBust();

        assertThat(overLimit).isTrue();
    }

    @Test
    @DisplayName("덱의 카드가 21이 넘지 않는지 확인")
    public void checkPlayerDeckUnderLimit() {
        Guest guest = new Guest("guest");
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.JACK));
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.QUEEN));
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.ACE));
        boolean overLimit = guest.isBust();

        assertThat(overLimit).isFalse();
    }
}
