package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Symbol;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;

class MatchTest {

    @ParameterizedTest
    @CsvSource(value = {"SPADE:CLUB:ACE:JACK:BLACKJACK", "CLUB:SPADE:ACE:ACE:DRAW"}
            , delimiter = ':')
    @DisplayName("게스트 승무패 결정 로직 확인")
    public void checkGuestFindWinner(Suit suit, Suit secondSuit, Symbol symbol, Symbol secondSymbol, Match result) {
        Player guest = new Guest("green");
        guest.addCard(new Card(suit, symbol));
        guest.addCard(new Card(secondSuit, secondSymbol));

        Player dealer = new Dealer();
        dealer.addCard(new Card(suit, secondSymbol));
        dealer.addCard(new Card(secondSuit, secondSymbol));

        assertThat(Match.findWinner(guest, dealer)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"WIN:LOSE", "DRAW:DRAW"}, delimiter = ':')
    @DisplayName("딜러 승무패 결정 로직 확인")
    public void checkDealerFindWinner(Match guest, Match dealer) {
        assertThat(guest.getDealerResult()).isEqualTo(dealer);
    }
}
