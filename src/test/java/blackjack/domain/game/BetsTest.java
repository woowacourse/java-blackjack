package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
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
        final Map<Player, Money> initialBets = new LinkedHashMap<>();
        final Gambler herb = Gambler.create("허브");
        final Gambler pepper = Gambler.create("후추");
        initialBets.put(herb, Money.initialBet(1000));
        initialBets.put(pepper, Money.initialBet(100));
        final Bets bets = new Bets(initialBets);

        final Bets result = bets.calculateProfit(Map.of(herb, Result.BLACKJACK_WIN, pepper, Result.PUSH));

        assertThat(result.getBets().values())
                .extracting(Money::getValue)
                .containsExactly(1500, 0);
    }

    @Test
    void 베팅_결과를_반환한다() {
        final Map<Player, Money> initialBets = new LinkedHashMap<>();
        initialBets.put(Gambler.create("허브"), Money.initialBet(1000));
        initialBets.put(Gambler.create("후추"), Money.initialBet(100));

        final Bets bets = new Bets(initialBets);

        assertThat(bets.getBets().values())
                .extracting(Money::getValue)
                .containsExactly(1000, 100);
    }

    @Test
    void 딜러의_수익을_반환한다() {
        final Map<Player, Money> initialBets = new LinkedHashMap<>();
        initialBets.put(Gambler.create("허브"), Money.initialBet(1000));
        initialBets.put(Gambler.create("후추"), Money.initialBet(100));
        final Bets bets = new Bets(initialBets);

        Money money = bets.getDealerProfit();

        assertThat(money.getValue()).isEqualTo(-1100);
    }
}
