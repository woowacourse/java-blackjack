import domain.Card;
import domain.CardDeck;
import domain.ShuffleStrategy;
import strategy.BettingRule;
import strategy.DefaultBettingRule;
import domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    private GameService gameService;
    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        ShuffleStrategy strategy = (cards -> {});
        BettingRule bettingRule = new DefaultBettingRule();
        gameService = new GameService(strategy, bettingRule);
    }

    @Test
    @DisplayName("문자열을 이름으로 한 사용자를 생성한다.")
    public void make_user_name_to_string(){
        String input = "jason";
        User user = User.from(input);
        Assertions.assertThat(user.getName()).isEqualTo("jason");
    }

    @Test
    @DisplayName("첫 딜에 사용자는 패를 2장 갖는다.")
    public void dealer_should_open_1_card (){
        User user = User.from("json");
        List<Card> cards = new ArrayList<>();
        cards.add(gameService.deal());
        cards.add(gameService.deal());
        user.receiveInitCard(cards);
        assertThat(user.getHand().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("베팅 금액을 설정하면 User 객체에 들어간다.")
    public void setup_bet_amount_to_player() {
        User user = User.from("json", 1000);
        int betAmount =1000;
        Assertions.assertThat(user.getBetAmount()).isEqualTo(betAmount);
    }

    @Test
    @DisplayName("베팅 금액이 0 이하면 오류를 반환한다.")
    public void bet_amount_cannot_under_0() {
        Assertions.assertThatThrownBy(() -> User.from("json",-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 베팅 금액은 0보다 커야 합니다.");
    }
}
