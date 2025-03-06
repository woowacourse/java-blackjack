package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게임 결과 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ResultStatusTest {

    @Test
    void 플레이어가_가진_숫자들의_합이_21을_초과하면_플레이어는_패배한다() {
        Player player = new Player(new Name("drago"), new Cards(
            List.of(new Card(Symbol.DIAMOND, Number.KING),
                new Card(Symbol.CLOVER, Number.JACK),
                new Card(Symbol.HEART, Number.TWO))));
        Players players = new Players(List.of(player));

        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(Symbol.DIAMOND, Number.KING),
                new Card(Symbol.HEART, Number.EIGHT),
                new Card(Symbol.SPADE, Number.TWO))));

        Map<String, ResultStatus> result = Map.of("drago", ResultStatus.LOSE);

        assertThat(ResultStatus.judgeGameResult(players, dealer)).isEqualTo(result);
    }

    @Test
    void 플레이어가_가진_숫자들의_합이_21을_초과하지않고_딜러숫자의합이_21을_초과하면_플레이어는_승리한다() {
        Player player = new Player(new Name("drago"), new Cards(
            List.of(new Card(Symbol.DIAMOND, Number.KING),
                new Card(Symbol.CLOVER, Number.EIGHT),
                new Card(Symbol.HEART, Number.TWO))));
        Players players = new Players(List.of(player));

        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(Symbol.DIAMOND, Number.KING),
                new Card(Symbol.HEART, Number.JACK),
                new Card(Symbol.SPADE, Number.TWO))));

        Map<String, ResultStatus> result = Map.of("drago", ResultStatus.WIN);

        assertThat(ResultStatus.judgeGameResult(players, dealer)).isEqualTo(result);
    }

    @Test
    void 플레이어와_딜러가_가진_숫자들의_합이_21을_초과하지않는경우_21에가까운_플레이어가_승리한다() {
        Player player = new Player(new Name("drago"), new Cards(
            List.of(new Card(Symbol.DIAMOND, Number.KING),
                new Card(Symbol.CLOVER, Number.EIGHT),
                new Card(Symbol.HEART, Number.TWO))));
        Players players = new Players(List.of(player));

        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(Symbol.DIAMOND, Number.KING),
                new Card(Symbol.HEART, Number.SEVEN),
                new Card(Symbol.SPADE, Number.TWO))));

        Map<String, ResultStatus> result = Map.of("drago", ResultStatus.WIN);

        assertThat(ResultStatus.judgeGameResult(players, dealer)).isEqualTo(result);
    }

    @Test
    void 플레이어와_딜러가_가진_숫자들의_합이_21을_초과하지않고_동일하면_무승부이다() {
        Player player = new Player(new Name("drago"), new Cards(
            List.of(new Card(Symbol.DIAMOND, Number.KING),
                new Card(Symbol.CLOVER, Number.EIGHT),
                new Card(Symbol.HEART, Number.TWO))));
        Players players = new Players(List.of(player));

        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(Symbol.DIAMOND, Number.KING),
                new Card(Symbol.HEART, Number.EIGHT),
                new Card(Symbol.SPADE, Number.TWO))));

        Map<String, ResultStatus> result = Map.of("drago", ResultStatus.PUSH);

        assertThat(ResultStatus.judgeGameResult(players, dealer)).isEqualTo(result);
    }

    @Test
    void 게임결과_초기맵을_반환한다() {
        Map<ResultStatus, Integer> initMap = ResultStatus.initMap();
        Map<ResultStatus, Integer> expected = Map.of(ResultStatus.WIN, 0, ResultStatus.LOSE, 0, ResultStatus.PUSH, 0);

        assertThat(initMap).isEqualTo(expected);
    }
}
