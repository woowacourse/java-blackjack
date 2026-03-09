package service;

import domain.MatchResult;
import domain.card.Card;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import dto.GameResultDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GameManagerTest {

    @Test
    @DisplayName("딜러와 모든 플레이어는 게임 시작 시, 2장의 카드를 받는다.")
    void 게임_시작시_2장_카드() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi", "james"));

        GameManager gameManager = new GameManager(dealer, players);


        assertThrows(IllegalStateException.class, () -> dealer.getCards().size());
        for (Player player : players.getPlayers()) {
            assertThrows(IllegalStateException.class, () -> player.getCards().size());
        }

        // when
        gameManager.initHands(new Deck());

        // then
        Assertions.assertEquals(dealer.getCards().size(), 2);
        for (Player player : players.getPlayers()) {
            Assertions.assertEquals(player.getCards().size(), 2);
        }
    }

    @Test
    @DisplayName("플레이어가 딜러를 이긴 경우 승리한다.")
    void 플레이어_승_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        GameManager gameManager = new GameManager(dealer, players);

        dealer.receive(new Card(Rank.JACK, Suit.CLOVER));
        dealer.receive(new Card(Rank.SEVEN, Suit.HEART));

        for (Player player : players.getPlayers()) {
            player.receive(new Card(Rank.ACE, Suit.CLOVER));
            player.receive(new Card(Rank.KING, Suit.CLOVER));
        }

        // when
        GameResultDto gameResultDto = gameManager.calculateResults();
        Map<String, MatchResult> playerResult = gameResultDto.getPlayersResult();

        // then
        for (Map.Entry<String, MatchResult> matchResultEntry : playerResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.WIN);
        }
    }

    @Test
    @DisplayName("플레이어가 딜러와 비긴 경우 무승부한다.")
    void 플레이어_무승부_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        GameManager gameManager = new GameManager(dealer, players);

        dealer.receive(new Card(Rank.JACK, Suit.CLOVER));
        dealer.receive(new Card(Rank.SEVEN, Suit.HEART));

        for (Player player : players.getPlayers()) {
            player.receive(new Card(Rank.SEVEN, Suit.CLOVER));
            player.receive(new Card(Rank.KING, Suit.CLOVER));
        }

        // when
        GameResultDto gameResultDto = gameManager.calculateResults();
        Map<String, MatchResult> playerResult = gameResultDto.getPlayersResult();

        // then
        for (Map.Entry<String, MatchResult> matchResultEntry : playerResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.DRAW);
        }
    }

    @Test
    @DisplayName("플레이어가 딜러에게 진 경우 패배한다.")
    void 플레이어_패_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        GameManager gameManager = new GameManager(dealer, players);

        dealer.receive(new Card(Rank.ACE, Suit.CLOVER));
        dealer.receive(new Card(Rank.KING, Suit.CLOVER));

        for (Player player : players.getPlayers()) {
            player.receive(new Card(Rank.JACK, Suit.CLOVER));
            player.receive(new Card(Rank.SEVEN, Suit.HEART));
        }

        // when
        GameResultDto gameResultDto = gameManager.calculateResults();
        Map<String, MatchResult> playerResult = gameResultDto.getPlayersResult();
        // then
        for (Map.Entry<String, MatchResult> matchResultEntry : playerResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.LOSE);
        }
    }

    @Test
    @DisplayName("플레이어만 버스트일 경우 바로 패배한다.")
    void 플레이어만_버스트_패_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        GameManager gameManager = new GameManager(dealer, players);

        dealer.receive(new Card(Rank.ACE, Suit.CLOVER));
        dealer.receive(new Card(Rank.KING, Suit.CLOVER));

        for (Player player : players.getPlayers()) {
            player.receive(new Card(Rank.JACK, Suit.CLOVER));
            player.receive(new Card(Rank.SEVEN, Suit.HEART));
            player.receive(new Card(Rank.JACK, Suit.HEART));
        }

        // when
        GameResultDto gameResultDto = gameManager.calculateResults();
        Map<String, MatchResult> playerResult = gameResultDto.getPlayersResult();

        // then
        for (Map.Entry<String, MatchResult> matchResultEntry : playerResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.LOSE);
        }
    }

    @Test
    @DisplayName("딜러만 버스트일 경우 플레이어가 승리한다.")
    void 딜러만_버스트_플레이어_패_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        GameManager gameManager = new GameManager(dealer, players);

        dealer.receive(new Card(Rank.JACK, Suit.CLOVER));
        dealer.receive(new Card(Rank.SEVEN, Suit.HEART));
        dealer.receive(new Card(Rank.JACK, Suit.HEART));

        for (Player player : players.getPlayers()) {
            player.receive(new Card(Rank.ACE, Suit.CLOVER));
            player.receive(new Card(Rank.KING, Suit.CLOVER));
        }

        // when
        GameResultDto gameResultDto = gameManager.calculateResults();
        Map<String, MatchResult> playerResult = gameResultDto.getPlayersResult();

        // then
        for (Map.Entry<String, MatchResult> matchResultEntry : playerResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.WIN);
        }
    }

    @Test
    @DisplayName("딜러와 플레이어가 동점인 상황에서 플레이어만 블랙잭일 경우 플레이어가 승리한다.")
    void 동점_플레이어_블랙잭_승리_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        GameManager gameManager = new GameManager(dealer, players);

        dealer.receive(new Card(Rank.JACK, Suit.CLOVER));
        dealer.receive(new Card(Rank.ACE, Suit.HEART));
        dealer.receive(new Card(Rank.KING, Suit.HEART));

        for (Player player : players.getPlayers()) {
            player.receive(new Card(Rank.ACE, Suit.CLOVER));
            player.receive(new Card(Rank.KING, Suit.CLOVER));
        }

        // when
        GameResultDto gameResultDto = gameManager.calculateResults();
        Map<String, MatchResult> playerResult = gameResultDto.getPlayersResult();

        // then
        for (Map.Entry<String, MatchResult> matchResultEntry : playerResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.WIN);
        }
    }

    @Test
    @DisplayName("딜러와 플레이어가 동점인 상황에서 딜러만 블랙잭일 경우 플레이어가 패배한다.")
    void 동점_딜러_블랙잭_플레이어_패배_판단() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        GameManager gameManager = new GameManager(dealer, players);

        dealer.receive(new Card(Rank.ACE, Suit.CLOVER));
        dealer.receive(new Card(Rank.KING, Suit.CLOVER));

        for (Player player : players.getPlayers()) {
            player.receive(new Card(Rank.JACK, Suit.CLOVER));
            player.receive(new Card(Rank.ACE, Suit.HEART));
            player.receive(new Card(Rank.KING, Suit.HEART));
        }

        // when
        GameResultDto gameResultDto = gameManager.calculateResults();
        Map<String, MatchResult> playerResult = gameResultDto.getPlayersResult();

        // then
        for (Map.Entry<String, MatchResult> matchResultEntry : playerResult.entrySet()) {
            Assertions.assertEquals(matchResultEntry.getValue(), MatchResult.LOSE);
        }
    }
}
