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

class GameResultTest {

    private static Stream<Arguments> getPlayerAndDealer() {
        Map<Name, Batting> playerNamesAndBattings = new HashMap<>();
        playerNamesAndBattings.put(new Name("pobi"), Batting.from(1.0));
        Players players = new Players(playerNamesAndBattings);
        Dealer dealer = new Dealer();
        return Stream.of(arguments(players, dealer));
    }

    @DisplayName("블랙잭 승 : 플레이어 첫턴 블랙잭 && dealer가 블랙잭이 아닐 때")
    @ParameterizedTest
    @MethodSource("getPlayerAndDealer")
    void should_returnBlackJackWin_When_PlayerBlackJack_And_DealerIsNotBlackJack(Players players, Dealer dealer) {
        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.create(0));
        testPlayer.addCard(Card.create(9));

        dealer.addCard(Card.create(0));
        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getTargetResultCount(Result.BLACK_JACk_WIN)).isOne();
    }

    @DisplayName("플레이어 승 : 플레이어 카드패 > 딜러 카드패")
    @ParameterizedTest
    @MethodSource("getPlayerAndDealer")
    void should_returnWin_When_PlayerHands_Higher_Than_DealerHands(Players players, Dealer dealer) {
        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.create(0));
        testPlayer.addCard(Card.create(8));

        dealer.addCard(Card.create(0));
        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getTargetResultCount(Result.WIN)).isOne();
    }

    @DisplayName("플레이어 승 : 플레이어 - NonBurst, 딜러 - Burst")
    @ParameterizedTest
    @MethodSource("getPlayerAndDealer")
    void should_returnWin_When_PlayerNonBurst_DealerBurst(Players players, Dealer dealer) {
        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.create(0));
        testPlayer.addCard(Card.create(8));

        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(9));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getTargetResultCount(Result.WIN)).isOne();
    }

    @DisplayName("플레이어 패 : 플레이어 카드패 < 딜러 카드패")
    @ParameterizedTest
    @MethodSource("getPlayerAndDealer")
    void should_returnLose_When_PlayerHands_Lower_Than_DealerHands(Players players, Dealer dealer) {
        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.create(0));
        testPlayer.addCard(Card.create(1));

        dealer.addCard(Card.create(0));
        dealer.addCard(Card.create(9));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getTargetResultCount(Result.LOSE)).isOne();
    }

    @DisplayName("플레이어 패 : 플레이어 - Burst, 딜러 - NonBurst")
    @ParameterizedTest
    @MethodSource("getPlayerAndDealer")
    void should_returnLose_When_PlayerBurst_DealerNonBurst(Players players, Dealer dealer) {
        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.create(9));
        testPlayer.addCard(Card.create(9));
        testPlayer.addCard(Card.create(9));

        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(9));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getTargetResultCount(Result.LOSE)).isOne();
    }

    @DisplayName("무승부 : 플레이어 카드패 == 딜러 카드패")
    @ParameterizedTest
    @MethodSource("getPlayerAndDealer")
    void should_returnDraw_When_PlayerHands_Equal_DealerHands(Players players, Dealer dealer) {
        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.create(0));

        dealer.addCard(Card.create(0));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getTargetResultCount(Result.DRAW)).isOne();
    }

    @DisplayName("패 : 플레이어- Burst, 딜러- Burst")
    @ParameterizedTest
    @MethodSource("getPlayerAndDealer")
    void should_returnDraw_When_Both_Burst(Players players, Dealer dealer) {
        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.create(9));
        testPlayer.addCard(Card.create(9));
        testPlayer.addCard(Card.create(9));

        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(9));
        dealer.addCard(Card.create(9));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getTargetResultCount(Result.LOSE)).isOne();
    }
}
