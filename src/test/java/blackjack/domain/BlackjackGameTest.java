package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        guest.addCard(new Card(Suit.SPADE, Symbols.EIGHT));
        guest.addCard(new Card(Suit.SPADE, Symbols.SEVEN));
        players.addPlayer(guest);

        dealer.addCard(new Card(Suit.DIAMOND, Symbols.ACE));
        dealer.addCard(new Card(Suit.DIAMOND, Symbols.TEN));
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
        guest.addCard(new Card(Suit.SPADE, Symbols.ACE));
        guest.addCard(new Card(Suit.SPADE, Symbols.TEN));
        players.addPlayer(guest);

        dealer.addCard(new Card(Suit.DIAMOND, Symbols.ACE));
        dealer.addCard(new Card(Suit.DIAMOND, Symbols.THREE));
        dealer.addCard(new Card(Suit.DIAMOND, Symbols.SEVEN));
        players.addPlayer(dealer);

        BlackjackGame blackjackGame = new BlackjackGame();
        Results results = blackjackGame.calculateResult(players);
        MatchResult guestResult = results.getResult(guest);
        MatchResult dealerResult = results.getResult(dealer);

        assertThat(guestResult.getMatch().get(Match.BLACKJACK)).isEqualTo(dealerResult.getMatch().get(Match.LOSE));
    }
}