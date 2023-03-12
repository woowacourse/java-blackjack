package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.CloverCard;
import domain.card.HeartCard;
import domain.user.Dealer;
import domain.user.Name;
import domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

@DisplayName("게임 결과는 ")
class GameResultTest {
    @Test
    @DisplayName("계산되기 위해 GameResult 객체 생성을 할 수 있다.")
    void createTest() {
        assertDoesNotThrow(() -> new GameResult(List.of("pobi", "neo")));
    }

    @Test
    @DisplayName("전체 플레이어의 결과를 포함한다.")
    void judgePlayerResultsTest() {
        //given
        Dealer dealer = new Dealer(List.of(CloverCard.SIX, CloverCard.THREE));
        Player pobi = new Player(new Name("pobi"), List.of(CloverCard.FIVE, CloverCard.EIGHT));
        Player neo = new Player(new Name("neo"), List.of(HeartCard.TWO, HeartCard.THREE));
        List<Player> players = List.of(pobi, neo);
        GameResult gameResult = new GameResult(List.of("pobi", "neo"));
        gameResult.saveResults(dealer, players);

        //when
        Map<String, Boolean> playerResult = gameResult.getPlayerResults();

        //then
        assertThat(playerResult.get("pobi")).isTrue();
        assertThat(playerResult.get("neo")).isFalse();
    }

    @Test
    @DisplayName("딜러의 결과를 포함한다.")
    void judgeDealerResultsTest() {
        //given
        Dealer dealer = new Dealer(List.of(CloverCard.SIX, CloverCard.THREE));
        Player pobi = new Player(new Name("pobi"), List.of(CloverCard.FIVE, CloverCard.EIGHT));
        Player neo = new Player(new Name("neo"), List.of(HeartCard.TWO, HeartCard.THREE));
        List<Player> players = List.of(pobi, neo);
        GameResult gameResult = new GameResult(List.of("pobi", "neo"));
        gameResult.saveResults(dealer, players);

        //when
        Map<Boolean, Integer> dealerResult = gameResult.getDealerResult();

        //then
        assertThat(dealerResult.get(true)).isEqualTo(1);
        assertThat(dealerResult.get(false)).isEqualTo(1);
    }
}
