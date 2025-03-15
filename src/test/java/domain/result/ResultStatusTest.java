package domain.result;

import domain.card.Card;
import domain.card.Number;
import domain.card.Symbol;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게임 결과 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ResultStatusTest {

    @Test
    void 플레이어가_가진_숫자들의_합이_21을_초과하면_플레이어는_패배한다() {
        Player player = new Player("drago",
            List.of(new Card(Symbol.DIAMOND, domain.card.Number.KING),
                new Card(Symbol.CLOVER, domain.card.Number.JACK),
                new Card(Symbol.HEART, domain.card.Number.TWO)),
            1000);

        Dealer dealer = new Dealer(
            List.of(new Card(Symbol.DIAMOND, domain.card.Number.KING),
                new Card(Symbol.HEART, domain.card.Number.EIGHT),
                new Card(Symbol.SPADE, domain.card.Number.TWO)));


        assertThat(ResultStatus.judgeGameResult(player, dealer)).isEqualTo(ResultStatus.LOSE);
    }

    @Test
    void 플레이어가_가진_숫자들의_합이_21을_초과하지않고_딜러숫자의합이_21을_초과하면_플레이어는_승리한다() {
        Player player = new Player("drago",
            List.of(new Card(Symbol.DIAMOND, domain.card.Number.KING),
                new Card(Symbol.CLOVER, domain.card.Number.EIGHT),
                new Card(Symbol.HEART, domain.card.Number.TWO)),
            1000);

        Dealer dealer = new Dealer(
            List.of(new Card(Symbol.DIAMOND, domain.card.Number.KING),
                new Card(Symbol.HEART, domain.card.Number.JACK),
                new Card(Symbol.SPADE, domain.card.Number.TWO)));

        assertThat(ResultStatus.judgeGameResult(player, dealer)).isEqualTo(ResultStatus.WIN);
    }

    @Test
    void 플레이어와_딜러가_가진_숫자들의_합이_21을_초과하지않는경우_21에가까운_플레이어가_승리한다() {
        Player player = new Player("drago",
            List.of(new Card(Symbol.DIAMOND, domain.card.Number.KING),
                new Card(Symbol.CLOVER, domain.card.Number.EIGHT),
                new Card(Symbol.HEART, domain.card.Number.TWO)),
            1000);

        Dealer dealer = new Dealer(
            List.of(new Card(Symbol.DIAMOND, domain.card.Number.KING),
                new Card(Symbol.HEART, domain.card.Number.SEVEN),
                new Card(Symbol.SPADE, domain.card.Number.TWO)));

        assertThat(ResultStatus.judgeGameResult(player, dealer)).isEqualTo(ResultStatus.WIN);
    }

    @Test
    void 플레이어와_딜러가_가진_숫자들의_합이_21을_초과하지않고_동일하면_무승부이다() {
        Player player = new Player("drago",
            List.of(new Card(Symbol.DIAMOND, domain.card.Number.KING),
                new Card(Symbol.CLOVER, domain.card.Number.EIGHT),
                new Card(Symbol.HEART, domain.card.Number.TWO)),
            1000);

        Dealer dealer = new Dealer(
            List.of(new Card(Symbol.DIAMOND, domain.card.Number.KING),
                new Card(Symbol.HEART, domain.card.Number.EIGHT),
                new Card(Symbol.SPADE, Number.TWO)));

        assertThat(ResultStatus.judgeGameResult(player, dealer)).isEqualTo(ResultStatus.PUSH);
    }

    @ParameterizedTest
    @CsvSource({
        "WIN, 1000, 1000",
        "LOSE, 1000, -1000",
        "PUSH, 1000, 0",
        "BLACK_JACK, 1000, 1500"
    })
    void 상태에_따른_수익률을_계산한다(ResultStatus status, int betAmount, int income) {
        assertThat(status.calculateIncome(betAmount)).isEqualTo(income);
    }
}
