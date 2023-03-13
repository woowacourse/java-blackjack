package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.card.Result;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BetsTest {

    @Test
    void 이름과_금액을_받아_베팅을_한다() {
        final Bets bets = new Bets();
        final Player player = Gambler.create("허브");
        bets.addBet(player, 1000);

        final Map<Player, Money> result = bets.getBets();

        assertThat(result.get(player)).isEqualTo(Money.initialBet(1000));
    }

    @Test
    void 베팅_결과를_계산한다() {
        final Bets bets = new Bets();
        final Player herb = Gambler.create("허브");
        final Player pepper = Gambler.create("후추");
        bets.addBet(herb, 1000);
        bets.addBet(pepper, 100);

        bets.calculateProfit(Map.of(
                herb, Result.BLACKJACK_WIN,
                pepper, Result.PUSH)
        );

        assertThat(bets.getBets()).contains(
                entry(herb, Money.initialBet(1500)),
                entry(pepper, Money.ZERO)
        );
    }

    @Test
    void 베팅_결과를_반환한다() {
        final Bets bets = new Bets();
        final Player herb = Gambler.create("허브");
        final Player pepper = Gambler.create("후추");
        bets.addBet(herb, 100);
        bets.addBet(pepper, 10000);

        final Map<Player, Money> result = bets.getBets();

        assertThat(result).contains(
                entry(herb, Money.initialBet(100)),
                entry(pepper, Money.initialBet(10000))
        );
    }

    @Test
    void 딜러의_수익을_반환한다() {
        final Bets bets = new Bets();
        bets.addBet(Gambler.create("허브"), 1000);
        bets.addBet(Gambler.create("후추"), 2000);

        Money money = bets.getDealerProfit();

        assertThat(money.getValue()).isEqualTo(-3000);
    }
}
