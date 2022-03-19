package blackjack.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import blackjack.domain.card.CardShuffleMachine;
import blackjack.domain.card.Deck;
import blackjack.domain.card.PlayingCards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.GameResponse;
import blackjack.domain.result.Profits;

public class BlackjackGame {

    private final Deck deck;
    private final Players blackjackPlayers;

    public BlackjackGame(Deck deck, Players players) {
        this.deck = deck;
        this.blackjackPlayers = players;
    }

    public static BlackjackGame of(Deck deck, List<String> playerNames, HashMap<String, Integer> playersBetMoney) {
        return new BlackjackGame(deck, initPlayers(playerNames, playersBetMoney));
    }

    private static Players initPlayers(List<String> playerNames, HashMap<String, Integer> playersBetMoney) {
        List<Player> players = new ArrayList<>();
        players.add(new Dealer());
        for (String playerName : playerNames) {
            players.add(new Guest(playerName, new PlayingCards(), playersBetMoney.get(playerName)));
        }
        return new Players(players);
    }

    public void initGames(CardShuffleMachine playingCardShuffleMachine) {
        initCards(playingCardShuffleMachine);
    }

    private void initCards(CardShuffleMachine playingCardShuffleMachine) {
        for (Player blackjackPlayer : blackjackPlayers.getPlayers()) {
            blackjackPlayer.addCard(deck.assignCard(playingCardShuffleMachine));
            blackjackPlayer.addCard(deck.assignCard(playingCardShuffleMachine));
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
        return player.isCanHit();
    }

    public boolean isTurnDealer() {
        Player dealer = this.getDealer();
        return dealer.isCanHit();
    }

    public void assignCard(Player player, CardShuffleMachine playingCardShuffleMachine) {
        player.addCard(deck.assignCard(playingCardShuffleMachine));
    }

    public Profits calculateProfits() {
        return new Profits(blackjackPlayers);
    }

    public List<GameResponse> getPlayersGameResponses() {
        List<GameResponse> gameResponses = new ArrayList<>();
        for (Player player : blackjackPlayers.getPlayers()) {
            gameResponses.add(new GameResponse(player.getName(), player.getPlayingCards()));
        }
        return gameResponses;
    }

    public List<GameResponse> getTurnPlayerGameResponse() {
        List<GameResponse> gameResponses = new ArrayList<>();
        GameResponse gameResponse = new GameResponse(blackjackPlayers.turnPlayer().getName(),
                blackjackPlayers.turnPlayer().getPlayingCards());
        gameResponses.add(gameResponse);
        return gameResponses;
    }

    public Players getPlayers() {
        return blackjackPlayers;
    }

    public Player getTurnPlayer() {
        return blackjackPlayers.turnPlayer();
    }

    public Player getDealer() {
        return blackjackPlayers.getDealer();
    }
}
