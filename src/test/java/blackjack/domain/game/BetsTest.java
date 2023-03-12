package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.player.Name;
import blackjack.domain.player.Result;
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
        final Name player = Name.from("허브");
        bets.addBet(player, 1000);

        final Map<Name, Money> result = bets.getBets();

        assertThat(result.get(player)).isEqualTo(Money.initialBet(1000));
    }

    @Test
    void 베팅_결과를_계산한다() {
        final Bets bets = new Bets();
        bets.addBet(Name.from("허브"), 1000);
        bets.addBet(Name.from("후추"), 100);

        bets.calculateProfit(Map.of(
                Name.from("허브"), Result.BLACKJACK_WIN,
                Name.from("후추"), Result.PUSH)
        );

        assertThat(bets.getBets()).contains(
                entry(Name.from("허브"), Money.initialBet(1500)),
                entry(Name.from("후추"), Money.ZERO)
        );
    }

    @Test
    void 베팅_결과를_반환한다() {
        final Bets bets = new Bets();
        bets.addBet(Name.from("허브"), 100);
        bets.addBet(Name.from("후추"), 10000);

        final Map<Name, Money> result = bets.getBets();

        assertThat(result).contains(
                entry(Name.from("허브"), Money.initialBet(100)),
                entry(Name.from("후추"), Money.initialBet(10000))
        );
    }

    @Test
    void 딜러의_수익을_반환한다() {
        final Bets bets = new Bets();
        bets.addBet(Name.from("허브"), 1000);
        bets.addBet(Name.from("후추"), 2000);

        Money money = bets.getDealerProfit();

        assertThat(money.getValue()).isEqualTo(-3000);
    }
}
