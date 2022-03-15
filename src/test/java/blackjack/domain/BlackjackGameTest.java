package blackjack.domain;

import blackjack.domain.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.Match;
import blackjack.domain.result.MatchResult;
import blackjack.domain.result.Results;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackGameTest {

    BlackjackGame blackjackGame;
    Player guest;
    Player dealer;

    @BeforeEach
    void setUp() {
        blackjackGame = new BlackjackGame();
        guest = new Guest("haha");
        dealer = new Dealer();
    }

    @Test
    @DisplayName("딜러가 블랙잭인 경우 확인")
    void isDealerBlackjack() {
        Players players = new Players();
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.EIGHT));
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.SEVEN));
        players.addPlayer(guest);

        dealer.addCard(new PlayingCard(Suit.DIAMOND, Denomination.ACE));
        dealer.addCard(new PlayingCard(Suit.DIAMOND, Denomination.TEN));
        players.addPlayer(dealer);

        Results results = blackjackGame.calculateResult(players);
        MatchResult guestResult = results.getResult(guest);
        MatchResult dealerResult = results.getResult(dealer);

        assertThat(guestResult.getMatch().get(Match.LOSE)).isEqualTo(dealerResult.getMatch().get(Match.BLACKJACK));
    }

    @Test
    @DisplayName("플레이어가 블랙잭인 경우 확인")
    void isPlayerBlackjack() {
        Players players = new Players();
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.ACE));
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.TEN));
        players.addPlayer(guest);

        dealer.addCard(new PlayingCard(Suit.DIAMOND, Denomination.ACE));
        dealer.addCard(new PlayingCard(Suit.DIAMOND, Denomination.THREE));
        dealer.addCard(new PlayingCard(Suit.DIAMOND, Denomination.SEVEN));
        players.addPlayer(dealer);

        Results results = blackjackGame.calculateResult(players);
        MatchResult guestResult = results.getResult(guest);
        MatchResult dealerResult = results.getResult(dealer);

        assertThat(guestResult.getMatch().get(Match.BLACKJACK)).isEqualTo(dealerResult.getMatch().get(Match.LOSE));
    }

    @Test
    @DisplayName("카드 뽑기 확인")
    void pickCard() {
        PlayingCardFixMachine playingCardFixMachine = new PlayingCardFixMachine();
        blackjackGame.addCard(guest, playingCardFixMachine);
        blackjackGame.addCard(guest, playingCardFixMachine);

        Set<PlayingCard> cards = new LinkedHashSet<>();
        cards.add(new PlayingCard(Suit.SPADE, Denomination.ACE));
        cards.add(new PlayingCard(Suit.SPADE, Denomination.TWO));

        assertThat(guest.getDeck().getCards()).isEqualTo(cards);
    }
}