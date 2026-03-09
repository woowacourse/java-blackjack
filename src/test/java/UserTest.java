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
}
