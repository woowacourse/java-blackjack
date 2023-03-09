package domain.board;

import domain.PlayerStatus;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameBoardTest {

    @DisplayName("플레이어 현황판들과 딜러현황판을 이용하여 게임현황판을 만들다.")
    @Test
    void createGameBoard() {
        PlayerBoards playerBoards = PlayerBoards.from(List.of("split", "echo"));
        Dealer dealer = new Dealer();
        DealerBoard dealerBoard = new DealerBoard(dealer, PlayerStatus.HIT_ABLE);
        GameBoard gameBoard = new GameBoard(playerBoards, dealerBoard);
        Assertions.assertThat(gameBoard.getPlayers()).containsAll(playerBoards.getPlayers());
        Assertions.assertThat(gameBoard.getDealer()).isEqualTo(dealerBoard.getDealer());
    }

    @DisplayName("현재 게임을 진행해야 하는 플레이어 현황판을 반환한다.")
    @Test
    void getCurrentTurnPlayerBoard() {
        PlayerBoards playerBoards = PlayerBoards.from(List.of("split", "echo"));
        GameBoard gameBoard = new GameBoard(playerBoards, new DealerBoard(new Dealer(), PlayerStatus.HIT_ABLE));
        Assertions.assertThat(gameBoard.getPlayers()).containsExactly(new Player("split"), new Player("echo"));
        PlayerBoard currentTurnPlayerBoard = gameBoard.getCurrentTurnPlayerBoard();
        Player currentTurnPlayer = currentTurnPlayerBoard.getPlayer();
        Assertions.assertThat(currentTurnPlayerBoard.getPlayer()).isEqualTo(currentTurnPlayer);
    }
}
