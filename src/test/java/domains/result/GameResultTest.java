package domains.result;

import domains.card.Card;
import domains.card.Symbol;
import domains.card.Type;
import domains.user.Dealer;
import domains.user.Hands;
import domains.user.Player;
import domains.user.Players;
import domains.user.name.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {

    @DisplayName("참가자들과 딜러의 게임 결과를 계산하여 승패를 반환")
    @ParameterizedTest
    @MethodSource("gameData")
    void getWinOrLose_GivenPlayers_WinAndDraw(Players players, Dealer dealer) {
        GameResult gameResult = new GameResult(players, dealer);

        Iterator<Player> iterator = players.iterator();
        Player ddoring = iterator.next();
        Player smallBear = iterator.next();

        assertThat(gameResult.getPlayerGameResult(ddoring)).isEqualTo(ResultType.BLACKJACK);
        assertThat(gameResult.getPlayerGameResult(smallBear)).isEqualTo(ResultType.DRAW);
    }

    @DisplayName("플레이어들의 결과를 바탕으로 딜러의 결과를 생성 및 반환")
    @ParameterizedTest
    @MethodSource("gameData")
    void calculateDealerResult_PlayersResult_ReturnDealerResult(Players players, Dealer dealer, Map<ResultType, Integer> dealerResult) {
        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.calculateDealerResult()).isEqualTo(dealerResult);
    }

    static Stream<Arguments> gameData() {
        Card ace = new Card(Symbol.ACE, Type.CLUB);
        Card king = new Card(Symbol.KING, Type.HEART);
        Card four = new Card(Symbol.FOUR, Type.DIAMOND);

        Player ddoring = new Player(new PlayerName("또링"), "4000", new Hands(Arrays.asList(ace, king)));
        Player smallBear = new Player(new PlayerName("작은곰"), "4000", new Hands(Arrays.asList(ace, four)));
        Players players = new Players(new ArrayList<>(Arrays.asList(ddoring, smallBear)));

        Dealer dealer = new Dealer(new Hands(Arrays.asList(ace, four)));

        Map<ResultType, Integer> dealerResult = new HashMap<>();
        dealerResult.put(ResultType.WIN, 0);
        dealerResult.put(ResultType.DRAW, 1);
        dealerResult.put(ResultType.LOSE, 1);
        dealerResult.put(ResultType.BLACKJACK, 0);

        return Stream.of(
                Arguments.of(players, dealer, dealerResult)
        );
    }
}
