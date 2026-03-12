
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
        User user = new User("test");
        user.receiveCard(Card.CLUB_FIVE);
        user.receiveCard(Card.CLUB_NINE);
        user.receiveCard(Card.CLUB_THREE);

        assertThat(user.calculateScore()).isEqualTo(17);
    }

    @Test
    @DisplayName("페이스 카드가 포함된 점수를 계산한다")
    void calculateScoreWithFaceCards() {
        User user = new User("test");
        user.receiveCard(Card.CLUB_ACE);
        user.receiveCard(Card.CLUB_JACK);
        user.receiveCard(Card.CLUB_QUEEN);
        user.receiveCard(Card.CLUB_KING);

        assertThat(user.calculateScore()).isEqualTo(31);
    }

    @Test
    @DisplayName("Ace는 버스트가 나지 않도록 최적값으로 계산된다")
    void calculateScoreWithAceOptimal() {
        User user = new User("test");
        user.receiveCard(Card.CLUB_ACE);
        user.receiveCard(Card.CLUB_KING);

        assertThat(user.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace가 11이면 버스트일 때 1로 계산된다")
    void calculateScoreWithAceAvoidsBurst() {
        User user = new User("test");
        user.receiveCard(Card.CLUB_ACE);
        user.receiveCard(Card.CLUB_KING);
        user.receiveCard(Card.CLUB_QUEEN);

        assertThat(user.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("처음 카드를 분배받으면 각 사람들이 패에 2장씩 가지고 있어야 한다.")
    public void when_init_deal_each_must_have_2cards() {
        Dealer dealer = new Dealer();
        User user1 = new User("json");
        User user2 = new User("poby");
        List<User> users = List.of(user1,user2);

        gameService.initDeal(users,dealer);
        assertThat(dealer.getHand().size()).isEqualTo(2);
        assertThat(user1.getHand().size()).isEqualTo(2);
        assertThat(user2.getHand().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 최종 승패는 각 사용자들의 결과를 합쳐서 계산된다.")
    public void calculate_dealer_final_result_by_sum_of_users_result(){
        Dealer dealer = new Dealer();
        User winUser = new User("json");
        User loseUser = new User("poby");
        User drawUser = new User("draw");
        List<User> users = List.of(winUser, loseUser, drawUser);

        dealer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));
        winUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_SEVEN));
        loseUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_ACE));
        drawUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));

        gameService.determineResult(users, dealer);
        long totalUserWinRounds = users.stream().filter(user -> user.getGameResult() == GameResult.WIN)
                        .count();
        long totalUserLoseRounds = users.stream().filter(user -> user.getGameResult() == GameResult.LOSE)
                        .count();
        long totalUserDrawRounds = users.stream().filter(user -> user.getGameResult() == GameResult.DRAW)
                .count();

        assertThat(dealer.getWinRounds()).isEqualTo(totalUserLoseRounds);
        assertThat(dealer.getLoseRounds()).isEqualTo(totalUserWinRounds);
        assertThat(dealer.getDrawRounds()).isEqualTo(totalUserDrawRounds);
    }

    @Test
    @DisplayName("딜러와 유저 모두 블랙잭이면 무승부로 수익은 0이다")
    void both_dealer_and_user_blackjack() {
        User user1 = new User("json");
        user1.receiveInitCard(List.of(Card.DIAMOND_ACE, Card.DIAMOND_TEN));
        user1.bet(new Amount(10000));

        User user2 = new User("park");
        user2.receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_TEN));
        user2.bet(new Amount(5000));

        Dealer dealer = new Dealer();
        dealer.receiveInitCard(List.of(Card.HEART_ACE, Card.HEART_TEN));

        gameService.determineResult(List.of(user1, user2), dealer);

        assertThat(user1.getGameResult()).isEqualTo(GameResult.DRAW);
        assertThat(user2.getGameResult()).isEqualTo(GameResult.DRAW);
        assertThat(user1.calculateProfit()).isEqualTo(0);
        assertThat(user2.calculateProfit()).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러만 블랙잭이면 유저는 패배하고 배팅금을 잃는다")
    void only_dealer_blackjack() {
        User user1 = new User("json");
        user1.receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_NINE));
        user1.bet(new Amount(10000));

        User user2 = new User("park");
        user2.receiveInitCard(List.of(Card.SPADE_SEVEN, Card.CLUB_SEVEN, Card.HEART_SEVEN));
        user2.bet(new Amount(5000));

        Dealer dealer = new Dealer();
        dealer.receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_TEN));

        gameService.determineResult(List.of(user1, user2), dealer);

        assertThat(user1.getGameResult()).isEqualTo(GameResult.LOSE);
        assertThat(user2.getGameResult()).isEqualTo(GameResult.LOSE);
        assertThat(user1.calculateProfit()).isEqualTo(-10000);
        assertThat(user2.calculateProfit()).isEqualTo(-5000);
    }


    @Test
    @DisplayName("유저만 블랙잭이면 배팅금의 1.5배를 받는다")
    void only_user_blackjack() {
        User user1 = new User("json");
        user1.receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_TEN));
        user1.bet(new Amount(10000));

        User user2 = new User("json");
        user2.receiveInitCard(List.of(Card.HEART_ACE, Card.HEART_FIVE));
        user2.bet(new Amount(5000));

        Dealer dealer = new Dealer();
        dealer.receiveInitCard(List.of(Card.SPADE_NINE, Card.SPADE_TEN));

        gameService.determineResult(List.of(user1, user2), dealer);

        assertThat(user1.getGameResult()).isEqualTo(GameResult.WIN);
        assertThat(user2.getGameResult()).isEqualTo(GameResult.LOSE);
        assertThat(user1.calculateProfit()).isEqualTo(15000);
        assertThat(user2.calculateProfit()).isEqualTo(-5000);
    }
}
