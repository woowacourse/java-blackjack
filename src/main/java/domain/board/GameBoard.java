package domain.board;

import domain.GameJudge;
import domain.GameResult;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameBoard {

    private final PlayerBoards playerBoards;
    private final DealerBoard dealerBoard;

    public GameBoard(PlayerBoards playerBoards, DealerBoard dealerBoard) {
        this.playerBoards = playerBoards;
        this.dealerBoard = dealerBoard;
    }

    public DealerBoard getDealerBoard() {
        return dealerBoard;
    }

    public Dealer getDealer() {
        return dealerBoard.getDealer();
    }

    public boolean dealerNeedMoreCard() {
        return dealerBoard.needMoreCard();
    }

    public PlayerBoard getCurrentTurnPlayerBoard() {
        Optional<PlayerBoard> currentTurn = playerBoards.getAllPlayerBoard()
            .stream()
            .filter(PlayerBoard::isWaiting)
            .findFirst();
        if (currentTurn.isPresent()) {
            return currentTurn.get();
        }
        throw new IllegalStateException("더 이상 진행할 게임이 없습니다.");
    }

    public List<Player> getPlayers() {
        return playerBoards.getPlayers();
    }

    public List<PlayerBoard> getPlayerBoards() {
        return playerBoards.getAllPlayerBoard();
    }

    public List<GameResult> getPlayersGameResult() {
        return playerBoards.getAllPlayerBoard().stream()
            .map(playerBoard -> GameJudge.getPlayerGameResult(dealerBoard, playerBoard))
            .collect(Collectors.toList());
    }

    public boolean isGameLeft() {
        return playerBoards.getAllPlayerBoard().stream().anyMatch(PlayerBoard::isWaiting);
    }
}
