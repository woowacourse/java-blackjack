package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GameResultTest {

    private static Dealer dealer;
    private static Players players;
    private static Player player;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        dealer.receiveCard(new Card(CardShape.HEART, CardNumber.JACK));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.EIGHT));

        player = new Player("pobi");
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.FOUR));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.SIX));
        players = new Players(List.of(player));
    }

    @Test
    @DisplayName("게임 결과 확인: 버스터 없이 플레이어가 지는 경우")
    void gameResult1() {
        GameResult gameResult = new GameResult(dealer, players.getPlayers());

        assertThat(gameResult.getDealerResults().get(Result.WIN)).isEqualTo(1);
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("게임 결과 확인: 버스터 없이 비기는 경우")
    void gameResult2() {
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.EIGHT));

        GameResult gameResult = new GameResult(dealer, players.getPlayers());

        assertThat(gameResult.getDealerResults().get(Result.DRAW)).isEqualTo(1);
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("게임 결과 확인: 버스터 없이 딜러가 지는 경우")
    void gameResult3() {
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.EIGHT));
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.TWO));

        GameResult gameResult = new GameResult(dealer, players.getPlayers());

        assertThat(gameResult.getDealerResults().get(Result.LOSE)).isEqualTo(1);
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("게임 결과 확인: 플레이어만 버스터인 경우")
    void gameResult4() {
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.EIGHT));
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.KING));

        GameResult gameResult = new GameResult(dealer, players.getPlayers());

        assertThat(gameResult.getDealerResults().get(Result.WIN)).isEqualTo(1);
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("게임 결과 확인: 딜러만 버스터인 경우")
    void gameResult5() {
        dealer.receiveCard(new Card(CardShape.CLOVER, CardNumber.SEVEN));

        GameResult gameResult = new GameResult(dealer, players.getPlayers());

        assertThat(gameResult.getDealerResults().get(Result.LOSE)).isEqualTo(1);
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("게임 결과 확인: 둘 다 버스터인 경우")
    void gameResult6() {
        dealer.receiveCard(new Card(CardShape.CLOVER, CardNumber.SEVEN));
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.EIGHT));
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.KING));

        GameResult gameResult = new GameResult(dealer, players.getPlayers());

        assertThat(gameResult.getDealerResults().get(Result.WIN)).isEqualTo(1);
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("게임 결과 확인 : 플레이어 여러명일 때")
    void multiPlayer() {
        Player player2 = new Player("jena");
        player2.receiveCard(new Card(CardShape.CLOVER, CardNumber.ACE));
        player2.receiveCard(new Card(CardShape.DIAMOND, CardNumber.SEVEN));
        Player player3 = new Player("io");
        player3.receiveCard(new Card(CardShape.CLOVER, CardNumber.JACK));
        player3.receiveCard(new Card(CardShape.DIAMOND, CardNumber.KING));

        players = new Players(List.of(player, player2, player3));
        GameResult gameResult = new GameResult(dealer, players.getPlayers());

        assertThat(gameResult.getDealerResults().get(Result.WIN)).isEqualTo(1);
        assertThat(gameResult.getDealerResults().get(Result.DRAW)).isEqualTo(1);
        assertThat(gameResult.getDealerResults().get(Result.LOSE)).isEqualTo(1);

        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.LOSE);
        assertThat(gameResult.getPlayerResult(player2)).isEqualTo(Result.DRAW);
        assertThat(gameResult.getPlayerResult(player3)).isEqualTo(Result.WIN);
    }
}
