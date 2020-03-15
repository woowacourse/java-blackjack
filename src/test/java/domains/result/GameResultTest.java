package domains.result;

import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    private GameResult gameResult;

    @BeforeEach
    void setUp() {
        gameResult = new GameResult();
    }

    @DisplayName("참가자들과 딜러의 게임 결과를 계산하여 승패를 반환")
    @ParameterizedTest
    @MethodSource("gameData")
    void getWinOrLose_GivenPlayers_WinAndDraw(Players players, Dealer dealer) {
        gameResult.create(players, dealer);

        Iterator<Player> iterator = players.iterator();
        Player ddoring = iterator.next();
        Player smallBear = iterator.next();

        assertThat(gameResult.getWinOrLose(ddoring)).isEqualTo(WinOrDrawOrLose.WIN);
        assertThat(gameResult.getWinOrLose(smallBear)).isEqualTo(WinOrDrawOrLose.DRAW);
    }
}
