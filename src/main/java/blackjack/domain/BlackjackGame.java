package blackjack.domain;

import java.util.List;

import blackjack.domain.card.CardShuffleMachine;
import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCardShuffleMachine;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.Results;

public class BlackjackGame {

    private final List<PlayingCard> playingCards;
    private Players blackjackPlayers;

    public BlackjackGame() {
        this.playingCards = Deck.getPlayingCards();
    }

    public void initGames(List<String> playerNames, PlayingCardShuffleMachine playingCardShuffleMachine) {
        initPlayers(playerNames);
        initCards(playingCardShuffleMachine);
    }

    private void initPlayers(List<String> playerNames) {
        blackjackPlayers = new Players();
        blackjackPlayers.addPlayer(new Dealer());
        for (String playerName : playerNames) {
            blackjackPlayers.addPlayer(new Guest(playerName));
        }
    }

    private void initCards(PlayingCardShuffleMachine playingCardShuffleMachine) {
        for (Player blackjackPlayer : blackjackPlayers.getPlayers()) {
            blackjackPlayer.addCard(playingCardShuffleMachine.assignCard(playingCards));
            blackjackPlayer.addCard(playingCardShuffleMachine.assignCard(playingCards));
        }
    }

    public boolean isExistNextPlayer() {
        return blackjackPlayers.hasNextTurn();
    }

    public void nextTurn() {
        blackjackPlayers.nextTurn();
    }

    public boolean isTurnGuest() {
        Player player = blackjackPlayers.turnPlayer();
        if (player.isDealer()) {
            return false;
        }
        return checkGetMoreCard(player);
    }

    public boolean checkGetMoreCard(Player player) {
        return !player.isOverMoreCardLimit();
    }

    public boolean turnDealer(CardShuffleMachine playingCardShuffleMachine) {
        Player dealer = blackjackPlayers.getDealer();
        if (!dealer.isOverMoreCardLimit()) {
            addCard(dealer, playingCardShuffleMachine);
            return true;
        }
        return false;
    }

    public void addCard(Player player, CardShuffleMachine playingCardShuffleMachine) {
        player.addCard(playingCardShuffleMachine.assignCard(playingCards));
    }

    public Results calculateResult(Players players) {
        Results results = new Results(players);
        results.calculate();
        return results;
    }

    public Players getBlackjackPlayers() {
        return blackjackPlayers;
    }

    public Player getTurnPlayer() {
        return blackjackPlayers.turnPlayer();
    }
}
