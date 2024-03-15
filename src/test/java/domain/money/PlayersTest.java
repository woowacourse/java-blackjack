package domain.money;

import static domain.card.Number.ACE;
import static domain.card.Number.SIX;
import static domain.card.Number.TEN;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Number;
import domain.user.Dealer;
import domain.user.Hand;
import domain.user.Name;
import domain.user.Player;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayersTest {
    @ParameterizedTest(name = "{2}")
    @CsvSource(value = {"SEVEN,1000,win", "SIX,0,draw", "FIVE,-1000,lose"})
    @DisplayName("플레이 결과에 따라 플레이어의 돈을 바꾼다.")
    void changeByPlayerResultsWinTest(Number number, int moneyValue, String result) {
        Player player = new Player(new Name("aa"), new Hand(new Card(SPADE, number)));
        Dealer dealer = new Dealer(new Hand(new Card(SPADE, SIX)));
        Players players = new Players(new HashMap<>(Map.of(player, new Money(1000))));
        Players resultPlayers = players.generateMoneyResult(dealer);

        assertThat(resultPlayers.calculateDealerMoney() * -1).isEqualTo(moneyValue);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 승리한 경우 금액의 1.5배를 받는다.")
    void earn150PercentMoneyWhenBlackjackAndWin() {
        Player player = new Player(new Name("aa"), new Hand(new Card(SPADE, ACE), new Card(SPADE, TEN)));
        Dealer dealer = new Dealer(new Hand(new Card(SPADE, SIX)));
        Players players = new Players(new HashMap<>(Map.of(player, new Money(1000))));
        Players resultPlayers = players.generateMoneyResult(dealer);

        assertThat(resultPlayers.calculateDealerMoney() * -1).isEqualTo(1500);

    }

    @Test
    @DisplayName("딜러의 금액은 플레이어가 잃은 금액이다.")
    void calculateDealerMoneyTest() {
        Player player = new Player(new Name("aa"), new Hand());
        Players players = new Players(new HashMap<>(Map.of(player, new Money(1000))));

        assertThat(players.calculateDealerMoney()).isEqualTo(-1000);
    }
}
