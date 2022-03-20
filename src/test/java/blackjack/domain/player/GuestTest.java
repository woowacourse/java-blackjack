package blackjack.domain.player;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.PlayingCards;
import blackjack.domain.Fixtures;

import static org.assertj.core.api.Assertions.assertThat;

class GuestTest {


    @Test
    @DisplayName("guest 객체 생성 확인")
    void createGuest() {
        Guest guest = Fixtures.guest;

        assertThat(guest).isInstanceOf(Guest.class);
    }

    @Test
    @DisplayName("카드를 할당 받았는지 확인")
    void checkAddCardToDeck() {
        Guest guest = Fixtures.guest;
        PlayingCard playingCard = Fixtures.SPADE_ACE;
        guest.addCard(playingCard);

        Guest compareGuest = new Guest("compare_guest", new PlayingCards(), 100);
        compareGuest.addCard(playingCard);

        assertThat(guest).isEqualTo(compareGuest);
    }

    @Test
    @DisplayName("게스트가 Bust인 경우")
    void checkGuestIsBust() {
        Set<PlayingCard> cards = new HashSet<>();
        cards.add(Fixtures.SPADE_JACK);
        cards.add(Fixtures.SPADE_NINE);
        cards.add(Fixtures.SPADE_EIGHT);

        Guest guest = new Guest("guest", new PlayingCards(cards), 100);
        assertThat(guest.isBust()).isTrue();
    }

    @Test
    @DisplayName("게스트가 Bust가 아닌 경우")
    void checkGuestIsNotBust() {
        Set<PlayingCard> cards = new HashSet<>();
        cards.add(Fixtures.SPADE_JACK);
        cards.add(Fixtures.SPADE_NINE);
        cards.add(Fixtures.SPADE_TWO);

        Guest guest = new Guest("guest", new PlayingCards(cards), 100);
        assertThat(guest.isBust()).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE:TWO:JACK:false", "SPADE:JACK:TWO:true"}, delimiter = ':')
    @DisplayName("플레이어 승패 확인")
    void checkPlayerResult(Suit suit, Denomination denomination, Denomination secondDenomination, boolean expected) {
        Set<PlayingCard> guestCards = new HashSet<>();
        guestCards.add(new PlayingCard(suit, denomination));
        Guest guest = new Guest("guest", new PlayingCards(guestCards), 100);

        Set<PlayingCard> dealerCards = new HashSet<>();
        dealerCards.add(new PlayingCard(suit, secondDenomination));
        Dealer dealer = new Dealer("딜러", new PlayingCards(dealerCards));

        assertThat(guest.isWin(dealer)).isEqualTo(expected);
    }

    @Test
    @DisplayName("게스트 Hit 확인: 넘치지 않은 경우")
    void checkGuestCanHit() {
        Set<PlayingCard> guestCards = new HashSet<>();
        guestCards.add(Fixtures.SPADE_NINE);
        guestCards.add(Fixtures.SPADE_ACE);
        Guest guest = new Guest("guest", new PlayingCards(guestCards), 100);

        assertThat(guest.isHit()).isTrue();
    }

    @Test
    @DisplayName("게스트 Hit 확인: 넘친 경우")
    void checkGuestCantHit() {
        Set<PlayingCard> guestCards = new HashSet<>();
        guestCards.add(Fixtures.SPADE_NINE);
        guestCards.add(Fixtures.SPADE_EIGHT);
        guestCards.add(Fixtures.SPADE_JACK);
        Guest guest = new Guest("guest", new PlayingCards(guestCards), 100);

        assertThat(guest.isHit()).isFalse();
    }
}
