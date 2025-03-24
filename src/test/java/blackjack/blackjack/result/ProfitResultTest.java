package blackjack.blackjack.result;

import static blackjack.fixture.TestFixture.provide16Cards;
import static blackjack.fixture.TestFixture.provideBlackjack;
import static blackjack.fixture.TestFixture.provideOver16Cards;
import static blackjack.fixture.TestFixture.provideUnder16Cards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.blackjack.participant.Dealer;
import blackjack.blackjack.participant.Participant;
import blackjack.blackjack.participant.Player;
import blackjack.blackjack.participant.Players;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitResultTest {

    @Test
    @DisplayName("수익을 계산한다")
    void calculateProfit() {
        // Given
        final Dealer dealer = new Dealer(provide16Cards());
        final Player mint = new Player(provideOver16Cards(), "밍트", BigDecimal.valueOf(10_000));
        final Player mj = new Player(provideUnder16Cards(), "엠제이", BigDecimal.valueOf(20_000));
        final Player pobi = new Player(provide16Cards(), "포비", BigDecimal.valueOf(30_000));
        final Player norang = new Player(provideBlackjack(), "노랑", BigDecimal.valueOf(50_000));

        mint.stay();
        mj.stay();
        pobi.stay();
        final ProfitResult profitResult = ProfitResult.from(dealer, new Players(List.of(mint, mj, pobi, norang)));

        // When
        final Map<Participant, BigDecimal> profits = profitResult.getProfitByParticipant();

        // Then
        Assertions.assertAll(
                () -> assertThat(profits.get(dealer)).isEqualTo(new BigDecimal("-65000.0")),
                () -> assertThat(profits.get(mint)).isEqualTo(new BigDecimal(10000)),
                () -> assertThat(profits.get(mj)).isEqualTo(new BigDecimal(-20000)),
                () -> assertThat(profits.get(pobi)).isEqualTo(BigDecimal.ZERO),
                () -> assertThat(profits.get(norang)).isEqualTo(new BigDecimal("75000.0"))
        );
    }
}
