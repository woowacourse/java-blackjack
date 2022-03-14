package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Card card = new Card(Suit.SPADE, Symbol.FOUR);
        guest.addCard(card);

        Guest compareGuest = new Guest("compare_guest");
        compareGuest.addCard(card);
        assertThat(guest).isEqualTo(compareGuest);
    }

    @Test
    @DisplayName("덱의 카드가 21이 넘는지 확인")
    public void checkPlayerDeckOverLimit() {
        Guest guest = new Guest("guest");
        guest.addCard(new Card(Suit.SPADE, Symbol.JACK));
        guest.addCard(new Card(Suit.SPADE, Symbol.QUEEN));
        guest.addCard(new Card(Suit.SPADE, Symbol.TWO));
        boolean overLimit = guest.isOverPointLimit();

        assertThat(overLimit).isTrue();
    }

    @Test
    @DisplayName("덱의 카드가 21이 넘지 않는지 확인")
    public void checkPlayerDeckUnderLimit() {
        Guest guest = new Guest("guest");
        guest.addCard(new Card(Suit.SPADE, Symbol.JACK));
        guest.addCard(new Card(Suit.SPADE, Symbol.QUEEN));
        guest.addCard(new Card(Suit.SPADE, Symbol.ACE));
        boolean overLimit = guest.isOverPointLimit();

        assertThat(overLimit).isFalse();
    }
}
