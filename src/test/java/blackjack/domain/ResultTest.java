package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ResultTest {
    private static Stream<Arguments> getPlayerAndDealer() {
        Map<Name, Batting> playerNamesAndBattings = new HashMap<>();
        playerNamesAndBattings.put(new Name("pobi"), Batting.from(100.0));
        Player testPlayer = new Players(playerNamesAndBattings)
                .getPlayers()
                .get(0);
        Dealer dealer = new Dealer();

        return Stream.of(arguments(testPlayer, dealer));
    }

    @DisplayName("블랙잭 승리(1.5배) : 플레이어 첫턴 블랙잭 && dealer가 블랙잭이 아닐 때")
    @ParameterizedTest
    @MethodSource("getPlayerAndDealer")
    void should_returnBlackJackWin_When_PlayerBlackJack_And_DealerIsNotBlackJack(Player testPlayer, Dealer dealer) {
        testPlayer.addCard(Card.create(0));
        testPlayer.addCard(Card.create(9));
        dealer.addCard(Card.create(0));

        double profit = Result.calculateProfit(testPlayer, dealer);

        assertThat(profit).isEqualTo(100.0 * 1.5);
    }

    @DisplayName("플레이어 승 : 플레이어 카드패 > 딜러 카드패")
    @ParameterizedTest
    @MethodSource("getPlayerAndDealer")
    void should_returnWin_When_PlayerHands_Higher_Than_DealerHands(Player testPlayer, Dealer dealer) {
        testPlayer.addCard(Card.create(0));
        testPlayer.addCard(Card.create(8));
        dealer.addCard(Card.create(0));

        double profit = Result.calculateProfit(testPlayer, dealer);

        assertThat(profit).isEqualTo(100.0);
    }

    @DisplayName("플레이어 승 : 플레이어 - NonBurst, 딜러 - Burst")
    @ParameterizedTest
    @MethodSource("getPlayerAndDealer")
    void should_returnWin_When_PlayerNonBurst_DealerBurst(Player testPlayer, Dealer dealer) {
        testPlayer.addCard(Card.create(0));
        testPlayer.addCard(Card.create(8));
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(9));

        double profit = Result.calculateProfit(testPlayer, dealer);

        assertThat(profit).isEqualTo(100.0);
    }

    @DisplayName("플레이어 패 : 플레이어 카드패 < 딜러 카드패")
    @ParameterizedTest
    @MethodSource("getPlayerAndDealer")
    void should_returnLose_When_PlayerHands_Lower_Than_DealerHands(Player testPlayer, Dealer dealer) {
        testPlayer.addCard(Card.create(0));
        testPlayer.addCard(Card.create(1));
        dealer.addCard(Card.create(0));
        dealer.addCard(Card.create(9));

        double profit = Result.calculateProfit(testPlayer, dealer);

        assertThat(profit).isEqualTo(100.0 * -1);
    }

    @DisplayName("플레이어 패 : 플레이어 - Burst, 딜러 - NonBurst")
    @ParameterizedTest
    @MethodSource("getPlayerAndDealer")
    void should_returnLose_When_PlayerBurst_DealerNonBurst(Player testPlayer, Dealer dealer) {
        testPlayer.addCard(Card.create(9));
        testPlayer.addCard(Card.create(9));
        testPlayer.addCard(Card.create(9)); //player -Bust
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(9));

        double profit = Result.calculateProfit(testPlayer, dealer);

        assertThat(profit).isEqualTo(100.0 * -1);
    }

    @DisplayName("플레이어 패 : 플레이어- Burst, 딜러- Burst")
    @ParameterizedTest
    @MethodSource("getPlayerAndDealer")
    void should_returnDraw_When_Both_Burst(Player testPlayer, Dealer dealer) {
        testPlayer.addCard(Card.create(9));
        testPlayer.addCard(Card.create(9));
        testPlayer.addCard(Card.create(9));

        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(9));

        double profit = Result.calculateProfit(testPlayer, dealer);

        assertThat(profit).isEqualTo(100.0 * -1);
    }

    @DisplayName("무승부 : 플레이어 카드패 == 딜러 카드패")
    @ParameterizedTest
    @MethodSource("getPlayerAndDealer")
    void should_returnDraw_When_PlayerHands_Equal_DealerHands(Player testPlayer, Dealer dealer) {
        testPlayer.addCard(Card.create(0));
        dealer.addCard(Card.create(0));

        double profit = Result.calculateProfit(testPlayer, dealer);

        assertThat(profit).isZero();
    }
}
