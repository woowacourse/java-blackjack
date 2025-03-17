package model.bet;

import bet.Money;
import bet.ProfitStatus;
import card.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import participant.Dealer;
import participant.Player;
import participant.Players;
import result.GameResult;
import util.TestCardDistributor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ProfitStatusTest {
    @DisplayName("플레이어 배팅 상태가 초기화 되는지")
    @ParameterizedTest
    @MethodSource("makeProfitStatusTestData")
    void plusProfitMoneyTest(Player expectedPlayer, Money expectedMoney) {
        //given
        Map<Player, Money> profits = new HashMap<>();
        Players players = new Players(List.of(new Player("hippo"), new Player("pobi")));
        for (Player player : players.getPlayers()) {
            profits.put(player, new Money(1000));
        }

        //when
        ProfitStatus profitStatus = new ProfitStatus(profits);
        Map<Player, Money> values = profitStatus.getValues();

        //then
        Assertions.assertThat(values).containsEntry(expectedPlayer, expectedMoney);
    }

    private static Stream<Arguments> makeProfitStatusTestData() {
        return Stream.of(
                Arguments.arguments(
                        new Player("hippo"),
                        new Money(1000)
                ),
                Arguments.arguments(
                        new Player("pobi"),
                        new Money(1000)
                )
        );
    }

    @DisplayName("플레이어 배팅 결과가 제대로 계산되는 지")
    @ParameterizedTest
    @MethodSource("makeBetResultTestData")
    void calculatePlayerBetResult(List<Card> playerCard, List<Card> dealerCard, long expectedProfit) {
        //given
        Map<Player, Money> profits = new HashMap<>();

        Players players = new Players(List.of(new Player("hippo")));
        for (Player player : players.getPlayers()) {
            profits.put(player, new Money(1000L));
            TestCardDistributor.divideCardToPlayer(playerCard, player);
        }
        Dealer dealer = new Dealer();
        TestCardDistributor.divideCardToDealer(dealerCard, dealer);

        GameResult gameResult = new GameResult();
        ProfitStatus profitStatus = new ProfitStatus(profits);
        //when
        Map<Player, Long> betResult = profitStatus.calculateBetResult(gameResult.calculatePlayersMatchResult(players, dealer));
        Long actualProfit = betResult.get(new Player("hippo"));
        //then
        Assertions.assertThat(actualProfit).isEqualTo(expectedProfit);
    }

    private static Stream<Arguments> makeBetResultTestData() {
        return Stream.of(
                Arguments.arguments(
                        List.of(new Card(Suit.DIAMONDS,NormalRank.JACK), new Card(Suit.DIAMONDS, AceRank.SOFT_ACE)), // 플레이어 블랙잭
                        List.of(new Card(Suit.DIAMONDS,NormalRank.JACK), new Card(Suit.DIAMONDS,AceRank.SOFT_ACE)), // 딜러 블랙잭
                        0L
                ),
                Arguments.arguments(
                        List.of(new Card(Suit.DIAMONDS,NormalRank.JACK), new Card(Suit.DIAMONDS, AceRank.SOFT_ACE)), // 플레이어 블랙잭
                        List.of(new Card(Suit.DIAMONDS,NormalRank.JACK), new Card(Suit.DIAMONDS,NormalRank.FIVE)), // 딜러 일반
                        1500L
                ),
                Arguments.arguments(
                        List.of(new Card(Suit.DIAMONDS,NormalRank.JACK), new Card(Suit.DIAMONDS,NormalRank.FIVE)), // 플레이어 일반
                        List.of(new Card(Suit.DIAMONDS,NormalRank.JACK), new Card(Suit.DIAMONDS, AceRank.SOFT_ACE)), // 딜러 블랙잭
                        -1000L
                ),
                Arguments.arguments(
                        List.of(new Card(Suit.DIAMONDS,NormalRank.FIVE), new Card(Suit.DIAMONDS, NormalRank.THREE), new Card(Suit.DIAMONDS, NormalRank.JACK)), // 플레이어 일반 18
                        List.of(new Card(Suit.DIAMONDS,NormalRank.JACK), new Card(Suit.DIAMONDS, NormalRank.SEVEN)), // 딜러 일반 17
                        1000L
                ),
                Arguments.arguments(
                        List.of(new Card(Suit.DIAMONDS,NormalRank.JACK), new Card(Suit.DIAMONDS, NormalRank.SEVEN)), // 플레이어 일반 17
                        List.of(new Card(Suit.DIAMONDS,NormalRank.FIVE), new Card(Suit.DIAMONDS, NormalRank.THREE), new Card(Suit.DIAMONDS, NormalRank.JACK)), // 딜러 일반 18
                        -1000L
                ),
                Arguments.arguments(
                        List.of(new Card(Suit.DIAMONDS,NormalRank.JACK), new Card(Suit.DIAMONDS, NormalRank.SEVEN), new Card(Suit.DIAMONDS, NormalRank.SEVEN)), // 플레이어 버스트 24
                        List.of(new Card(Suit.DIAMONDS,NormalRank.FIVE), new Card(Suit.DIAMONDS, NormalRank.THREE), new Card(Suit.DIAMONDS, NormalRank.JACK)), // 딜러 일반 18
                        -1000L
                ),
                Arguments.arguments(
                        List.of(new Card(Suit.DIAMONDS,NormalRank.JACK), new Card(Suit.DIAMONDS, NormalRank.SEVEN), new Card(Suit.DIAMONDS, NormalRank.SEVEN)), // 플레이어 버스트 24
                        List.of(new Card(Suit.DIAMONDS,NormalRank.JACK), new Card(Suit.DIAMONDS, NormalRank.SEVEN), new Card(Suit.DIAMONDS, NormalRank.SEVEN)), // 딜러 버스트 24
                        -1000L
                ),
                Arguments.arguments(
                        List.of(new Card(Suit.DIAMONDS,NormalRank.FIVE), new Card(Suit.DIAMONDS, NormalRank.THREE), new Card(Suit.DIAMONDS, NormalRank.JACK)), // 플레이어 일반 18
                        List.of(new Card(Suit.DIAMONDS,NormalRank.JACK), new Card(Suit.DIAMONDS, NormalRank.SEVEN), new Card(Suit.DIAMONDS, NormalRank.SEVEN)), // 딜러 버스트 24
                        1000L
                )
        );
    }

    @DisplayName("딜러 배팅 결과가 제대로 계산되는 지")
    @Test
    void calculateDealerBetResult() {
        //given
        Player hippo = new Player("hippo");
        Player pobi = new Player("pobi");
        Player james = new Player("james");

        Players players = new Players(List.of(hippo, pobi, james));

        List<Card> hippoCards = List.of(new Card(Suit.CLUBS, NormalRank.FIVE),new Card(Suit.CLUBS, NormalRank.TEN),new Card(Suit.CLUBS, NormalRank.SIX)); // 21
        List<Card> pobiCards = List.of(new Card(Suit.CLUBS, NormalRank.JACK),new Card(Suit.CLUBS, AceRank.SOFT_ACE)); // 21 blackjack
        List<Card> jamesCards = List.of(new Card(Suit.CLUBS, NormalRank.TEN),new Card(Suit.CLUBS, NormalRank.KING),new Card(Suit.CLUBS, NormalRank.TEN)); // 30

        TestCardDistributor.divideCardToPlayer(hippoCards, hippo);
        TestCardDistributor.divideCardToPlayer(pobiCards, pobi);
        TestCardDistributor.divideCardToPlayer(jamesCards, james);

        Map<Player, Money> profits = new HashMap<>();
        profits.put(new Player("hippo"), new Money(2000L));
        profits.put(new Player("pobi"), new Money(1000L));
        profits.put(new Player("james"), new Money(1500L));

        Dealer dealer = new Dealer();
        List<Card> dealerCards = List.of(new Card(Suit.CLUBS, NormalRank.TEN),new Card(Suit.CLUBS, NormalRank.SEVEN)); // 17
        TestCardDistributor.divideCardToDealer(dealerCards, dealer);

        GameResult gameResult = new GameResult();
        ProfitStatus profitStatus = new ProfitStatus(profits);
        Map<Player, Long> betResult = profitStatus.calculateBetResult(gameResult.calculatePlayersMatchResult(players, dealer));
        long dealerProfit = profitStatus.calculateDealerBetResult(betResult);
        //when

        //then
        Assertions.assertThat(dealerProfit).isEqualTo(-2000L);
    }
}
