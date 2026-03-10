import domain.Card;
import domain.Dealer;
import domain.GameResult;
import domain.RandomShuffle;
import domain.ShuffleStrategy;
import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    void setUp() {
        ShuffleStrategy strategy = new RandomShuffle();
        gameService = new GameService(strategy);
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
        List<User> users = List.of(winUser, loseUser);

        dealer.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));
        winUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_SEVEN));
        loseUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_ACE));
        drawUser.receiveInitCard(List.of(Card.CLUB_KING, Card.CLUB_NINE));

        gameService.settleResult(users, dealer);
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
}
