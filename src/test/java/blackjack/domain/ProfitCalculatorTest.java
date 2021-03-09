package blackjack.domain;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.money.Money;
import blackjack.domain.utils.ProfitCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static blackjack.domain.Fixtures.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProfitCalculatorTest {

    @DisplayName("이익 계산 : 플레이어 스테이, 딜러 스테이")
    @Test
    void calculateProfit1() {
        Dealer dealer = new Dealer();
        dealer.initState(HIT);

        Players players = Players.of(Arrays.asList("joanne"), Arrays.asList(new Money(10000)));
        for (Player player : players) {
            player.initState(HIT);
        }

        Map<String, Double> answer = new LinkedHashMap<>();
        answer.put("딜러", 0.0);
        answer.put("joanne", 0.0);
        assertThat(ProfitCalculator.calculateProfitOf(dealer, players)).isEqualTo(answer);
    }

    @DisplayName("이익 계산 : 플레이어 버스트, 딜러 버스트 아닌 경우")
    @Test
    void calculateProfit2() {
        Dealer dealer = new Dealer();
        dealer.initState(HIT);

        Players players = Players.of(Arrays.asList("joanne"), Arrays.asList(new Money(10000)));
        for (Player player : players) {
            player.initState(BUST);
            player.receiveCard(CLUB_KING);
        }

        Map<String, Double> answer = new LinkedHashMap<>();
        answer.put("딜러", 10000.0);
        answer.put("joanne", -10000.0);
        assertThat(ProfitCalculator.calculateProfitOf(dealer, players)).isEqualTo(answer);
    }

    @DisplayName("이익 계산 : 딜러 버스트, 플레이어 버스트 아닌 경우")
    @Test
    void calculateProfit3() {
        Dealer dealer = new Dealer();
        dealer.initState(BUST);
        dealer.receiveCard(CLUB_KING);

        Players players = Players.of(Arrays.asList("joanne"), Arrays.asList(new Money(10000)));
        for (Player player : players) {
            player.initState(HIT);
        }

        Map<String, Double> answer = new LinkedHashMap<>();
        answer.put("딜러", -10000.0);
        answer.put("joanne", +10000.0);
        assertThat(ProfitCalculator.calculateProfitOf(dealer, players)).isEqualTo(answer);
    }

    @DisplayName("이익 계산 : 둘 다 블랙잭인 경우")
    @Test
    void calculateProfit4() {
        Dealer dealer = new Dealer();
        dealer.initState(BLACKJACK);

        Players players = Players.of(Arrays.asList("joanne"), Arrays.asList(new Money(10000)));
        for (Player player : players) {
            player.initState(BLACKJACK);
        }

        Map<String, Double> answer = new LinkedHashMap<>();
        answer.put("딜러", 0.0);
        answer.put("joanne", 0.0);
        assertThat(ProfitCalculator.calculateProfitOf(dealer, players)).isEqualTo(answer);
    }
}