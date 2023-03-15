package blackjack.domain.game;

import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.Result;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BettingProfitOfficeTest {

    @ParameterizedTest
    @CsvSource({"BLACKJACK, 15000", "LOSE, -10000", "PUSH, 0", "WIN, 10000"})
    void 겜블러의_수익을_계산한다(final Result result, final int expectedProfit) {
        final BettingProfitOffice bettingProfitOffice = generateBettingProfit();
        final Map<Player, Result> resultByPlayers = generateResultByPlayers(result);

        final Map<Player, Money> profitByPlayers = bettingProfitOffice.calculateProfitByPlayers(resultByPlayers);

        assertThat(profitByPlayers.values())
                .extracting(Money::getAmount)
                .containsExactly(expectedProfit);
    }

    private BettingProfitOffice generateBettingProfit() {
        return new BettingProfitOffice(Map.of(new Gambler("후추"), Money.createMoneyForBetting(10000)));
    }

    private Map<Player, Result> generateResultByPlayers(final Result result) {
        return Map.of(new Gambler("후추"), result);
    }
}
