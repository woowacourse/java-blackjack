package blackjack.domain;

import java.util.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.*;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackGameTest {

    @Test
    @DisplayName("게임 계산 결과: 딜러가 이긴 경우 확인")
    void isDealerBlackjack() {
        List<Player> players = new ArrayList<>();
        Set<PlayingCard> guestCards = new HashSet<>();
        guestCards.add(new PlayingCard(Suit.SPADE, Denomination.EIGHT));
        guestCards.add(new PlayingCard(Suit.SPADE, Denomination.SEVEN));
        Guest guest = new Guest("guest", new PlayingCards(guestCards));
        players.add(guest);

        Set<PlayingCard> dealerCards = new HashSet<>();
        dealerCards.add(new PlayingCard(Suit.DIAMOND, Denomination.ACE));
        dealerCards.add(new PlayingCard(Suit.DIAMOND, Denomination.TEN));
        Dealer dealer = new Dealer("딜러", new PlayingCards(dealerCards));
        players.add(dealer);

        Players playerList = new Players(players);
        BlackjackGame blackjackGame = BlackjackGame.of(Deck.create(), new ArrayList<>());
        //Results results = blackjackGame.calculateResult(playerList);
        //MatchResult guestResult = results.getResult(guest);
        //MatchResult dealerResult = results.getResult(dealer);

        //assertThat(guestResult.getMatch().get(Match.LOSE)).isEqualTo(dealerResult.getMatch().get(Match.WIN));
    }

    @Test
    @DisplayName("게임 계산 결과: 플레이어가 이긴 경우 확인")
    void isPlayerBlackjack() {
        List<Player> players = new ArrayList<>();
        Set<PlayingCard> guestCards = new HashSet<>();
        guestCards.add(new PlayingCard(Suit.SPADE, Denomination.ACE));
        guestCards.add(new PlayingCard(Suit.SPADE, Denomination.TEN));
        Guest guest = new Guest("guest", new PlayingCards(guestCards));
        players.add(guest);

        Set<PlayingCard> dealerCards = new HashSet<>();
        dealerCards.add(new PlayingCard(Suit.DIAMOND, Denomination.ACE));
        dealerCards.add(new PlayingCard(Suit.DIAMOND, Denomination.THREE));
        dealerCards.add(new PlayingCard(Suit.DIAMOND, Denomination.SEVEN));
        Dealer dealer = new Dealer("딜러", new PlayingCards(dealerCards));
        players.add(dealer);

        Players playerList = new Players(players);
        BlackjackGame blackjackGame = BlackjackGame.of(Deck.create(), new ArrayList<>());
        //Results results = blackjackGame.calculateResult(playerList);
        //MatchResult guestResult = results.getResult(guest);
        //MatchResult dealerResult = results.getResult(dealer);

        //assertThat(guestResult.getMatch().get(Match.WIN)).isEqualTo(dealerResult.getMatch().get(Match.LOSE));
    }

    @Test
    @DisplayName("카드가 할당되는지 확인")
    void pickCard() {
        PlayingCardFixMachine playingCardFixMachine = new PlayingCardFixMachine();
        Guest guest = new Guest("guest", new PlayingCards());
        BlackjackGame blackjackGame = BlackjackGame.of(Deck.create(), new ArrayList<>());
        blackjackGame.assignCard(guest, playingCardFixMachine);
        blackjackGame.assignCard(guest, playingCardFixMachine);

        assertThat(guest.getPlayingCards().getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("다음 순서가 존재하지 않는 경우: 딜러만 있는 경우")
    void checkNonExistNextTurn() {
        List<String> players = new ArrayList<>();
        CardShuffleMachine playingCardShuffleMachine = new PlayingCardShuffleMachine();
        BlackjackGame blackjackGame = BlackjackGame.of(Deck.create(), players);
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
        BlackjackGame blackjackGame = BlackjackGame.of(Deck.create(), players);
        blackjackGame.initGames(playingCardShuffleMachine);
        blackjackGame.nextTurn();

        assertThat(blackjackGame.isExistNextPlayer()).isTrue();
    }

    @Test
    @DisplayName("플레이어 턴이 맞는지 확인")
    void checkRightPlayerTurn() {
        List<String> players = new ArrayList<>();
        players.add("green");
        PlayingCardFixMachine playingCardFixMachine = new PlayingCardFixMachine();
        BlackjackGame blackjackGame = BlackjackGame.of(Deck.create(), players);
        blackjackGame.initGames(playingCardFixMachine);
        blackjackGame.nextTurn();

        assertThat(blackjackGame.isTurnGuest()).isTrue();
    }

    @Test
    @DisplayName("딜러 턴이 가능한지 확인")
    void checkPossibleDealerTurn() {
        List<String> players = new ArrayList<>();
        CardShuffleMachine playingCardFixMachine = new PlayingCardFixMachine();
        BlackjackGame blackjackGame = BlackjackGame.of(Deck.create(), players);
        blackjackGame.initGames(playingCardFixMachine);

        assertThat(blackjackGame.isTurnDealer()).isFalse();
    }
}
