package result;

import card.Card;
import card.Symbol;
import card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import user.Dealer;
import user.Hands;
import user.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    @DisplayName("참가자들과 딜러의 게임 결과를 계산하여 승패를 반환")
    @ParameterizedTest
    @MethodSource("gameData")
    void getWinOrLose_GivenPlayers_WinAndDraw(List<Player> players, Dealer dealer) {
        GameResult gameResult = new GameResult(players, dealer);
        assertThat(gameResult.getWinOrLose(players.get(0))).isEqualTo(WinOrLose.WIN);
        assertThat(gameResult.getWinOrLose(players.get(1))).isEqualTo(WinOrLose.DRAW);
    }

    static Stream<Arguments> gameData() {
        Card ace = new Card(Symbol.ACE, Type.CLUB);
        Card king = new Card(Symbol.KING, Type.HEART);
        Card four = new Card(Symbol.FOUR, Type.DIAMOND);

        Dealer dealer = new Dealer(new Hands(Arrays.asList(ace, four)));

        Player ddoring = new Player("또링", new Hands(Arrays.asList(ace, king)));
        Player smallBear = new Player("작은곰", new Hands(Arrays.asList(ace, four)));
        List<Player> players = new ArrayList<>(Arrays.asList(ddoring, smallBear));

        return Stream.of(
                Arguments.of(players, dealer)
        );
    }


}
