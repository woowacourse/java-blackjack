package domain;

import domain.board.DealerBoard;
import domain.board.GameBoard;
import domain.board.PlayerBoard;
import domain.board.PlayerBoards;
import domain.card.Card;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerStatus;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final Deck deck = new Deck();
    private final GameBoard gameBoard;

    private BlackJackGame(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public static BlackJackGame from(PlayerBoards playerBoards) {
        DealerBoard dealerBoard = new DealerBoard(new Dealer(), PlayerStatus.HIT_ABLE);
        return new BlackJackGame(new GameBoard(playerBoards, dealerBoard));
    }

    public void initializeHand() {
        deck.shuffle();
        DealerBoard dealerBoard = gameBoard.getDealerBoard();
        dealTo(dealerBoard.getDealer());
        dealTo(dealerBoard.getDealer());
        dealerBoard.update();
        gameBoard.getPlayerBoards().forEach((playerBoard -> {
            dealTo(playerBoard.getPlayer());
            dealTo(playerBoard.getPlayer());
            playerBoard.update(TurnAction.HIT);
        }));
    }

    private void dealTo(Player player) {
        player.dealt(deck.draw());
    }

    public void playerPlay(TurnAction turnAction) {
        PlayerBoard currentPlayerBoard = getCurrentPlayerBoard();
        if (turnAction == TurnAction.HIT) {
            dealTo(currentPlayerBoard.getPlayer());
        }
        currentPlayerBoard.update(turnAction);
    }

    public PlayerBoard getCurrentPlayerBoard() {
        return gameBoard.getCurrentTurnPlayerBoard();
    }

    public boolean dealerNeedMoreCard() {
        return gameBoard.dealerNeedMoreCard();
    }

    public List<String> getPlayerNames() {
        return gameBoard.getPlayers().stream().map(Player::getName).collect(Collectors.toList());
    }

    public List<List<Card>> getPlayersInitialHand() {
        return gameBoard.getPlayers().stream().map(Player::getInitialHand).collect(Collectors.toList());
    }

    public String getDealerName() {
        return gameBoard.getDealer().getName();
    }

    public List<Card> getDealerInitialHand() {
        return gameBoard.getDealer().getInitialHand();
    }

    public Dealer getDealer() {
        return gameBoard.getDealer();
    }

    public boolean isGameLeft() {
        return gameBoard.isGameLeft();
    }

    public void dealerPlay() {
        dealTo(gameBoard.getDealer());
        gameBoard.getDealerBoard().update();
    }

    public List<GameResult> getPlayersGameResult() {
        return gameBoard.getPlayersGameResult();
    }

    public List<Player> getPlayers() {
        return gameBoard.getPlayers();
    }
}
