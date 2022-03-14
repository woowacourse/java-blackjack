package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Symbol;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.Match;
import blackjack.domain.result.MatchResult;
import blackjack.domain.result.Results;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackGameTest {

    Player guest;
    Player dealer;

    @BeforeEach
    void setUp() {
        guest = new Guest("haha");
        dealer = new Dealer();
    }

    @Test
    @DisplayName("딜러가 블랙잭인 경우 확인")
    void isDealerBlackjack() {
        Players players = new Players();
        guest.addCard(new Card(Suit.SPADE, Symbol.EIGHT));
        guest.addCard(new Card(Suit.SPADE, Symbol.SEVEN));
        players.addPlayer(guest);

        dealer.addCard(new Card(Suit.DIAMOND, Symbol.ACE));
        dealer.addCard(new Card(Suit.DIAMOND, Symbol.TEN));
        players.addPlayer(dealer);

        BlackjackGame blackjackGame = new BlackjackGame();
        Results results = blackjackGame.calculateResult(players);
        MatchResult guestResult = results.getResult(guest);
        MatchResult dealerResult = results.getResult(dealer);

        assertThat(guestResult.getMatch().get(Match.LOSE)).isEqualTo(dealerResult.getMatch().get(Match.BLACKJACK));
    }

    @Test
    @DisplayName("플레이어가 블랙잭인 경우 확인")
    void isPlayerBlackjack() {
        Players players = new Players();
        guest.addCard(new Card(Suit.SPADE, Symbol.ACE));
        guest.addCard(new Card(Suit.SPADE, Symbol.TEN));
        players.addPlayer(guest);

        dealer.addCard(new Card(Suit.DIAMOND, Symbol.ACE));
        dealer.addCard(new Card(Suit.DIAMOND, Symbol.THREE));
        dealer.addCard(new Card(Suit.DIAMOND, Symbol.SEVEN));
        players.addPlayer(dealer);

        BlackjackGame blackjackGame = new BlackjackGame();
        Results results = blackjackGame.calculateResult(players);
        MatchResult guestResult = results.getResult(guest);
        MatchResult dealerResult = results.getResult(dealer);

        assertThat(guestResult.getMatch().get(Match.BLACKJACK)).isEqualTo(dealerResult.getMatch().get(Match.LOSE));
    }
}