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

import static org.assertj.core.api.Assertions.assertThat;

class GuestTest {

    @Test
    @DisplayName("guest 객체 생성 확인")
    void createGuest() {
        Guest guest = new Guest("guest", new PlayingCards());

        assertThat(guest).isInstanceOf(Guest.class);
    }

    @Test
    @DisplayName("카드를 할당 받았는지 확인")
    void checkAddCardToDeck() {
        Guest guest = new Guest("guest", new PlayingCards());
        PlayingCard playingCard = new PlayingCard(Suit.SPADE, Denomination.FOUR);
        guest.addCard(playingCard);

        Guest compareGuest = new Guest("compare_guest", new PlayingCards());
        compareGuest.addCard(playingCard);

        assertThat(guest).isEqualTo(compareGuest);
    }

    @Test
    @DisplayName("플레이어가 Bust인 경우")
    void checkPlayerIsBust() {
        Set<PlayingCard> cards = new HashSet<>();
        cards.add(new PlayingCard(Suit.SPADE, Denomination.JACK));
        cards.add(new PlayingCard(Suit.SPADE, Denomination.QUEEN));
        cards.add(new PlayingCard(Suit.SPADE, Denomination.TWO));

        Guest guest = new Guest("guest", new PlayingCards(cards));
        assertThat(guest.isBust()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 Bust가 아닌 경우")
    void checkPlayerIsNotBust() {
        Set<PlayingCard> cards = new HashSet<>();
        cards.add(new PlayingCard(Suit.SPADE, Denomination.JACK));
        cards.add(new PlayingCard(Suit.SPADE, Denomination.QUEEN));
        cards.add(new PlayingCard(Suit.SPADE, Denomination.ACE));

        Guest guest = new Guest("guest", new PlayingCards(cards));
        assertThat(guest.isBust()).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE:TWO:JACK:false", "SPADE:JACK:TWO:true"}, delimiter = ':')
    @DisplayName("플레이어 승패 확인")
    void checkPlayerResult(Suit suit, Denomination denomination, Denomination secondDenomination, boolean expected) {
        Set<PlayingCard> guestCards = new HashSet<>();
        guestCards.add(new PlayingCard(suit, denomination));
        Guest guest = new Guest("guest", new PlayingCards(guestCards));

        Set<PlayingCard> dealerCards = new HashSet<>();
        dealerCards.add(new PlayingCard(suit, secondDenomination));
        Dealer dealer = new Dealer("딜러", new PlayingCards(dealerCards));

        assertThat(guest.isWin(dealer)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE:JACK:JACK", "SPADE:ACE:ACE"}, delimiter = ':')
    @DisplayName("플레이어와 딜러 무승부 확인")
    void checkPlayerDraw(Suit suit, Denomination denomination, Denomination secondDenomination) {
        Set<PlayingCard> guestCards = new HashSet<>();
        guestCards.add(new PlayingCard(suit, denomination));
        Guest guest = new Guest("guest", new PlayingCards(guestCards));

        Set<PlayingCard> dealerCards = new HashSet<>();
        dealerCards.add(new PlayingCard(suit, secondDenomination));
        Dealer dealer = new Dealer("딜러", new PlayingCards(dealerCards));

        assertThat(dealer.isDraw(guest)).isTrue();
    }

    @Test
    @DisplayName("Hit 확인: 넘치지 않은 경우")
    void checkCanHit() {
        Set<PlayingCard> guestCards = new HashSet<>();
        guestCards.add(new PlayingCard(Suit.SPADE, Denomination.JACK));
        guestCards.add(new PlayingCard(Suit.SPADE, Denomination.QUEEN));
        Guest guest = new Guest("guest", new PlayingCards(guestCards));

        assertThat(guest.isCanHit()).isTrue();
    }

    @Test
    @DisplayName("Hit 확인: 넘친 경우")
    void checkCantHit() {
        Set<PlayingCard> guestCards = new HashSet<>();
        guestCards.add(new PlayingCard(Suit.SPADE, Denomination.JACK));
        guestCards.add(new PlayingCard(Suit.SPADE, Denomination.QUEEN));
        guestCards.add(new PlayingCard(Suit.SPADE, Denomination.ACE));
        guestCards.add(new PlayingCard(Suit.CLUB, Denomination.ACE));
        Guest guest = new Guest("guest", new PlayingCards(guestCards));

        assertThat(guest.isCanHit()).isFalse();
    }
}
