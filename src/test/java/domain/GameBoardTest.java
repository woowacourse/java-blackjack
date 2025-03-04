package domain;

import domain.card.CardSet;
import domain.participant.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameBoardTest {

    @Test
    void 게임판_테스트() {
        //given
        Player player1 = new Player("우가");
        Player player2 = new Player("히스타");
        CardSet gameCardSet = CardSet.generateFullSet();


        //when
        GameBoard gameBoard = new GameBoard(gameCardSet, List.of(player1, player2));

        //then
        Assertions.assertThat(gameBoard).isInstanceOf(GameBoard.class);
    }
}
