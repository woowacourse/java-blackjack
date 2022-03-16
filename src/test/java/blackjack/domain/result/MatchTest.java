package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Denomination;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.card.PlayingCards;

class MatchTest {

    @ParameterizedTest
    @CsvSource(value = {"SPADE:CLUB:ACE:JACK:JACK:DRAW", "SPADE:CLUB:ACE:JACK:FIVE:WIN", "SPADE:CLUB:ACE:THREE:FIVE:LOSE"}
            , delimiter = ':')
    @DisplayName("승무패 결정 로직 확인")
    public void checkInitCardFindWinner(Suit suit, Suit secondSuit, Denomination denomination,
                                        Denomination secondDenomination, Denomination thirdDenomination, Match result) {
        Set<PlayingCard> guestCards = new HashSet<>();
        guestCards.add(new PlayingCard(suit, denomination));
        guestCards.add(new PlayingCard(secondSuit, secondDenomination));
        Player guest = new Guest("guest", new PlayingCards(guestCards));

        Set<PlayingCard> dealerCards = new HashSet<>();
        dealerCards.add(new PlayingCard(suit, denomination));
        dealerCards.add(new PlayingCard(secondSuit, thirdDenomination));
        Player dealer = new Dealer("딜러", new PlayingCards(dealerCards));

        assertThat(Match.findWinner(guest, dealer)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE:CLUB:ACE:JACK:FIVE:LOSE"}, delimiter = ':')
    @DisplayName("처음 2장의 카드 이후(플레이어는 블랙잭이 아닌 21, 딜러는 블랙잭인 경우): 승무패 결정 로직 확인")
    public void checkBlackjackDealer(Suit suit, Suit secondSuit, Denomination denomination, Denomination secondDenomination,
                                     Denomination thirdDenomination, Match result) {
        Set<PlayingCard> guestCards = new HashSet<>();
        guestCards.add(new PlayingCard(suit, denomination));
        guestCards.add(new PlayingCard(secondSuit, thirdDenomination));
        guestCards.add(new PlayingCard(secondSuit, thirdDenomination));
        Player guest = new Guest("guest", new PlayingCards(guestCards));

        Set<PlayingCard> dealerCards = new HashSet<>();
        dealerCards.add(new PlayingCard(suit, denomination));
        dealerCards.add(new PlayingCard(secondSuit, secondDenomination));
        Player dealer = new Dealer("딜러", new PlayingCards(dealerCards));

        assertThat(Match.findWinner(guest, dealer)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE:CLUB:ACE:JACK:FIVE:LOSE"}, delimiter = ':')
    @DisplayName("처음 2장의 카드 이후(플레이어는 블랙잭이 아닌 21, 딜러는 블랙잭인 경우): 승무패 결정 로직 확인")
    public void checkWinner(Suit suit, Suit secondSuit, Denomination denomination, Denomination secondDenomination,
                                  Denomination thirdDenomination, Match result) {
        Set<PlayingCard> guestCards = new HashSet<>();
        guestCards.add(new PlayingCard(suit, denomination));
        guestCards.add(new PlayingCard(secondSuit, thirdDenomination));
        guestCards.add(new PlayingCard(secondSuit, thirdDenomination));
        Player guest = new Guest("guest", new PlayingCards(guestCards));

        Set<PlayingCard> dealerCards = new HashSet<>();
        dealerCards.add(new PlayingCard(suit, denomination));
        dealerCards.add(new PlayingCard(secondSuit, secondDenomination));
        Player dealer = new Dealer("딜러", new PlayingCards(dealerCards));

        assertThat(Match.findWinner(guest, dealer)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE:CLUB:HEART:ACE:JACK:TEN:WIN", "SPADE:CLUB:HEART:ACE:KING:NINE:WIN"}, delimiter = ':')
    @DisplayName("플레이어와 딜러 승무패 확인: 딜러보다 점수가 적지만, 딜러가 bust인 경우")
    public void checkWinnerGuest(Suit suit, Suit secondSuit, Suit thirdSuit, Denomination denomination,
                                 Denomination secondDenomination, Denomination thirdDenomination, Match result) {
        Set<PlayingCard> guestCards = new HashSet<>();
        guestCards.add(new PlayingCard(suit, denomination));
        guestCards.add(new PlayingCard(secondSuit, secondDenomination));
        Player guest = new Guest("guest", new PlayingCards(guestCards));

        Set<PlayingCard> dealerCards = new HashSet<>();
        dealerCards.add(new PlayingCard(suit, thirdDenomination));
        dealerCards.add(new PlayingCard(secondSuit, thirdDenomination));
        dealerCards.add(new PlayingCard(thirdSuit, thirdDenomination));
        Player dealer = new Dealer("딜러", new PlayingCards(dealerCards));

        assertThat(Match.findWinner(guest, dealer)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE:CLUB:HEART:ACE:JACK:TEN:LOSE", "SPADE:CLUB:HEART:ACE:QUEEN:EIGHT:LOSE"}, delimiter = ':')
    @DisplayName("플레이어와 딜러 승무패 확인: 게스트보다 점수가 적지만, 게스트가 bust인 경우")
    public void checkWinnerDealer(Suit suit, Suit secondSuit, Suit thirdSuit, Denomination denomination,
                                  Denomination secondDenomination, Denomination thirdDenomination, Match result) {
        Set<PlayingCard> guestCards = new HashSet<>();
        guestCards.add(new PlayingCard(suit, thirdDenomination));
        guestCards.add(new PlayingCard(secondSuit, thirdDenomination));
        guestCards.add(new PlayingCard(thirdSuit, thirdDenomination));
        Player guest = new Guest("guest", new PlayingCards(guestCards));

        Set<PlayingCard> dealerCards = new HashSet<>();
        dealerCards.add(new PlayingCard(suit, denomination));
        dealerCards.add(new PlayingCard(secondSuit, secondDenomination));
        Player dealer = new Dealer("딜러", new PlayingCards(dealerCards));

        assertThat(Match.findWinner(guest, dealer)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"WIN:LOSE", "DRAW:DRAW", "LOSE:WIN"}, delimiter = ':')
    @DisplayName("딜러 승무패 결정 로직 확인")
    public void checkDealerFindWinner(Match guest, Match dealer) {
        assertThat(guest.getDealerResult()).isEqualTo(dealer);
    }
}
