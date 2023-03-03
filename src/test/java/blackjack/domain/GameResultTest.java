package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GameResultTest {

    private static Dealer dealer;
    private static Players players;
    private static Player player;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(new ArrayList<>(List.of(new Card(TrumpShape.HEART, TrumpNumber.JACK), new Card(TrumpShape.SPADE, TrumpNumber.EIGHT))));
        dealer.isAbleToReceive();

        player = new Player("pobi", new ArrayList<>(List.of(new Card(TrumpShape.CLOVER, TrumpNumber.FOUR), new Card(TrumpShape.DIAMOND, TrumpNumber.SIX))));
        players = new Players(List.of(player));
        player.isAbleToReceive();
    }

    @Test
    @DisplayName("게임 결과 확인: 버스터 없이 플레이어가 지는 경우")
    void gameResult1() {
        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.getDealerResults()).isEqualTo(List.of(Result.WIN));
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("게임 결과 확인: 버스터 없이 비기는 경우")
    void gameResult2() {
        player.receiveCard(new Card(TrumpShape.CLOVER, TrumpNumber.EIGHT));
        player.isAbleToReceive();

        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.getDealerResults()).isEqualTo(List.of(Result.DRAW));
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("게임 결과 확인: 버스터 없이 딜러가 지는 경우")
    void gameResult3() {
        player.receiveCard(new Card(TrumpShape.CLOVER, TrumpNumber.EIGHT));
        player.receiveCard(new Card(TrumpShape.CLOVER, TrumpNumber.TWO));
        player.isAbleToReceive();

        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.getDealerResults()).isEqualTo(List.of(Result.LOSE));
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("게임 결과 확인: 플레이어만 버스터인 경우")
    void gameResult4() {
        player.receiveCard(new Card(TrumpShape.CLOVER, TrumpNumber.EIGHT));
        player.receiveCard(new Card(TrumpShape.CLOVER, TrumpNumber.KING));
        player.isAbleToReceive();

        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.getDealerResults()).isEqualTo(List.of(Result.WIN));
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("게임 결과 확인: 딜러만 버스터인 경우")
    void gameResult5() {
        dealer.receiveCard(new Card(TrumpShape.CLOVER, TrumpNumber.SEVEN));
        dealer.isAbleToReceive();

        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.getDealerResults()).isEqualTo(List.of(Result.LOSE));
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("게임 결과 확인: 둘 다 버스터인 경우")
    void gameResult6() {
        dealer.receiveCard(new Card(TrumpShape.CLOVER, TrumpNumber.SEVEN));
        dealer.isAbleToReceive();
        player.receiveCard(new Card(TrumpShape.CLOVER, TrumpNumber.EIGHT));
        player.receiveCard(new Card(TrumpShape.CLOVER, TrumpNumber.KING));
        player.isAbleToReceive();

        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.getDealerResults()).isEqualTo(List.of(Result.WIN));
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("게임 결과 확인 : 플레이어 여러명일 때")
    void multiPlayer() {
        Player player2 = new Player("jena", new ArrayList<>(List.of(new Card(TrumpShape.CLOVER, TrumpNumber.ACE), new Card(TrumpShape.DIAMOND, TrumpNumber.SEVEN))));
        Player player3 = new Player("io", new ArrayList<>(List.of(new Card(TrumpShape.CLOVER, TrumpNumber.JACK), new Card(TrumpShape.DIAMOND, TrumpNumber.KING))));

        players = new Players(List.of(player, player2, player3));
        players.getPlayers().forEach(Player::isAbleToReceive);
        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.getDealerResults()).isEqualTo(List.of(Result.WIN, Result.DRAW, Result.LOSE));
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.LOSE);
        assertThat(gameResult.getPlayerResult(player2)).isEqualTo(Result.DRAW);
        assertThat(gameResult.getPlayerResult(player3)).isEqualTo(Result.WIN);

    }

}
