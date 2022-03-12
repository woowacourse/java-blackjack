package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Guest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GuestTest {

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
        Card card = new Card(Suit.SPADE, Rank.FOUR);
        guest.addCard(card);

        Guest compareGuest = new Guest("compare_guest");
        compareGuest.addCard(card);
        assertThat(guest).isEqualTo(compareGuest);
    }

    @Test
    @DisplayName("덱의 카드가 21이 넘는지 확인")
    public void checkPlayerDeckOverLimit() {
        Guest guest = new Guest("guest");
        guest.addCard(new Card(Suit.SPADE, Rank.JACK));
        guest.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        guest.addCard(new Card(Suit.SPADE, Rank.TWO));

        boolean overLimit = guest.isOverLimit(21);
        assertThat(overLimit).isTrue();
    }

    @Test
    @DisplayName("덱의 카드가 21이 넘지 않는지 확인")
    public void checkPlayerDeckUnderLimit() {
        Guest guest = new Guest("guest");
        guest.addCard(new Card(Suit.SPADE, Rank.JACK));
        guest.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        guest.addCard(new Card(Suit.SPADE, Rank.ACE));
        boolean overLimit = guest.isOverLimit(21);
        assertThat(overLimit).isFalse();
    }
}
