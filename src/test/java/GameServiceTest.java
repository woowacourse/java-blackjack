
import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.GameService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService();
    }

    @Test
    @DisplayName("일반 카드들의 합산 점수를 계산한다")
    void calculateBasicScore() {
        Player player = new Player("test");
        player.receiveCard(Card.CLUB_FIVE);
        player.receiveCard(Card.CLUB_NINE);
        player.receiveCard(Card.CLUB_THREE);

        assertThat(player.calculateScore()).isEqualTo(17);
    }

    @Test
    @DisplayName("페이스 카드가 포함된 점수를 계산한다")
    void calculateScoreWithFaceCards() {
        Player player = new Player("test");
        player.receiveCard(Card.CLUB_ACE);
        player.receiveCard(Card.CLUB_JACK);
        player.receiveCard(Card.CLUB_QUEEN);
        player.receiveCard(Card.CLUB_KING);

        assertThat(player.calculateScore()).isEqualTo(31);
    }

    @Test
    @DisplayName("Ace는 버스트가 나지 않도록 최적값으로 계산된다")
    void calculateScoreWithAceOptimal() {
        Player player = new Player("test");
        player.receiveCard(Card.CLUB_ACE);
        player.receiveCard(Card.CLUB_KING);

        assertThat(player.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace가 11이면 버스트일 때 1로 계산된다")
    void calculateScoreWithAceAvoidsBurst() {
        Player player = new Player("test");
        player.receiveCard(Card.CLUB_ACE);
        player.receiveCard(Card.CLUB_KING);
        player.receiveCard(Card.CLUB_QUEEN);

        assertThat(player.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("처음 카드를 분배받으면 각 게임 참가자들은 패를 2장씩 가지고 있어야 한다.")
    public void when_init_deal_each_must_have_2cards() {
        Dealer dealer = new Dealer();
        Player player1 = new Player("json");
        Player player2 = new Player("poby");
        List<Player> players = List.of(player1, player2);

        gameService.initDeal(players,dealer);
        assertThat(dealer.getHand().size()).isEqualTo(2);
        assertThat(player1.getHand().size()).isEqualTo(2);
        assertThat(player2.getHand().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 최종 승패는 각 플레이어들의 결과를 합쳐서 계산된다.")
    public void calculate_dealer_final_result_by_sum_of_users_result(){
        Dealer dealer = new Dealer();
        Player winPlayer = new Player("json");
        Player losePlayer = new Player("poby");
        Player drawPlayer = new Player("draw");
        List<Player> players = List.of(winPlayer, losePlayer, drawPlayer);

        dealer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));
        winPlayer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_SEVEN));
        losePlayer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_ACE));
        drawPlayer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));

        gameService.determineResult(players, dealer);
        long totalUserWinRounds = players.stream().filter(user -> user.getGameResult() == GameResult.WIN)
                        .count();
        long totalUserLoseRounds = players.stream().filter(user -> user.getGameResult() == GameResult.LOSE)
                        .count();
        long totalUserDrawRounds = players.stream().filter(user -> user.getGameResult() == GameResult.DRAW)
                .count();

        assertThat(dealer.getWinRounds()).isEqualTo(totalUserLoseRounds);
        assertThat(dealer.getLoseRounds()).isEqualTo(totalUserWinRounds);
        assertThat(dealer.getDrawRounds()).isEqualTo(totalUserDrawRounds);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 블랙잭이면 무승부로 수익은 0이다")
    void both_dealer_and_user_blackjack() {
        Player player1 = new Player("json");
        player1.receiveInitCard(List.of(Card.DIAMOND_ACE, Card.DIAMOND_TEN));
        player1.bet(new Amount(10000));

        Player player2 = new Player("park");
        player2.receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_TEN));
        player2.bet(new Amount(5000));

        Dealer dealer = new Dealer();
        dealer.receiveInitCard(List.of(Card.HEART_ACE, Card.HEART_TEN));

        gameService.determineResult(List.of(player1, player2), dealer);

        assertThat(player1.getGameResult()).isEqualTo(GameResult.DRAW);
        assertThat(player2.getGameResult()).isEqualTo(GameResult.DRAW);
        assertThat(player1.calculateProfit()).isEqualTo(0);
        assertThat(player2.calculateProfit()).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러만 블랙잭이면 플레이어는 패배하고 배팅금을 잃는다")
    void only_dealer_blackjack() {
        Player player1 = new Player("json");
        player1.receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_NINE));
        player1.bet(new Amount(10000));

        Player player2 = new Player("park");
        player2.receiveInitCard(List.of(Card.SPADE_SEVEN, Card.CLUB_SEVEN, Card.HEART_SEVEN));
        player2.bet(new Amount(5000));

        Dealer dealer = new Dealer();
        dealer.receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_TEN));

        gameService.determineResult(List.of(player1, player2), dealer);

        assertThat(player1.getGameResult()).isEqualTo(GameResult.LOSE);
        assertThat(player2.getGameResult()).isEqualTo(GameResult.LOSE);
        assertThat(player1.calculateProfit()).isEqualTo(-10000);
        assertThat(player2.calculateProfit()).isEqualTo(-5000);
    }


    @Test
    @DisplayName("플레이어만 블랙잭이면 배팅금의 1.5배를 받는다")
    void only_user_blackjack() {
        Player player1 = new Player("json");
        player1.receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_TEN));
        player1.bet(new Amount(10000));

        Player player2 = new Player("json");
        player2.receiveInitCard(List.of(Card.HEART_ACE, Card.HEART_FIVE));
        player2.bet(new Amount(5000));

        Dealer dealer = new Dealer();
        dealer.receiveInitCard(List.of(Card.SPADE_NINE, Card.SPADE_TEN));

        gameService.determineResult(List.of(player1, player2), dealer);

        assertThat(player1.getGameResult()).isEqualTo(GameResult.WIN);
        assertThat(player2.getGameResult()).isEqualTo(GameResult.LOSE);
        assertThat(player1.calculateProfit()).isEqualTo(15000);
        assertThat(player2.calculateProfit()).isEqualTo(-5000);
    }
}
