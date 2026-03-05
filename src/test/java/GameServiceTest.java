
import domain.Dealer;
import domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        //given, when
        int score = gameService.calculateScore(List.of("2", "5", "10"));
        //then
        assertThat(score).isEqualTo(17);
    }

    @Test
    @DisplayName("페이스 카드가 포함된 점수를 계산한다")
    void calculateAceAsEleven() {
        // given, when
        int score = gameService.calculateScore(List.of("1", "J", "Q", "K"));
        //then
        assertThat(score).isEqualTo(31);
    }

    @Test
    @DisplayName("첫 배부 때 합이 21 나오면 블랙잭 판정이다")
    public void if_card_sum_equals21_blackjack(){
        int score = 21;
        boolean result = gameService.isBlackjack(score);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("첫 배부 때 합이 21이 안 나오면 블랙잭 판정이 아니다")
    public void if_card_sum_not21_blackjack(){
        int score = 20;
        boolean result = gameService.isBlackjack(score);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("카드의 합이 21이 넘을 시 버스트 판정이다")
    public void if_card_sum_over21_burst(){
        int score = 22;
        boolean result = gameService.isBurst(score);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("카드의 합이 21이 넘지 않으면 버스트가 아니다")
    public void if_card_sum_under21_burst(){
        int score = 20;
        boolean result = gameService.isBurst(score);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Ace를 제외한 점수의 합이 10 이하면 Ace 점수는 11이 된다.")
    public void if_remain_score_under10_ace_score_11() {
        int sum = 10;
        int aceScore = gameService.calculateOptimalAceScore(sum);
        assertThat(aceScore).isEqualTo(11);
    }

    @Test
    @DisplayName("Ace를 제외한 점수의 합이 11 이상이면 Ace 점수는 1이 된다.")
    public void if_remain_score_over11_ace_score_1() {
        int sum = 11;
        int aceScore = gameService.calculateOptimalAceScore(sum);
        assertThat(aceScore).isEqualTo(1);
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
}
