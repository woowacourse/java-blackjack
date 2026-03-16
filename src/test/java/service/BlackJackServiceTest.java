package service;

import domain.card.*;
import domain.card.DefaultShuffleStrategy;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.money.BettingResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class BlackJackServiceTest {

    @Test
    @DisplayName("딜러와 모든 플레이어는 게임 시작 시, 2장의 카드를 받는다.")
    void 게임_시작시_2장_카드() {
        // given
        Dealer dealer = new Dealer();
        Map<String, Integer> playerBets = new HashMap<>();
        playerBets.put("pobi", 1000);
        Players players = new Players(playerBets);

        BlackJackService blackJackService = new BlackJackService(
                new Deck(new DefaultShuffleStrategy()), dealer, players);

        Assertions.assertEquals(0, dealer.getHand().getHand().size());
        for (Player player : players.getPlayers()) {
            Assertions.assertEquals(0, player.getHand().getHand().size());
        }

        // when
        blackJackService.initHand();

        // then
        Assertions.assertEquals(2, dealer.getHand().getHand().size());
        for (Player player : players.getPlayers()) {
            Assertions.assertEquals(2, player.getHand().getHand().size());
        }
    }

    @Test
    @DisplayName("플레이어가 딜러를 이긴 경우 승리한다.")
    void 플레이어_승_판단() {
        // given
        Dealer dealer = new Dealer();
        Map<String, Integer> playerBets = new HashMap<>();
        playerBets.put("pobi", 1000);
        Players players = new Players(playerBets);

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
            Assertions.assertEquals(MatchResult.WIN, matchResultEntry.getValue());
        }
    }

    @Test
    @DisplayName("플레이어가 딜러와 비긴 경우 무승부한다.")
    void 플레이어_무승부_판단() {
        // given
        Dealer dealer = new Dealer();
        Map<String, Integer> playerBets = new HashMap<>();
        playerBets.put("pobi", 1000);
        Players players = new Players(playerBets);

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
            Assertions.assertEquals(MatchResult.DRAW, matchResultEntry.getValue());
        }
    }

    @Test
    @DisplayName("플레이어가 딜러에게 진 경우 패배한다.")
    void 플레이어_패_판단() {
        // given
        Dealer dealer = new Dealer();
        Map<String, Integer> playerBets = new HashMap<>();
        playerBets.put("pobi", 1000);
        Players players = new Players(playerBets);

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
            Assertions.assertEquals(MatchResult.LOSE, matchResultEntry.getValue());
        }
    }

    @Test
    @DisplayName("플레이어만 버스트일 경우 바로 패배한다.")
    void 플레이어만_버스트_패_판단() {
        // given
        Dealer dealer = new Dealer();
        Map<String, Integer> playerBets = new HashMap<>();
        playerBets.put("pobi", 1000);
        Players players = new Players(playerBets);

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
            Assertions.assertEquals(MatchResult.LOSE, matchResultEntry.getValue());
        }
    }

    @Test
    @DisplayName("딜러만 버스트일 경우 플레이어가 승리한다.")
    void 딜러만_버스트_플레이어_패_판단() {
        // given
        Dealer dealer = new Dealer();
        Map<String, Integer> playerBets = new HashMap<>();
        playerBets.put("pobi", 1000);
        Players players = new Players(playerBets);

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
            Assertions.assertEquals(MatchResult.WIN, matchResultEntry.getValue());
        }
    }

    @Test
    @DisplayName("딜러와 플레이어가 동점인 상황에서 플레이어만 블랙잭일 경우 플레이어가 승리한다.")
    void 동점_플레이어_블랙잭_승리_판단() {
        // given
        Dealer dealer = new Dealer();
        Map<String, Integer> playerBets = new HashMap<>();
        playerBets.put("pobi", 1000);
        Players players = new Players(playerBets);

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
            Assertions.assertEquals(MatchResult.WIN, matchResultEntry.getValue());
        }
    }

    @Test
    @DisplayName("딜러와 플레이어가 동점인 상황에서 딜러만 블랙잭일 경우 플레이어가 패배한다.")
    void 동점_딜러_블랙잭_플레이어_패배_판단() {
        // given
        Dealer dealer = new Dealer();
        Map<String, Integer> playerBets = new HashMap<>();
        playerBets.put("pobi", 1000);
        Players players = new Players(playerBets);

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
            Assertions.assertEquals(MatchResult.LOSE, matchResultEntry.getValue());
        }
    }

    @Test
    @DisplayName("딜러와 플레이어가 둘 다 블랙잭일 경우 무승부이다.")
    void 동점_블랙잭_무승_판단() {
        // given
        Dealer dealer = new Dealer();
        Map<String, Integer> playerBets = new HashMap<>();
        playerBets.put("pobi", 1000);
        Players players = new Players(playerBets);

        BlackJackService blackJackService = new BlackJackService(
                new Deck(new DefaultShuffleStrategy()), dealer, players);

        dealer.hit(new Card(Rank.ACE, Suit.CLOVER));
        dealer.hit(new Card(Rank.KING, Suit.CLOVER));

        for (Player player : players.getPlayers()) {
            player.hit(new Card(Rank.ACE, Suit.HEART));
            player.hit(new Card(Rank.JACK, Suit.CLOVER));
        }

        // when
        Map<String, MatchResult> matchResult = blackJackService.calculateResults();

        // then
        for (Map.Entry<String, MatchResult> matchResultEntry : matchResult.entrySet()) {
            Assertions.assertEquals(MatchResult.DRAW, matchResultEntry.getValue());
        }
    }

    @Test
    @DisplayName("플레이어가 일반 승리한 경우 베팅 금액을 받는다.")
    void 플레이어_승리_베팅결과() {
        // given
        Dealer dealer = new Dealer();
        String name = "pobi";
        Map<String, Integer> playerBets = new HashMap<>();
        int money = 1000;
        playerBets.put(name, money);
        Players players = new Players(playerBets);

        BlackJackService blackJackService = new BlackJackService(
                new Deck(new DefaultShuffleStrategy()), dealer, players);

        dealer.hit(new Card(Rank.JACK, Suit.CLOVER));
        dealer.hit(new Card(Rank.SEVEN, Suit.HEART));

        Player player = players.getPlayers().getFirst();

        player.hit(new Card(Rank.NINE, Suit.HEART));
        player.hit(new Card(Rank.KING, Suit.CLOVER));

        // when
        Map<String, BettingResult> results = blackJackService.calculateBettingResults();

        // then
        Assertions.assertEquals(money, results.get(name).getEarnings());
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 승리한 경우 베팅 금액의 1.5배를 받는다.")
    void 플레이어_블랙잭_승리_베팅결과() {
        // given
        Dealer dealer = new Dealer();

        String name = "pobi";
        int money = 10000;
        Map<String, Integer> playerBets = new HashMap<>();
        playerBets.put(name, money);
        Players players = new Players(playerBets);

        BlackJackService blackJackService = new BlackJackService(
                new Deck(new DefaultShuffleStrategy()), dealer, players);

        dealer.hit(new Card(Rank.JACK, Suit.CLOVER));
        dealer.hit(new Card(Rank.SEVEN, Suit.HEART));

        Player player = players.getPlayers().getFirst();
        player.hit(new Card(Rank.ACE, Suit.CLOVER));
        player.hit(new Card(Rank.KING, Suit.CLOVER));

        // when
        Map<String, BettingResult> results = blackJackService.calculateBettingResults();

        // then
        Assertions.assertEquals(money * (1.5), results.get(name).getEarnings());
    }

    @Test
    @DisplayName("플레이어가 패배한 경우 베팅 금액을 잃는다.")
    void 플레이어_패배_베팅결과() {
        // given
        Dealer dealer = new Dealer();

        String name = "pobi";
        int money = 10000;
        Map<String, Integer> playerBets = new HashMap<>();
        playerBets.put(name, money);
        Players players = new Players(playerBets);

        BlackJackService blackJackService = new BlackJackService(
                new Deck(new DefaultShuffleStrategy()), dealer, players);

        dealer.hit(new Card(Rank.ACE, Suit.CLOVER));
        dealer.hit(new Card(Rank.KING, Suit.CLOVER));

        Player player = players.getPlayers().getFirst();
        player.hit(new Card(Rank.JACK, Suit.CLOVER));
        player.hit(new Card(Rank.SEVEN, Suit.HEART));

        // when
        Map<String, BettingResult> results = blackJackService.calculateBettingResults();

        // then
        Assertions.assertEquals(money * (-1), results.get(name).getEarnings());
    }

    @Test
    @DisplayName("플레이어가 무승부인 경우 베팅 금액을 돌려받는다.")
    void 플레이어_무승부_베팅결과() {
        // given
        Dealer dealer = new Dealer();

        String name = "pobi";
        int money = 10000;
        Map<String, Integer> playerBets = new HashMap<>();
        playerBets.put(name, money);
        Players players = new Players(playerBets);

        BlackJackService blackJackService = new BlackJackService(
                new Deck(new DefaultShuffleStrategy()), dealer, players);

        dealer.hit(new Card(Rank.JACK, Suit.CLOVER));
        dealer.hit(new Card(Rank.SEVEN, Suit.HEART));

        Player player = players.getPlayers().getFirst();
        player.hit(new Card(Rank.NINE, Suit.CLOVER));
        player.hit(new Card(Rank.EIGHT, Suit.CLOVER));

        // when
        Map<String, BettingResult> results = blackJackService.calculateBettingResults();

        // then
        Assertions.assertEquals(0, results.get(name).getEarnings());
    }
}
