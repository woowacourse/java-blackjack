package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class BlackJackRuleTest {

    @ParameterizedTest
    @CsvSource(value = {"11, 11, TIE", "11, 12, LOSE", "11, 10, WIN", "22, 22, TIE", "22, 21, LOSE", "22, 23, TIE",
            "21, 21, TIE", "21, 22, WIN", "21, 20, WIN"})
    void 딜러와_플레이어의_점수를_통해_딜러의_결과를_계산할_수_있음(final int dealerScore, final int playerScore, final ResultType resultType) {
        final BlackJackRule blackJackRule = new BlackJackRule(dealerScore);
        final ResultType actual = blackJackRule.calculateDealerResult(playerScore);
        assertThat(actual).isEqualTo(resultType);
    }
}
