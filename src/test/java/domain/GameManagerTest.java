package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게임 매니저 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GameManagerTest {

    @Test
    void 플레이어가_가진_숫자들의_합이_21을_초과하면_플레이어는_패배한다() {
        Player player = new Player("드라고", new Cards(
                List.of(new Card(Symbol.DIAMOND, Number.KING),
                        new Card(Symbol.CLOVER, Number.JACK),
                        new Card(Symbol.HEART, Number.TWO))));
        Players players = new Players(List.of(player));

        Dealer dealer = new Dealer(new Cards(
                List.of(new Card(Symbol.DIAMOND, Number.KING),
                        new Card(Symbol.HEART, Number.EIGHT),
                        new Card(Symbol.SPADE, Number.TWO))));

        GameManager gameManager = new GameManager(players, dealer);
        Map<String, ResultStatus> result = Map.of("드라고", ResultStatus.LOSE);

        assertThat(gameManager.judgeGameResult()).isEqualTo(result);
    }

    @Test
    void 플레이어가_가진_숫자들의_합이_21을_초과하지않고_딜러숫자의합이_21을_초과하면_플레이어는_승리한다() {
        Player player = new Player("드라고", new Cards(
                List.of(new Card(Symbol.DIAMOND, Number.KING),
                        new Card(Symbol.CLOVER, Number.EIGHT),
                        new Card(Symbol.HEART, Number.TWO))));
        Players players = new Players(List.of(player));

        Dealer dealer = new Dealer(new Cards(
                List.of(new Card(Symbol.DIAMOND, Number.KING),
                        new Card(Symbol.HEART, Number.JACK),
                        new Card(Symbol.SPADE, Number.TWO))));

        GameManager gameManager = new GameManager(players, dealer);
        Map<String, ResultStatus> result = Map.of("드라고", ResultStatus.WIN);

        assertThat(gameManager.judgeGameResult()).isEqualTo(result);
    }

    @Test
    void 플레이어와_딜러가_가진_숫자들의_합이_21을_초과하지않는경우_21에가까운_플레이어가_승리한다() {
        Player player = new Player("드라고", new Cards(
                List.of(new Card(Symbol.DIAMOND, Number.KING),
                        new Card(Symbol.CLOVER, Number.EIGHT),
                        new Card(Symbol.HEART, Number.TWO))));
        Players players = new Players(List.of(player));

        Dealer dealer = new Dealer(new Cards(
                List.of(new Card(Symbol.DIAMOND, Number.KING),
                        new Card(Symbol.HEART, Number.SEVEN),
                        new Card(Symbol.SPADE, Number.TWO))));

        GameManager gameManager = new GameManager(players, dealer);
        Map<String, ResultStatus> result = Map.of("드라고", ResultStatus.WIN);

        assertThat(gameManager.judgeGameResult()).isEqualTo(result);
    }

    @Test
    void 플레이어와_딜러가_가진_숫자들의_합이_21을_초과하지않고_동일하면_무승부이다() {
        Player player = new Player("드라고", new Cards(
                List.of(new Card(Symbol.DIAMOND, Number.KING),
                        new Card(Symbol.CLOVER, Number.EIGHT),
                        new Card(Symbol.HEART, Number.TWO))));
        Players players = new Players(List.of(player));

        Dealer dealer = new Dealer(new Cards(
                List.of(new Card(Symbol.DIAMOND, Number.KING),
                        new Card(Symbol.HEART, Number.EIGHT),
                        new Card(Symbol.SPADE, Number.TWO))));

        GameManager gameManager = new GameManager(players, dealer);
        Map<String, ResultStatus> result = Map.of("드라고", ResultStatus.PUSH);

        assertThat(gameManager.judgeGameResult()).isEqualTo(result);
    }
}
