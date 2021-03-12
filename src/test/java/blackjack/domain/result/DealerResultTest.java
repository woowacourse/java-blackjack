package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerResultTest {

    @DisplayName("Dealer의 수익을 계산하는 기능을 테스트한다")
    @Test
    void testCalculateEarnings() {
        //given
        Player pobi = new Player("pobi", cards -> 21);
        Player woni = new Player("woni", cards -> 18);
        pobi.bet(10_000);
        woni.bet(5_000);
        List<Player> players = Arrays.asList(pobi, woni);
        Dealer dealer = new Dealer(cards -> 20);

        //when
        DealerResult dealerResult = dealer.getDealerResult(players);

        //then
        assertThat(dealerResult.calculateEarnings()).isEqualTo(-5_000.0);
    }
}
