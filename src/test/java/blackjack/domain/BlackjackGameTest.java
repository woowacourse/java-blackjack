package blackjack.domain;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.*;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.Match;
import blackjack.domain.result.MatchResult;
import blackjack.domain.result.Results;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackGameTest {

    @Test
    @DisplayName("게임 계산 결과: 딜러가 이긴 경우 확인")
    void isDealerBlackjack() {
        List<Player> players = new ArrayList<>();
        Guest guest = new Guest("guest", new PlayingCards());
        Dealer dealer = new Dealer();
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.EIGHT));
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.SEVEN));
        players.add(guest);

        dealer.addCard(new PlayingCard(Suit.DIAMOND, Denomination.ACE));
        dealer.addCard(new PlayingCard(Suit.DIAMOND, Denomination.TEN));
        players.add(dealer);

        Players playerList = new Players(players);
        BlackjackGame blackjackGame = new BlackjackGame(Deck.create(), new ArrayList<>());
        Results results = blackjackGame.calculateResult(playerList);
        MatchResult guestResult = results.getResult(guest);
        MatchResult dealerResult = results.getResult(dealer);

        assertThat(guestResult.getMatch().get(Match.LOSE)).isEqualTo(dealerResult.getMatch().get(Match.WIN));
    }

    @Test
    @DisplayName("게임 계산 결과: 플레이어가 이긴 경우 확인")
    void isPlayerBlackjack() {
        List<Player> players = new ArrayList<>();
        Guest guest = new Guest("guest", new PlayingCards());
        Dealer dealer = new Dealer();
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.ACE));
        guest.addCard(new PlayingCard(Suit.SPADE, Denomination.TEN));
        players.add(guest);

        dealer.addCard(new PlayingCard(Suit.DIAMOND, Denomination.ACE));
        dealer.addCard(new PlayingCard(Suit.DIAMOND, Denomination.THREE));
        dealer.addCard(new PlayingCard(Suit.DIAMOND, Denomination.SEVEN));
        players.add(dealer);

        Players playerList = new Players(players);
        BlackjackGame blackjackGame = new BlackjackGame(Deck.create(), new ArrayList<>());
        Results results = blackjackGame.calculateResult(playerList);
        MatchResult guestResult = results.getResult(guest);
        MatchResult dealerResult = results.getResult(dealer);

        assertThat(guestResult.getMatch().get(Match.WIN)).isEqualTo(dealerResult.getMatch().get(Match.LOSE));
    }

    @Test
    @DisplayName("카드가 뽑히는지 확인")
    void pickCard() {
        PlayingCardFixMachine playingCardFixMachine = new PlayingCardFixMachine();
        Guest guest = new Guest("guest", new PlayingCards());
        BlackjackGame blackjackGame = new BlackjackGame(Deck.create(), new ArrayList<>());
        blackjackGame.assignCard(guest, playingCardFixMachine);
        blackjackGame.assignCard(guest, playingCardFixMachine);

        Set<PlayingCard> cards = new LinkedHashSet<>();
        cards.add(new PlayingCard(Suit.SPADE, Denomination.ACE));
        cards.add(new PlayingCard(Suit.SPADE, Denomination.TWO));

        assertThat(guest.getPlayingCards().getCards()).isEqualTo(cards);
    }

    @Test
    @DisplayName("다음 순서가 존재하지 않는 경우: 딜러만 있는 경우")
    void checkNonExistNextTurn() {
        List<String> players = new ArrayList<>();

        CardShuffleMachine playingCardShuffleMachine = new PlayingCardShuffleMachine();
        BlackjackGame blackjackGame = new BlackjackGame(Deck.create(), players);
        blackjackGame.initGames(playingCardShuffleMachine);
        blackjackGame.nextTurn();

        assertThat(blackjackGame.isExistNextPlayer()).isFalse();
    }

    @Test
    @DisplayName("다음 순서가 존재하는 경우: 플레이어가 있는 경우")
    void checkExistNextTurn() {
        List<String> players = new ArrayList<>();
        players.add("green");
        CardShuffleMachine playingCardShuffleMachine = new PlayingCardShuffleMachine();
        BlackjackGame blackjackGame = new BlackjackGame(Deck.create(), players);
        blackjackGame.initGames(playingCardShuffleMachine);
        blackjackGame.nextTurn();

        assertThat(blackjackGame.isExistNextPlayer()).isTrue();
    }

    @Test
    @DisplayName("플레이어 턴이 맞는지 확인")
    void checkPlayerTurn() {
        List<String> players = new ArrayList<>();
        players.add("green");
        CardShuffleMachine playingCardShuffleMachine = new PlayingCardShuffleMachine();
        BlackjackGame blackjackGame = new BlackjackGame(Deck.create(), players);
        blackjackGame.initGames(playingCardShuffleMachine);
        blackjackGame.nextTurn();

        assertThat(blackjackGame.isTurnGuest()).isTrue();
    }

    @Test
    @DisplayName("딜러 턴이 가능한지 확인")
    void checkPossibleDealerTurn() {
        List<String> players = new ArrayList<>();
        CardShuffleMachine playingCardFixMachine = new PlayingCardFixMachine();
        BlackjackGame blackjackGame = new BlackjackGame(Deck.create(), players);
        blackjackGame.initGames(playingCardFixMachine);

        assertThat(blackjackGame.isTurnDealer()).isTrue();
    }
}