import domain.Card;
import domain.CardDeck;
import domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.GameService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    private GameService gameService;
    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cardDeck = new CardDeck();
        gameService = new GameService();
    }

    @Test
    @DisplayName("문자열을 이름으로 한 사용자를 생성한다.")
    public void make_user_name_to_string(){
        String input = "jason";
        User user = new User(input);
        Assertions.assertThat(user.getName()).isEqualTo("jason");
    }

    @Test
    @DisplayName("첫 딜에 사용자는 패를 2장 갖는다.")
    public void dealer_should_open_1_card (){
        User user = new User("json");
        List<Card> cards = new ArrayList<>();
        cards.add(cardDeck.deal());
        cards.add(cardDeck.deal());
        user.receiveInitCard(cards);
        assertThat(user.getHand().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("첫 배부 때 합이 21 나오면 블랙잭 판정이다")
    public void if_card_sum_equals21_blackjack(){
        int score = 21;
        User user = new User("json");
        boolean result = user.isBlackjack(score);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("첫 배부 때 합이 21이 안 나오면 블랙잭 판정이 아니다")
    public void if_card_sum_not21_blackjack(){
        int score = 20;
        User user = new User("json");
        boolean result = user.isBlackjack(score);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("카드의 합이 21이 넘을 시 버스트 판정이다")
    public void if_card_sum_over21_burst(){
        int score = 22;
        User user = new User("json");
        boolean result = user.isBurst(score);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("카드의 합이 21이 넘지 않으면 버스트가 아니다")
    public void if_card_sum_under21_burst(){
        int score = 20;
        User user = new User("json");
        boolean result = user.isBurst(score);
        assertThat(result).isFalse();
    }
}
