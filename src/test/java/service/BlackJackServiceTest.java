package service;

import domain.card.*;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BlackJackServiceTest {

    @Test
    @DisplayName("딜러와 모든 플레이어는 게임 시작 시, 2장의 카드를 받는다.")
    void 게임_시작시_2장_카드() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi", "james"));

        BlackJackService blackJackService = new BlackJackService(
                new Deck(new DefaultShuffleStrategy()), dealer, players);

        Assertions.assertEquals(dealer.getHand().getHand().size(), 0);
        for (Player player : players.getPlayers()) {
            Assertions.assertEquals(player.getHand().getHand().size(), 0);
        }

        // when
        blackJackService.initHand();

        // then
        Assertions.assertEquals(dealer.getHand().getHand().size(), 2);
        for (Player player : players.getPlayers()) {
            Assertions.assertEquals(player.getHand().getHand().size(), 2);
        }
    }

    @Test
    @DisplayName("플레이어가 딜러를 이긴 경우 승리한다.")
    void 플레이어_승_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        BlackJackService blackJackService = new BlackJackService(
                new Deck(new DefaultShuffleStrategy()), dealer, players);

        dealer.hit(new Card(Rank.JACK, Suit.CLOVER));
        dealer.hit(new Card(Rank.SEVEN, Suit.HEART));

        for (Player player : players.getPlayers()) {
            player.hit(new Card(Rank.ACE, Suit.CLOVER));
            player.hit(new Card(Rank.KING, Suit.CLOVER));
        }

        // when
        Map<String, MatchResult> matchResult = blackJackService.calculateResults();

        // then
        for (Map.Entry<String, MatchResult> matchResultEntry : matchResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.WIN);
        }
    }

    @Test
    @DisplayName("플레이어가 딜러와 비긴 경우 무승부한다.")
    void 플레이어_무승부_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        BlackJackService blackJackService = new BlackJackService(
                new Deck(new DefaultShuffleStrategy()), dealer, players);

        dealer.hit(new Card(Rank.JACK, Suit.CLOVER));
        dealer.hit(new Card(Rank.SEVEN, Suit.HEART));

        for (Player player : players.getPlayers()) {
            player.hit(new Card(Rank.SEVEN, Suit.CLOVER));
            player.hit(new Card(Rank.KING, Suit.CLOVER));
        }

        // when
        Map<String, MatchResult> matchResult = blackJackService.calculateResults();

        // then
        for (Map.Entry<String, MatchResult> matchResultEntry : matchResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.DRAW);
        }
    }

    @Test
    @DisplayName("플레이어가 딜러에게 진 경우 패배한다.")
    void 플레이어_패_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        BlackJackService blackJackService = new BlackJackService(
                new Deck(new DefaultShuffleStrategy()), dealer, players);

        dealer.hit(new Card(Rank.ACE, Suit.CLOVER));
        dealer.hit(new Card(Rank.KING, Suit.CLOVER));

        for (Player player : players.getPlayers()) {
            player.hit(new Card(Rank.JACK, Suit.CLOVER));
            player.hit(new Card(Rank.SEVEN, Suit.HEART));
        }

        // when
        Map<String, MatchResult> matchResult = blackJackService.calculateResults();

        // then
        for (Map.Entry<String, MatchResult> matchResultEntry : matchResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.LOSE);
        }
    }

    @Test
    @DisplayName("플레이어만 버스트일 경우 바로 패배한다.")
    void 플레이어만_버스트_패_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        BlackJackService blackJackService = new BlackJackService(
                new Deck(new DefaultShuffleStrategy()), dealer, players);

        dealer.hit(new Card(Rank.ACE, Suit.CLOVER));
        dealer.hit(new Card(Rank.KING, Suit.CLOVER));

        for (Player player : players.getPlayers()) {
            player.hit(new Card(Rank.JACK, Suit.CLOVER));
            player.hit(new Card(Rank.SEVEN, Suit.HEART));
            player.hit(new Card(Rank.JACK, Suit.HEART));
        }

        // when
        Map<String, MatchResult> matchResult = blackJackService.calculateResults();

        // then
        for (Map.Entry<String, MatchResult> matchResultEntry : matchResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.LOSE);
        }
    }

    @Test
    @DisplayName("딜러만 버스트일 경우 플레이어가 승리한다.")
    void 딜러만_버스트_플레이어_패_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        BlackJackService blackJackService = new BlackJackService(
                new Deck(new DefaultShuffleStrategy()), dealer, players);

        dealer.hit(new Card(Rank.JACK, Suit.CLOVER));
        dealer.hit(new Card(Rank.SEVEN, Suit.HEART));
        dealer.hit(new Card(Rank.JACK, Suit.HEART));

        for (Player player : players.getPlayers()) {
            player.hit(new Card(Rank.ACE, Suit.CLOVER));
            player.hit(new Card(Rank.KING, Suit.CLOVER));
        }

        // when
        Map<String, MatchResult> matchResult = blackJackService.calculateResults();

        // then
        for (Map.Entry<String, MatchResult> matchResultEntry : matchResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.WIN);
        }
    }

    @Test
    @DisplayName("딜러와 플레이어가 동점인 상황에서 플레이어만 블랙잭일 경우 플레이어가 승리한다.")
    void 동점_플레이어_블랙잭_승리_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        BlackJackService blackJackService = new BlackJackService(
                new Deck(new DefaultShuffleStrategy()), dealer, players);

        dealer.hit(new Card(Rank.JACK, Suit.CLOVER));
        dealer.hit(new Card(Rank.ACE, Suit.HEART));
        dealer.hit(new Card(Rank.KING, Suit.HEART));

        for (Player player : players.getPlayers()) {
            player.hit(new Card(Rank.ACE, Suit.CLOVER));
            player.hit(new Card(Rank.KING, Suit.CLOVER));
        }

        // when
        Map<String, MatchResult> matchResult = blackJackService.calculateResults();

        // then
        for (Map.Entry<String, MatchResult> matchResultEntry : matchResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.WIN);
        }
    }

    @Test
    @DisplayName("딜러와 플레이어가 동점인 상황에서 딜러만 블랙잭일 경우 플레이어가 패배한다.")
    void 동점_딜러_블랙잭_플레이어_패배_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        BlackJackService blackJackService = new BlackJackService(
                new Deck(new DefaultShuffleStrategy()), dealer, players);

        dealer.hit(new Card(Rank.ACE, Suit.CLOVER));
        dealer.hit(new Card(Rank.KING, Suit.CLOVER));

        for (Player player : players.getPlayers()) {
            player.hit(new Card(Rank.JACK, Suit.CLOVER));
            player.hit(new Card(Rank.ACE, Suit.HEART));
            player.hit(new Card(Rank.KING, Suit.HEART));
        }

        // when
        Map<String, MatchResult> matchResult = blackJackService.calculateResults();

        // then
        for (Map.Entry<String, MatchResult> matchResultEntry : matchResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.LOSE);
        }
    }

    @Test
    @DisplayName("딜러의 승패를 올바르게 판단한다.")
    void 딜러_승패_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of());
        BlackJackService blackJackService = new BlackJackService(
                new Deck(new DefaultShuffleStrategy()), dealer, players);

        Map<String, MatchResult> playerResults = new HashMap<>();

        playerResults.put("pobi", MatchResult.WIN);
        playerResults.put("sisi", MatchResult.WIN);
        playerResults.put("ao", MatchResult.WIN);
        playerResults.put("james", MatchResult.DRAW);
        playerResults.put("lala", MatchResult.LOSE);

        // when
        Map<MatchResult, Integer> matchResults = blackJackService.calculateDealerResult(playerResults);

        // then
        Assertions.assertEquals(matchResults.get(MatchResult.LOSE), 3);
        Assertions.assertEquals(matchResults.get(MatchResult.DRAW), 1);
        Assertions.assertEquals(matchResults.get(MatchResult.WIN), 1);
    }
}
