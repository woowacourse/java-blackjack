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
public class BettingZoneTest {

    @ParameterizedTest
    @CsvSource({"BLACKJACK, 15000", "LOSE, -10000", "PUSH, 0", "WIN, 10000"})
    void 겜블러의_수익을_계산한다(final Result result, final int expectedProfit) {
        final BettingZone bettingZone = generateBettingZone();
        final Map<Player, Result> resultByPlayers = generateResultByPlayers(result);

        final Map<Player, Money> profitByPlayers = bettingZone.calculateProfitByPlayers(resultByPlayers);

        assertThat(profitByPlayers.values())
                .extracting(Money::getAmount)
                .containsExactly(expectedProfit);
    }

    private BettingZone generateBettingZone() {
        return new BettingZone(Map.of(new Gambler("후추"), Money.createMoneyForBetting(10000)));
    }

    private Map<Player, Result> generateResultByPlayers(final Result result) {
        return Map.of(new Gambler("후추"), result);
    }
}
