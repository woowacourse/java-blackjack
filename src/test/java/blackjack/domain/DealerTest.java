package blackjack.domain;

import static blackjack.domain.Number.ACE;
import static blackjack.domain.Number.JACK;
import static blackjack.domain.Number.KING;
import static blackjack.domain.Number.QUEEN;
import static blackjack.domain.Number.SEVEN;
import static blackjack.domain.Number.THREE;
import static blackjack.domain.Symbol.CLUB;
import static blackjack.domain.Symbol.DIAMOND;
import static blackjack.domain.Symbol.HEART;
import static blackjack.domain.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {

    @DisplayName("합을 비교해 최종 승패를 결정한다.")
    @Test
    void should_Return_GameResult() {
        Dealer dealer = new Dealer();

        dealer.take(new Card(SPADE, QUEEN));
        dealer.take(new Card(CLUB, SEVEN));

        Players players = Players.from(List.of("a", "b", "c"));

        Player playerA = players.getPlayers().get(0);
        playerA.take(new Card(HEART, QUEEN));
        playerA.take(new Card(DIAMOND, ACE));

        Player playerB = players.getPlayers().get(1);
        playerB.take(new Card(CLUB, THREE));
        playerB.take(new Card(SPADE, KING));

        Player playerC = players.getPlayers().get(2);
        playerC.take(new Card(HEART, SEVEN));
        playerC.take(new Card(DIAMOND, JACK));

        dealer.addPlayerBetMoney(playerA, 10000);
        dealer.addPlayerBetMoney(playerB, 20000);
        dealer.addPlayerBetMoney(playerC, 30000);

        GameResult gameResult = dealer.judgeGameResult(players);
        DealerResult dealerResult = gameResult.getDealerResult();
        assertThat(dealerResult.getDealerResult()).isEqualTo(10000);

        List<PlayerResult> results = gameResult.getPlayersResult();

        PlayerResult resultA = results.get(0);
        assertThat(resultA.getName()).isEqualTo("a");
        assertThat(resultA.getBenefit()).isEqualTo(10000);

        PlayerResult resultB = results.get(1);
        assertThat(resultB.getName()).isEqualTo("b");
        assertThat(resultB.getBenefit()).isEqualTo(-20000);

        PlayerResult resultC = results.get(2);
        assertThat(resultC.getName()).isEqualTo("c");
        assertThat(resultC.getBenefit()).isEqualTo(0);
    }
}
