package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.dealer.Dealer;
import fixture.DealerFixture;
import fixture.PlayerFixture;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("PlayersProfit을 생성한다.")
    @Test
    void testCreate() {
        // given
        Map<Player, Profit> rawProfits = new LinkedHashMap<>();
        rawProfits.put(PlayerFixture.createPobi(), new Profit(new BigDecimal(10000)));
        rawProfits.put(PlayerFixture.createJason(), new Profit(new BigDecimal(20000)));

        // when
        Players players = new Players(rawProfits);

        // then
        assertThat(players.getProfits()).containsExactlyEntriesOf(Map.of(
                PlayerFixture.createPobi(), new Profit(new BigDecimal(10000)),
                PlayerFixture.createJason(), new Profit(new BigDecimal(20000))
        ));
    }

    @DisplayName("주어진 배팅 금액으로 PlayersProfit을 생성한다.")
    @Test
    void testCreateWith() {
        // given
        PlayerName pobi = new PlayerName("pobi");
        PlayerName jason = new PlayerName("jason");
        PlayerNames playerNames = new PlayerNames(List.of(pobi, jason));

        // when
        Players players = Players.create(playerNames,
                playerName -> getBetAmount(playerName, pobi, jason));

        // then
        assertThat(players.getProfits()).containsExactlyEntriesOf(Map.of(
                PlayerFixture.createPobi(), new Profit(new BigDecimal(10000)),
                PlayerFixture.createJason(), new Profit(new BigDecimal(20000))
        ));
    }

    private String getBetAmount(PlayerName name, PlayerName pobi, PlayerName jason) {
        if (name.equals(pobi)) {
            return "10000";
        }
        if (name.equals(jason)) {
            return "20000";
        }
        throw new IllegalArgumentException();
    }

    @DisplayName("배팅 금액과 결과에 따라 결정된 배팅 배수를 곱해 수익을 계산한다.")
    @Test
    void testCalculateProfit() {
        // given
        Map<Player, Profit> rawProfits = new LinkedHashMap<>();
        rawProfits.put(PlayerFixture.createPobi(), new Profit(new BigDecimal(10000)));
        rawProfits.put(PlayerFixture.createJason(), new Profit(new BigDecimal(20000)));
        Players players = new Players(rawProfits);

        Dealer dealer = DealerFixture.createDealer();

        // when
        players.calculateProfit(dealer);

        // then
        assertThat(players.getProfits()).containsExactlyEntriesOf(Map.of(
                PlayerFixture.createPobi(), new Profit(new BigDecimal(10000)),
                PlayerFixture.createJason(), new Profit(new BigDecimal(-20000))
        ));
    }

    @DisplayName("딜러의 수익은 플레이어들이 잃은 금액에서 얻은 금액을 제한 금액이다.")
    @Test
    void testDealerProfit() {
        // given
        Map<Player, Profit> rawProfits = new LinkedHashMap<>();
        rawProfits.put(PlayerFixture.createPobi(), new Profit(new BigDecimal(10000)));
        rawProfits.put(PlayerFixture.createJason(), new Profit(new BigDecimal(20000)));
        Players players = new Players(rawProfits);

        Dealer dealer = DealerFixture.createDealer();

        players.calculateProfit(dealer);

        // when
        Profit dealerProfit = players.dealerProfit();

        // then
        assertThat(dealerProfit).isEqualTo(new Profit(new BigDecimal(10000)));
    }
}
