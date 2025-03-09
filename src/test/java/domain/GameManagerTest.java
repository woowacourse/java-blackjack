package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CardGroup;
import domain.card.RandomCardGenerator;
import domain.fake.FaceCardGenerator;
import domain.fake.TwoScoreCardGenerator;
import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameManagerTest {

    @Test
    void 게임_매니저를_생성한다() {
        //given
        CardGroup cardGroup = new CardGroup();
        RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        final List<Player> players = List.of(
                new Player("윌슨", cardGroup, randomCardGenerator),
                new Player("가이온", cardGroup, randomCardGenerator));
        Dealer dealer = new Dealer(cardGroup, randomCardGenerator);

        //when
        final GameManager gameManager = new GameManager(dealer, players);
        //then
        assertThat(gameManager).isInstanceOf(GameManager.class);
    }

    @DisplayName("플레이어 20점 vs 딜러 18점")
    @Test
    void 플레이어가_버스트_하지_않고_딜러보다_점수가_높아야_승리() {
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup2 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();

        final List<Player> players = List.of(
                new Player("윌슨", cardGroup1, new FaceCardGenerator()),
                new Player("가이온", cardGroup2, new FaceCardGenerator()));
        final Dealer dealer = new Dealer(cardGroup3, new TwoScoreCardGenerator());

        //when
        final GameManager gameManager = new GameManager(dealer, players);
        gameManager.initOpeningCards();
        gameManager.dealerHitUntilStand();
        final Map<String, GameResult> playerGameResult = gameManager.calculatePlayerGameResult();

        //then
        assertThat(playerGameResult).containsEntry(players.getFirst().getName(), GameResult.WIN);
        assertThat(playerGameResult).containsEntry(players.get(1).getName(), GameResult.WIN);

    }

    @DisplayName("Face 카드 3장을 받아 30점의 경우 버스트하여 패배")
    @Test
    void 플레이어가_버스트_하는_경우_무조건_패배() {
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup2 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();
        final Player player1 = new Player("윌슨", cardGroup1, new FaceCardGenerator());
        final Player player2 = new Player("가이온", cardGroup2, new FaceCardGenerator());

        final List<Player> players = List.of(player1, player2);
        final Dealer dealer = new Dealer(cardGroup3, new TwoScoreCardGenerator());

        //when
        final GameManager gameManager = new GameManager(dealer, players);
        player1.receiveCard(3);
        player2.receiveCard(3);
        final Map<String, GameResult> playerGameResult = gameManager.calculatePlayerGameResult();

        //then
        assertThat(playerGameResult).containsEntry(players.getFirst().getName(), GameResult.LOSE);
        assertThat(playerGameResult).containsEntry(players.get(1).getName(), GameResult.LOSE);
    }

    @Test
    void 딜러와_플레이어의_점수가_같은_경우_무승부() {
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup2 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();

        final List<Player> players = List.of(
                new Player("윌슨", cardGroup1, new FaceCardGenerator()),
                new Player("가이온", cardGroup2, new FaceCardGenerator()));
        final Dealer dealer = new Dealer(cardGroup3, new FaceCardGenerator());

        //when
        final GameManager gameManager = new GameManager(dealer, players);
        final Map<String, GameResult> playerGameResult = gameManager.calculatePlayerGameResult();

        //then
        assertThat(playerGameResult).containsEntry(players.getFirst().getName(), GameResult.DRAW);
        assertThat(playerGameResult).containsEntry(players.get(1).getName(), GameResult.DRAW);
    }

    @Test
    void 딜러와_플레이어가_서로_버스트한_경우_무승부() {
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup2 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();
        final Player player1 = new Player("윌슨", cardGroup1, new FaceCardGenerator());
        final Player player2 = new Player("가이온", cardGroup2, new FaceCardGenerator());

        final List<Player> players = List.of(player1, player2);
        final Dealer dealer = new Dealer(cardGroup3, new FaceCardGenerator());

        //when
        final GameManager gameManager = new GameManager(dealer, players);
        player1.receiveCard(1);
        player2.receiveCard(1);
        dealer.receiveCard(1);
        final Map<String, GameResult> playerGameResult = gameManager.calculatePlayerGameResult();

        //then
        assertThat(playerGameResult).containsEntry(players.getFirst().getName(), GameResult.DRAW);
        assertThat(playerGameResult).containsEntry(players.get(1).getName(), GameResult.DRAW);
    }

    @Test
    void 딜러의_게임_결과를_계산한다() {
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup2 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();

        final List<Player> players = List.of(
                new Player("윌슨", cardGroup1, new TwoScoreCardGenerator()),
                new Player("가이온", cardGroup2, new TwoScoreCardGenerator()));
        final Dealer dealer = new Dealer(cardGroup3, new FaceCardGenerator());

        //when
        final GameManager gameManager = new GameManager(dealer, players);
        gameManager.initOpeningCards();
        final Map<GameResult, Integer> result = gameManager.calculateDealerGameResult();

        //then
        assertThat(result).containsEntry(GameResult.WIN, 2);
    }

    @Test
    void 플레이어의_게임_결과를_계산한다() {
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup2 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();

        final List<Player> players = List.of(
                new Player("윌슨", cardGroup1, new TwoScoreCardGenerator()),
                new Player("가이온", cardGroup2, new TwoScoreCardGenerator()));
        final Dealer dealer = new Dealer(cardGroup3, new FaceCardGenerator());

        //when
        final GameManager gameManager = new GameManager(dealer, players);
        gameManager.initOpeningCards();
        final Map<String, GameResult> result = gameManager.calculatePlayerGameResult();

        //then
        assertThat(result).containsEntry("윌슨", GameResult.LOSE).containsEntry("가이온", GameResult.LOSE);
    }
}
