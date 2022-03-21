package blackjack.domain.result;

import java.util.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.player.*;
import blackjack.domain.Fixtures;
import blackjack.domain.state.Ready;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitsTest {

    @ParameterizedTest
    @CsvSource(value = {"100:200:-300", "100:100:-200"}, delimiter = ':')
    @DisplayName("딜러 수익 확인")
    void checkDealerProfit(int firstMoney, int secondMoney, int expected) {
        //플레이어 목록 초기화
        List<Player> playerList = new ArrayList<>();
        Dealer dealer = new Dealer();
        playerList.add(dealer);

        Guest firstGuest = new Guest("guest", new Ready(), firstMoney);
        firstGuest.draw(Fixtures.SPADE_JACK);
        Guest secondGuest = new Guest("guest2", new Ready(), secondMoney);
        secondGuest.draw(Fixtures.SPADE_JACK);
        playerList.add(firstGuest);
        playerList.add(secondGuest);

        //플레이어 배팅 금액 초기화
        Players players = new Players(playerList);

        //수익 계산
        if (firstGuest.isRunning()) {
            firstGuest.stay();
        }
        if (secondGuest.isRunning()) {
            secondGuest.stay();
        }
        Profits profits = Profits.of(players);

        assertThat(profits.getPlayersProfit().get(dealer).getValue()).isEqualTo(expected);
    }
}
