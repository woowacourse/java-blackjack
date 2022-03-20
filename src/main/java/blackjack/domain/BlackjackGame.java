package blackjack.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import blackjack.domain.card.CardShuffleMachine;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.GameResponse;
import blackjack.domain.result.Profits;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;

public class BlackjackGame {

    private final Deck deck;
    private final Players blackjackPlayers;

    private BlackjackGame(Deck deck, Players players) {
        this.deck = deck;
        this.blackjackPlayers = players;
    }

    public static BlackjackGame of(Deck deck, HashMap<String, Integer> playersBetMoney) {
        return new BlackjackGame(deck, initPlayers(playersBetMoney));
    }

    private static Players initPlayers(HashMap<String, Integer> playersBetMoney) {
        List<Player> players = new ArrayList<>();
        players.add(new Dealer());
        for (String playerName : playersBetMoney.keySet()) {
            players.add(new Guest(playerName, new Ready(), playersBetMoney.get(playerName)));
        }
        return new Players(players);
    }

    public void initGames(CardShuffleMachine playingCardShuffleMachine) {
        deck.shuffle(playingCardShuffleMachine);
        initCards();
    }

    private void initCards() {
        for (Player blackjackPlayer : blackjackPlayers.getPlayers()) {
            blackjackPlayer.getState().draw(deck.assignCard());

            State state = blackjackPlayer.getState().draw(deck.assignCard());
            blackjackPlayer.changeState(state);
        }
    }

    public void assignCard(Player player) {
        player.changeState(player.getState().draw(deck.assignCard()));
    }

    public Profits calculateProfits() {
        return Profits.of(blackjackPlayers);
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
        return player.isHit();
    }

    public boolean isTurnDealer() {
        return this.getDealer().isHit();
    }

    public Player getTurnPlayer() {
        return blackjackPlayers.turnPlayer();
    }

    public State getTurnPlayerState() {
        return blackjackPlayers.turnPlayer().getState();
    }

    public Player getDealer() {
        return blackjackPlayers.getDealer();
    }

    public Players getPlayers() {
        return blackjackPlayers;
    }

    public List<GameResponse> getPlayersGameResponses() {
        List<GameResponse> gameResponses = new ArrayList<>();
        for (Player player : blackjackPlayers.getPlayers()) {
            gameResponses.add(new GameResponse(player.getName(), player.getState().playingCards()));
        }
        return gameResponses;
    }

    public List<GameResponse> getTurnPlayerGameResponse() {
        List<GameResponse> gameResponses = new ArrayList<>();
        GameResponse gameResponse = new GameResponse(blackjackPlayers.turnPlayer().getName(),
                blackjackPlayers.turnPlayer().getState().playingCards());
        gameResponses.add(gameResponse);
        return gameResponses;
    }
}
