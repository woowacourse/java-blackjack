package domain;

import domain.board.PlayerBoard;
import domain.board.PlayerBoards;
import domain.card.Card;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @DisplayName("게임 시작을 위해 딜러와 모든 플레이어에게 카드를 두장씩 지급한다.")
    @Test
    void initializeHand() {
        PlayerBoards playerBoards = PlayerBoards.from(List.of("split", "echo"));
        BlackJackGame blackJackGame = BlackJackGame.from(playerBoards);
        blackJackGame.initializeHand();
        List<List<Card>> playersInitialHand = blackJackGame.getPlayersInitialHand();
        Assertions.assertThat(blackJackGame.getDealer().getHand()).hasSize(2);
        Assertions.assertThat(playersInitialHand).hasSize(2);
        for (List<Card> cards : playersInitialHand) {
            Assertions.assertThat(cards).hasSize(2);
        }
    }

    @DisplayName("게임의 한 턴을 진행한다.")
    @Nested
    class playTurn {

        @Test
        @DisplayName("플레이어가 카드를 받음")
        void playTurnHit() {
            PlayerBoards playerBoards = PlayerBoards.from(List.of("split"));
            BlackJackGame blackJackGame = BlackJackGame.from(playerBoards);
            PlayerBoard currentPlayerBoard = blackJackGame.getCurrentPlayerBoard();
            blackJackGame.playerPlay(TurnAction.HIT);
            Assertions.assertThat(currentPlayerBoard.getPlayer().getHand()).hasSize(1);
        }

        @Test
        @DisplayName("플레이어가 카드를 받지 않음")
        void playTurnStand() {
            PlayerBoards playerBoards = PlayerBoards.from(List.of("split"));
            BlackJackGame blackJackGame = BlackJackGame.from(playerBoards);
            PlayerBoard currentPlayerBoard = blackJackGame.getCurrentPlayerBoard();
            blackJackGame.playerPlay(TurnAction.STAND);
            Assertions.assertThat(currentPlayerBoard.getPlayer().getHand()).hasSize(0);
        }
    }
}
