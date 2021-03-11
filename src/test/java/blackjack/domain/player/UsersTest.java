package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UsersTest {
    private Users users;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        Card fourCard = Card.valueOf(CardShape.CLUB, CardNumber.FOUR);
        Card threeCard = Card.valueOf(CardShape.CLUB, CardNumber.THREE);
        dealer = new Dealer(fourCard, threeCard);
        dealer.stay();
        User user1 = new User("pobi", fourCard, fourCard);
        user1.stay();
        User user2 = new User("jason", threeCard, threeCard);
        user2.stay();
        User user3 = new User("inbi", fourCard, threeCard);
        user3.stay();

        Card tenCard = Card.valueOf(CardShape.CLUB, CardNumber.TEN);
        User user4 = new User("mungto", tenCard, tenCard);
        user4.drawCard(tenCard);

        List<User> convertUsers = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        users = new Users(convertUsers);
        users.getUsers().forEach(user -> user.setBetAmount("10000"));
    }

    @DisplayName("서로다른 Users 생성자가 같은 결과를 가져오는지 테스트")
    @Test
    void constructorsEqual() {
        String usersNames = "pobi,jason,inbi,mungto";
        Users users2 = new Users(usersNames);
        for (int i = 0; i < 4; i++) {
            assertThat(users.getUsers().get(i).getName())
                .isEqualTo(users2.getUsers().get(i).getName());
        }
    }

    @DisplayName("게임 결과 반환 테스트")
    @Test
    void result() {
        Map<Name, Integer> result = users.getResult(dealer);
        assertThat(result.get(new Name("pobi"))).isEqualTo(10000);
        assertThat(result.get(new Name("jason"))).isEqualTo(-10000);
        assertThat(result.get(new Name("inbi"))).isEqualTo(0);
        assertThat(result.get(new Name("mungto"))).isEqualTo(-10000);
    }
}