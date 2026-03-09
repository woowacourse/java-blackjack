package domain.participant;

import domain.MatchResult;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class PlayersTest {

    @Test
    @DisplayName("플레이어 인원 수는 5명 이하여야 합니다.")
    void 플레이어인원수_5명이하_성공() {
        // given
        List<Player> players = List.of(new Player("pobi"), new Player("james"));

        // when - then
        Assertions.assertDoesNotThrow(() -> new Players(players));
    }

    @Test
    @DisplayName("플레이어 인원 수는 6명 이상인 경우 오류가 발생해야 한다.")
    void 플레이어인원수_6명이상_오류() {
        // given
        List<Player> players = List.of(
                new Player("pobi")
                , new Player("james")
                , new Player("eunoh")
                , new Player("ruro")
                , new Player("rama")
                , new Player("dudu"));

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Players(players));
    }

    @Test
    @DisplayName("플레이어 이름이 중복되는 경우 오류가 발생해야 한다.")
    void 플레이어_이름_중복_금지() {
        // given
        List<Player> players = List.of(
                new Player("pobi")
                , new Player("james")
                , new Player("pobi"));

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Players(players));
    }

    @Test
    @DisplayName("플레이어를 정확하게 찾는지 검증한다.")
    void findByTest() {
        // given
        Player targetPlayer = new Player("pobi");
        List<Player> playerNames = List.of(
                targetPlayer
                , new Player("james"));

        // when - then
        Players players = new Players(playerNames);

        Assertions.assertEquals(players.findBy(targetPlayer), targetPlayer);
    }

    @Test
    @DisplayName("플레이어만 버스트일 경우 바로 패배한다.")
    void 플레이어만_버스트_패_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(new Player("pobi")));

        dealer.receive(new Card(Rank.ACE, Suit.CLOVER));
        dealer.receive(new Card(Rank.KING, Suit.CLOVER));

        for (Player player : players.getPlayers()) {
            player.receive(new Card(Rank.JACK, Suit.CLOVER));
            player.receive(new Card(Rank.SEVEN, Suit.HEART));
            player.receive(new Card(Rank.JACK, Suit.HEART));
        }

        // when
        Map<Player, MatchResult> playerResult = players.calculateResult(dealer);

        // then
        for (Map.Entry<Player, MatchResult> matchResultEntry : playerResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.LOSE);
        }
    }

    @Test
    @DisplayName("딜러만 버스트일 경우 플레이어가 승리한다.")
    void 딜러만_버스트_플레이어_패_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(new Player("pobi")));

        dealer.receive(new Card(Rank.JACK, Suit.CLOVER));
        dealer.receive(new Card(Rank.SEVEN, Suit.HEART));
        dealer.receive(new Card(Rank.JACK, Suit.HEART));

        for (Player player : players.getPlayers()) {
            player.receive(new Card(Rank.ACE, Suit.CLOVER));
            player.receive(new Card(Rank.KING, Suit.CLOVER));
        }

        // when
        Map<Player, MatchResult> playerResult = players.calculateResult(dealer);

        // then
        for (Map.Entry<Player, MatchResult> matchResultEntry : playerResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.WIN);
        }
    }

    @Test
    @DisplayName("플레이어가 딜러를 이긴 경우 승리한다.")
    void 플레이어_승_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(new Player("pobi")));

        dealer.receive(new Card(Rank.JACK, Suit.CLOVER));
        dealer.receive(new Card(Rank.SEVEN, Suit.HEART));

        for (Player player : players.getPlayers()) {
            player.receive(new Card(Rank.ACE, Suit.CLOVER));
            player.receive(new Card(Rank.KING, Suit.CLOVER));
        }

        // when
        Map<Player, MatchResult> playerResult = players.calculateResult(dealer);

        // then
        for (Map.Entry<Player, MatchResult> matchResultEntry : playerResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.WIN);
        }
    }

    @Test
    @DisplayName("플레이어가 딜러와 비긴 경우 무승부한다.")
    void 플레이어_무승부_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(new Player("pobi")));

        dealer.receive(new Card(Rank.JACK, Suit.CLOVER));
        dealer.receive(new Card(Rank.SEVEN, Suit.HEART));

        for (Player player : players.getPlayers()) {
            player.receive(new Card(Rank.SEVEN, Suit.CLOVER));
            player.receive(new Card(Rank.KING, Suit.CLOVER));
        }

        // when
        Map<Player, MatchResult> playerResult = players.calculateResult(dealer);

        // then
        for (Map.Entry<Player, MatchResult> matchResultEntry : playerResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.DRAW);
        }
    }

    @Test
    @DisplayName("딜러와 플레이어가 동점인 상황에서 플레이어만 블랙잭일 경우 플레이어가 승리한다.")
    void 동점_플레이어_블랙잭_승리_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(new Player("pobi")));

        dealer.receive(new Card(Rank.JACK, Suit.CLOVER));
        dealer.receive(new Card(Rank.ACE, Suit.HEART));
        dealer.receive(new Card(Rank.KING, Suit.HEART));

        for (Player player : players.getPlayers()) {
            player.receive(new Card(Rank.ACE, Suit.CLOVER));
            player.receive(new Card(Rank.KING, Suit.CLOVER));
        }

        // when
        Map<Player, MatchResult> playerResult = players.calculateResult(dealer);

        // then
        for (Map.Entry<Player, MatchResult> matchResultEntry : playerResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.WIN);
        }
    }

    @Test
    @DisplayName("딜러와 플레이어가 동점인 상황에서 딜러만 블랙잭일 경우 플레이어가 패배한다.")
    void 동점_딜러_블랙잭_플레이어_패배_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(new Player("pobi")));

        dealer.receive(new Card(Rank.ACE, Suit.CLOVER));
        dealer.receive(new Card(Rank.KING, Suit.CLOVER));

        for (Player player : players.getPlayers()) {
            player.receive(new Card(Rank.JACK, Suit.CLOVER));
            player.receive(new Card(Rank.ACE, Suit.HEART));
            player.receive(new Card(Rank.KING, Suit.HEART));
        }

        // when
        Map<Player, MatchResult> playerResult = players.calculateResult(dealer);

        // then
        for (Map.Entry<Player, MatchResult> matchResultEntry : playerResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.LOSE);
        }
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 블랙잭인 경우 무승부 처리된다.")
    void 동점_딜러_플레이어_모두_블랙잭_무승부_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(new Player("pobi")));

        dealer.receive(new Card(Rank.ACE, Suit.CLOVER));
        dealer.receive(new Card(Rank.KING, Suit.CLOVER));

        for (Player player : players.getPlayers()) {
            player.receive(new Card(Rank.JACK, Suit.CLOVER));
            player.receive(new Card(Rank.ACE, Suit.HEART));
        }

        // when
        Map<Player, MatchResult> playerResult = players.calculateResult(dealer);

        // then
        for (Map.Entry<Player, MatchResult> matchResultEntry : playerResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.DRAW);
        }
    }

    @Test
    @DisplayName("플레이어가 딜러에게 진 경우 패배한다.")
    void 플레이어_패_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(new Player("pobi")));

        dealer.receive(new Card(Rank.JACK, Suit.CLOVER));
        dealer.receive(new Card(Rank.KING, Suit.CLOVER));

        for (Player player : players.getPlayers()) {
            player.receive(new Card(Rank.JACK, Suit.CLOVER));
            player.receive(new Card(Rank.SEVEN, Suit.HEART));
        }

        // when
        Map<Player, MatchResult> playerResult = players.calculateResult(dealer);

        // then
        for (Map.Entry<Player, MatchResult> matchResultEntry : playerResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.LOSE);
        }
    }
}
