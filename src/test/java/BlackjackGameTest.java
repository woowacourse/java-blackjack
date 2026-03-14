import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BlackjackGameTest {

    @Test
    @DisplayName("일반 카드들의 합산 점수를 계산한다")
    void calculateBasicScore() {
        Player player = new Player("test", new Amount(10000));
        player.receiveCard(Card.CLUB_FIVE);
        player.receiveCard(Card.CLUB_NINE);
        player.receiveCard(Card.CLUB_THREE);

        assertThat(player.calculateScore()).isEqualTo(17);
    }

    @Test
    @DisplayName("페이스 카드가 포함된 점수를 계산한다")
    void calculateScoreWithFaceCards() {
        Player player = new Player("test", new Amount(10000));
        player.receiveCard(Card.CLUB_ACE);
        player.receiveCard(Card.CLUB_JACK);
        player.receiveCard(Card.CLUB_QUEEN);
        player.receiveCard(Card.CLUB_KING);

        assertThat(player.calculateScore()).isEqualTo(31);
    }

    @Test
    @DisplayName("Ace는 버스트가 나지 않도록 최적값으로 계산된다")
    void calculateScoreWithAceOptimal() {
        Player player = new Player("test", new Amount(10000));
        player.receiveCard(Card.CLUB_ACE);
        player.receiveCard(Card.CLUB_KING);

        assertThat(player.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace가 11이면 버스트일 때 1로 계산된다")
    void calculateScoreWithAceAvoidsBurst() {
        Player player = new Player("test", new Amount(10000));
        player.receiveCard(Card.CLUB_ACE);
        player.receiveCard(Card.CLUB_KING);
        player.receiveCard(Card.CLUB_QUEEN);

        assertThat(player.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("처음 카드를 분배받으면 각 게임 참가자들은 패를 2장씩 가지고 있어야 한다.")
    void when_init_deal_each_must_have_2cards() {
        Player player1 = new Player("json", new Amount(10000));
        Player player2 = new Player("poby", new Amount(10000));
        Players players = new Players(List.of(player1, player2));

        BlackjackGame game = new BlackjackGame(players);
        game.initDeal();

        assertThat(game.getDealer().getHand().size()).isEqualTo(2);
        assertThat(player1.getHand().size()).isEqualTo(2);
        assertThat(player2.getHand().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 최종 승패는 각 플레이어들의 결과를 합쳐서 계산된다.")
    void calculate_dealer_final_result_by_sum_of_users_result() {
        Player winPlayer = new Player("json", new Amount(10000));
        Player losePlayer = new Player("poby", new Amount(10000));
        Player drawPlayer = new Player("draw", new Amount(10000));
        Players players = new Players(List.of(winPlayer, losePlayer, drawPlayer));

        BlackjackGame game = new BlackjackGame(players);

        game.getDealer().receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));
        winPlayer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_SEVEN));
        losePlayer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_ACE));
        drawPlayer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));

        game.determineResult();

        long totalUserWinRounds = players.getPlayers().stream()
                .filter(player -> player.getGameResult() == GameResult.WIN)
                .count();
        long totalUserLoseRounds = players.getPlayers().stream()
                .filter(player -> player.getGameResult() == GameResult.LOSE)
                .count();
        long totalUserDrawRounds = players.getPlayers().stream()
                .filter(player -> player.getGameResult() == GameResult.DRAW)
                .count();

        assertThat(game.getDealer().getWinRounds()).isEqualTo(totalUserLoseRounds);
        assertThat(game.getDealer().getLoseRounds()).isEqualTo(totalUserWinRounds);
        assertThat(game.getDealer().getDrawRounds()).isEqualTo(totalUserDrawRounds);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 블랙잭이면 무승부로 수익은 0이다")
    void both_dealer_and_user_blackjack() {
        Player player1 = new Player("json", new Amount(10000));
        player1.receiveInitCard(List.of(Card.DIAMOND_ACE, Card.DIAMOND_TEN));

        Player player2 = new Player("park", new Amount(10000));
        player2.receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_TEN));

        Players players = new Players(List.of(player1, player2));
        BlackjackGame game = new BlackjackGame(players);

        game.getDealer().receiveInitCard(List.of(Card.HEART_ACE, Card.HEART_TEN));
        game.determineResult();

        assertThat(player1.getGameResult()).isEqualTo(GameResult.DRAW);
        assertThat(player2.getGameResult()).isEqualTo(GameResult.DRAW);
        assertThat(player1.calculateProfit()).isEqualTo(0);
        assertThat(player2.calculateProfit()).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러만 블랙잭이면 플레이어는 패배하고 배팅금을 잃는다")
    void only_dealer_blackjack() {
        Player player1 = new Player("json", new Amount(10000));
        player1.receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_NINE));

        Player player2 = new Player("park", new Amount(5000));
        player2.receiveInitCard(List.of(Card.SPADE_SEVEN, Card.CLUB_SEVEN, Card.HEART_SEVEN));

        Players players = new Players(List.of(player1, player2));
        BlackjackGame game = new BlackjackGame(players);

        game.getDealer().receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_TEN));
        game.determineResult();

        assertThat(player1.getGameResult()).isEqualTo(GameResult.LOSE);
        assertThat(player2.getGameResult()).isEqualTo(GameResult.LOSE);
        assertThat(player1.calculateProfit()).isEqualTo(-10000);
        assertThat(player2.calculateProfit()).isEqualTo(-5000);
    }

    @Test
    @DisplayName("플레이어만 블랙잭이면 배팅금의 1.5배를 받는다")
    void only_user_blackjack() {
        Player player1 = new Player("json", new Amount(10000));
        player1.receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_TEN));

        Player player2 = new Player("park", new Amount(5000));
        player2.receiveInitCard(List.of(Card.HEART_ACE, Card.HEART_FIVE));

        Players players = new Players(List.of(player1, player2));
        BlackjackGame game = new BlackjackGame(players);

        game.getDealer().receiveInitCard(List.of(Card.SPADE_NINE, Card.SPADE_TEN));
        game.determineResult();

        assertThat(player1.getGameResult()).isEqualTo(GameResult.WIN);
        assertThat(player2.getGameResult()).isEqualTo(GameResult.LOSE);
        assertThat(player1.calculateProfit()).isEqualTo(15000);
        assertThat(player2.calculateProfit()).isEqualTo(-5000);
    }
}
