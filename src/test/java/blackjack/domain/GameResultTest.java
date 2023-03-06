package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GameResultTest {

    private static Dealer dealer;
    private static List<Player> players;
    private static Player player;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(new ArrayList<>(List.of(new Card(Suit.HEART, Letter.JACK), new Card(Suit.SPADE, Letter.EIGHT))));
        dealer.isAbleToReceive();

        player = new Player("pobi", new ArrayList<>(List.of(new Card(Suit.CLOVER, Letter.FOUR), new Card(Suit.DIAMOND, Letter.SIX))));
        player.isAbleToReceive();
        players = new ArrayList<>();
        players.add(player);
    }

    @Test
    @DisplayName("게임 결과 확인: 버스터 없이 플레이어가 지는 경우")
    void gameResult1() {
        GameResult gameResult = new GameResult(dealer, players);

        assertThat(gameResult.getDealerResults().get(Result.WIN)).isEqualTo(1);
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("게임 결과 확인: 버스터 없이 비기는 경우")
    void gameResult2() {
        player.receiveCard(new Card(Suit.CLOVER, Letter.EIGHT));
        player.isAbleToReceive();

        GameResult gameResult = new GameResult(dealer, players);

        assertThat(gameResult.getDealerResults().get(Result.DRAW)).isEqualTo(1);
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("게임 결과 확인: 버스터 없이 딜러가 지는 경우")
    void gameResult3() {
        player.receiveCard(new Card(Suit.CLOVER, Letter.EIGHT));
        player.receiveCard(new Card(Suit.CLOVER, Letter.TWO));
        player.isAbleToReceive();

        GameResult gameResult = new GameResult(dealer, players);

        assertThat(gameResult.getDealerResults().get(Result.LOSE)).isEqualTo(1);
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("게임 결과 확인: 플레이어만 버스터인 경우")
    void gameResult4() {
        player.receiveCard(new Card(Suit.CLOVER, Letter.EIGHT));
        player.receiveCard(new Card(Suit.CLOVER, Letter.KING));
        player.isAbleToReceive();

        GameResult gameResult = new GameResult(dealer, players);

        assertThat(gameResult.getDealerResults().get(Result.WIN)).isEqualTo(1);
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("게임 결과 확인: 딜러만 버스터인 경우")
    void gameResult5() {
        dealer.receiveCard(new Card(Suit.CLOVER, Letter.SEVEN));
        dealer.isAbleToReceive();

        GameResult gameResult = new GameResult(dealer, players);

        assertThat(gameResult.getDealerResults().get(Result.LOSE)).isEqualTo(1);
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("게임 결과 확인: 둘 다 버스터인 경우")
    void gameResult6() {
        dealer.receiveCard(new Card(Suit.CLOVER, Letter.SEVEN));
        dealer.isAbleToReceive();
        player.receiveCard(new Card(Suit.CLOVER, Letter.EIGHT));
        player.receiveCard(new Card(Suit.CLOVER, Letter.KING));
        player.isAbleToReceive();

        GameResult gameResult = new GameResult(dealer, players);

        assertThat(gameResult.getDealerResults().get(Result.WIN)).isEqualTo(1);
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("게임 결과 확인 : 플레이어 여러명일 때")
    void multiPlayer() {
        Player player2 = new Player("jena", new ArrayList<>(List.of(new Card(Suit.CLOVER, Letter.ACE), new Card(Suit.DIAMOND, Letter.SEVEN))));
        Player player3 = new Player("io", new ArrayList<>(List.of(new Card(Suit.CLOVER, Letter.JACK), new Card(Suit.DIAMOND, Letter.KING))));

        List<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player2);
        players.add(player3);

        players.forEach(Player::isAbleToReceive);
        GameResult gameResult = new GameResult(dealer, players);

        assertThat(gameResult.getDealerResults().get(Result.WIN)).isEqualTo(1);
        assertThat(gameResult.getDealerResults().get(Result.DRAW)).isEqualTo(1);
        assertThat(gameResult.getDealerResults().get(Result.LOSE)).isEqualTo(1);

        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.LOSE);
        assertThat(gameResult.getPlayerResult(player2)).isEqualTo(Result.DRAW);
        assertThat(gameResult.getPlayerResult(player3)).isEqualTo(Result.WIN);
    }

}
