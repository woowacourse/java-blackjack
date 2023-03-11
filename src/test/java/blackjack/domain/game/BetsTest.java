package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.Name;
import blackjack.domain.player.Result;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BetsTest {

    @Test
    void 베팅_결과를_계산한다() {
        final Map<Name, Money> initialBets = new LinkedHashMap<>();
        initialBets.put(Name.from("허브"), Money.initialBet(1000));
        initialBets.put(Name.from("후추"), Money.initialBet(100));
        final Bets bets = new Bets();
        bets.addBets(initialBets);

        bets.calculateProfit(Map.of(
                Name.from("허브"), Result.BLACKJACK_WIN,
                Name.from("후추"), Result.PUSH)
        );

        assertThat(bets.getBets().values())
                .extracting(Money::getValue)
                .containsExactly(1500, 0);
    }

    @Test
    void 베팅_결과를_반환한다() {
        final Bets bets = new Bets();
        final Map<Name, Money> initialBets = new LinkedHashMap<>();
        initialBets.put(Name.from("허브"), Money.initialBet(1000));
        initialBets.put(Name.from("후추"), Money.initialBet(100));
        bets.addBets(initialBets);

        final Map<Name, Money> result = bets.getBets();

        assertThat(result.values())
                .extracting(Money::getValue)
                .containsExactly(1000, 100);
    }

    @Test
    void 딜러의_수익을_반환한다() {
        final Bets bets = new Bets();
        final Map<Name, Money> initialBets = new LinkedHashMap<>();
        initialBets.put(Name.from("허브"), Money.initialBet(1000));
        initialBets.put(Name.from("후추"), Money.initialBet(100));
        bets.addBets(initialBets);

        Money money = bets.getDealerProfit();

        assertThat(money.getValue()).isEqualTo(-1100);
    }
}
