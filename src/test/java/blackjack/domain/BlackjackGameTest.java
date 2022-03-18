package blackjack.domain;

import java.util.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.*;
import blackjack.domain.player.Guest;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackGameTest {

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
