import domain.Card;
import domain.Dealer;
import domain.DealerProfit;
import domain.GameResult;
import domain.RandomShuffle;
import domain.ShuffleStrategy;
import domain.User;
import domain.UserProfit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.BettingRule;
import strategy.DefaultBettingRule;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    void setUp() {
        ShuffleStrategy strategy = new RandomShuffle();
        BettingRule bettingRule = new DefaultBettingRule();
        gameService = new GameService(strategy, bettingRule);
    }

    @Test
    @DisplayName("처음 카드를 분배받으면 각 사람들이 패에 2장씩 가지고 있어야 한다.")
    public void when_init_deal_each_must_have_2cards() {
        Dealer dealer = new Dealer();
        User user1 = User.from("json");
        User user2 = User.from("poby");
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
        User winUser = User.from("json");
        User loseUser = User.from("poby");
        User drawUser = User.from("draw");
        List<User> users = List.of(winUser, loseUser, drawUser);

        dealer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));
        winUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_SEVEN));
        loseUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_ACE));
        drawUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));

        List<UserProfit> userProfits = gameService.settleResult(users, dealer);
        long totalUserWinRounds = userProfits.stream().filter(up -> up.gameResult() == GameResult.WIN)
                        .count();
        long totalUserLoseRounds = userProfits.stream().filter(up -> up.gameResult() == GameResult.LOSE)
                        .count();
        long totalUserDrawRounds = userProfits.stream().filter(up -> up.gameResult() == GameResult.DRAW)
                .count();

        assertThat(dealer.getWinRounds()).isEqualTo(totalUserLoseRounds);
        assertThat(dealer.getLoseRounds()).isEqualTo(totalUserWinRounds);
        assertThat(dealer.getDrawRounds()).isEqualTo(totalUserDrawRounds);
    }

    @Test
    @DisplayName("게임 승패에 따라 수익을 계산한다.")
    public void calculate_user_profit_by_gameresult() {
        Dealer dealer = new Dealer();
        User winUser = User.from("json", 10000);
        User loseUser = User.from("poby", 20000);
        User drawUser = User.from("draw", 30000);
        List<User> users = List.of(winUser, loseUser, drawUser);

        dealer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));
        winUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_ACE));
        loseUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_SEVEN));
        drawUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));

        List<UserProfit> userProfits = gameService.settleResult(users, dealer);
        UserProfit winUserProfit = userProfits.stream().filter(up -> up.userName().equals("json")).findFirst().get();
        UserProfit drawUserProfit = userProfits.stream().filter(up -> up.userName().equals("draw")).findFirst().get();
        UserProfit loseUserProfit = userProfits.stream().filter(up -> up.userName().equals("poby")).findFirst().get();

        assertThat(winUserProfit.profit()).isEqualTo(15000);
        assertThat(drawUserProfit.profit()).isEqualTo(0);
        assertThat(loseUserProfit.profit()).isEqualTo(-20000);
    }

    @Test
    @DisplayName("딜러의 수익은 유저 수익의 반대 부호다.")
    public void dealer_profit_reverse_user_profit() {
        Dealer dealer = new Dealer();
        User winUser = User.from("json", 10000);
        User loseUser = User.from("poby", 20000);
        User drawUser = User.from("draw", 30000);
        List<User> users = List.of(winUser, loseUser, drawUser);

        dealer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));
        winUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_ACE));
        loseUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_SEVEN));
        drawUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));

        List<UserProfit> userProfits = gameService.settleResult(users, dealer);

        DealerProfit dealerProfit = gameService.upsertDealerProfit(userProfits);

        assertThat(dealerProfit.profit()).isEqualTo(5000);
    }
}
