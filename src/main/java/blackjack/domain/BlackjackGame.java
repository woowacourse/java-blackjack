package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.CardShuffleMachine;
import blackjack.domain.card.PlayingCard;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.GameResponse;
import blackjack.domain.result.Results;

public class BlackjackGame {

    private final List<PlayingCard> playingCards;
    private final Players blackjackPlayers;

    public BlackjackGame(List<PlayingCard> playingCards, List<String> playerNames) {
        this.playingCards = playingCards;
        this.blackjackPlayers = initPlayers(playerNames);
    }

    public void initGames(CardShuffleMachine playingCardShuffleMachine) {
        initCards(playingCardShuffleMachine);
    }

    private Players initPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        players.add(new Dealer());
        for (String playerName : playerNames) {
            players.add(new Guest(playerName));
        }
        return new Players(players);
    }

    private void initCards(CardShuffleMachine playingCardShuffleMachine) {
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

    private boolean checkGetMoreCard(Player player) {
        return player.isCanHit();
    }

    public boolean turnDealer(CardShuffleMachine playingCardShuffleMachine) {
        Player dealer = blackjackPlayers.getDealer();
        if (dealer.isCanHit()) {
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
        results.competeDealerWithPlayers();
        return results;
    }

    public List<GameResponse> getGameResponse(Players players) {
        List<GameResponse> gameResponses = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            gameResponses.add(new GameResponse(player.getName(), player.getDeck()));
        }
        return gameResponses;
    }

    public Players getBlackjackPlayers() {
        return blackjackPlayers;
    }

    public Player getTurnPlayer() {
        return blackjackPlayers.turnPlayer();
    }
}
