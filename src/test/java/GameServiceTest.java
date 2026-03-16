import domain.card.Card;
import domain.player.Dealer;
import domain.result.DealerProfit;
import domain.result.GameResult;
import domain.card.RandomShuffle;
import domain.card.ShuffleStrategy;
import domain.player.User;
import domain.result.RoundBetInfo;
import domain.result.UserProfit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.BettingRule;
import strategy.DefaultBettingRule;

import java.math.BigDecimal;
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
        List<RoundBetInfo> roundBetInfos = List.of(
                new RoundBetInfo(1, winUser, BigDecimal.valueOf(1000)),
                new RoundBetInfo(1, drawUser, BigDecimal.valueOf(2000)),
                new RoundBetInfo(1, loseUser, BigDecimal.valueOf(3000))
        );

        dealer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));
        winUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_SEVEN));
        loseUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_ACE));
        drawUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));

        List<UserProfit> userProfits = gameService.settleResult(roundBetInfos, dealer);
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
    @DisplayName("딜러의 수익은 유저 수익의 반대 부호다.")
    public void dealer_profit_reverse_user_profit() {
        Dealer dealer = new Dealer();
        User winUser = User.from("json");
        User loseUser = User.from("poby");
        User drawUser = User.from("draw");
        List<User> users = List.of(winUser, loseUser, drawUser);
        List<RoundBetInfo> roundBetInfos = List.of(
                new RoundBetInfo(1, winUser, BigDecimal.valueOf(1000)),
                new RoundBetInfo(1, drawUser, BigDecimal.valueOf(2000)),
                new RoundBetInfo(1, loseUser, BigDecimal.valueOf(3000))
        );


        dealer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));
        winUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_ACE));
        loseUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_SEVEN));
        drawUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));

        List<UserProfit> userProfits = gameService.settleResult(roundBetInfos, dealer);

        DealerProfit dealerProfit = gameService.upsertDealerProfit(userProfits);

        assertThat(dealerProfit.profit()).isEqualByComparingTo("1500");
    }
}
