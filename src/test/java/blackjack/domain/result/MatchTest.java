package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Denomination;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.state.Hit;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;

class MatchTest {

    @ParameterizedTest
    @CsvSource(value = {"SPADE:CLUB:ACE:JACK:KING:DRAW", "SPADE:CLUB:ACE:JACK:FIVE:WIN_BLACKJACK",
            "SPADE:CLUB:ACE:THREE:FIVE:LOSE"}, delimiter = ':')
    @DisplayName("승무패 결정 로직 확인")
    public void checkInitCardFindWinner(Suit suit, Suit secondSuit, Denomination denomination,
                                        Denomination secondDenomination, Denomination thirdDenomination, Match result) {
        Player guest = new Guest("guest", new Ready(), 100);
        guest.getState().draw(new PlayingCard(suit, denomination));
        State state = guest.getState().draw(new PlayingCard(secondSuit, secondDenomination));
        guest.changeState(state);
        if (state instanceof Hit) {
            guest.changeState(state.stay());
        }

        Player dealer = new Dealer();
        dealer.getState().draw(new PlayingCard(suit, denomination));
        dealer.getState().draw(new PlayingCard(secondSuit, thirdDenomination));
        dealer.changeState(dealer.getState().stay());

        assertThat(guest.getState().matchResult(dealer)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE:CLUB:ACE:JACK:FIVE:LOSE_BLACKJACK"}, delimiter = ':')
    @DisplayName("처음 2장의 카드 이후(플레이어는 블랙잭이 아닌 21, 딜러는 블랙잭인 경우): 승무패 결정 로직 확인")
    public void checkBlackjackDealer(Suit suit, Suit secondSuit, Denomination denomination,
                                     Denomination secondDenomination, Denomination thirdDenomination, Match result) {
        Player guest = new Guest("guest", new Ready(), 100);
        guest.getState().draw(new PlayingCard(suit, denomination));
        guest.changeState(guest.getState().draw(new PlayingCard(secondSuit, thirdDenomination)));
        guest.changeState(guest.getState().draw(new PlayingCard(secondSuit, thirdDenomination)));

        Player dealer = new Dealer();
        dealer.getState().draw(new PlayingCard(suit, denomination));
        dealer.changeState(dealer.getState().draw(new PlayingCard(secondSuit, secondDenomination)));

        assertThat(guest.getState().matchResult(dealer)).isEqualTo(result);
    }


    @ParameterizedTest
    @CsvSource(value = {"SPADE:CLUB:HEART:ACE:JACK:WIN", "SPADE:CLUB:HEART:ACE:KING:WIN"}, delimiter = ':')
    @DisplayName("플레이어와 딜러 승무패 확인: 딜러보다 점수가 적지만, 딜러가 bust인 경우")
    public void checkWinnerGuest(Suit suit, Suit secondSuit, Suit thirdSuit, Denomination denomination,
                                  Denomination thirdDenomination, Match result) {
        Player guest = new Guest("guest", new Ready(), 100);
        guest.getState().draw(new PlayingCard(suit, denomination));
        guest.changeState(guest.getState().draw(new PlayingCard(secondSuit, denomination)));
        guest.changeState(guest.getState().stay());

        Dealer dealer = new Dealer();
        dealer.getState().draw(new PlayingCard(suit, thirdDenomination));
        dealer.getState().draw(new PlayingCard(secondSuit, thirdDenomination));
        dealer.getState().draw(new PlayingCard(thirdSuit, thirdDenomination));

        assertThat(guest.getState().matchResult(dealer)).isEqualTo(result);
    }


    @ParameterizedTest
    @CsvSource(value = {"SPADE:CLUB:HEART:ACE:ACE:TEN:LOSE", "SPADE:CLUB:HEART:ACE:NINE:EIGHT:LOSE"}, delimiter = ':')
    @DisplayName("플레이어와 딜러 승무패 확인: 게스트보다 점수가 적지만, 게스트가 bust인 경우")
    public void checkWinnerDealer(Suit suit, Suit secondSuit, Suit thirdSuit, Denomination denomination,
                                  Denomination secondDenomination, Denomination thirdDenomination, Match result) {
        Player guest = new Guest("guest", new Ready(), 100);
        guest.getState().draw(new PlayingCard(suit, thirdDenomination));
        guest.changeState(guest.getState().draw(new PlayingCard(secondSuit, thirdDenomination)));
        guest.changeState(guest.getState().draw(new PlayingCard(thirdSuit, thirdDenomination)));

        Player dealer = new Dealer();
        dealer.getState().draw(new PlayingCard(suit, denomination));
        dealer.getState().draw(new PlayingCard(secondSuit, secondDenomination));

        assertThat(guest.getState().matchResult(dealer)).isEqualTo(result);
    }
}
