package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.ResultType;
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
        dealer = new Dealer();
        dealer.drawCard(new Card(CardShape.CLUB, CardNumber.SEVEN));

        User user1 = new User("pobi");
        user1.drawCard(new Card(CardShape.CLUB, CardNumber.EIGHT));

        User user2 = new User("jason");
        user2.drawCard(new Card(CardShape.CLUB, CardNumber.SIX));

        User user3 = new User("inbi");
        user3.drawCard(new Card(CardShape.CLUB, CardNumber.SEVEN));

        User user4 = new User("mungto");
        user4.drawCard(new Card(CardShape.CLUB, CardNumber.TEN));
        user4.drawCard(new Card(CardShape.CLUB, CardNumber.TEN));
        user4.drawCard(new Card(CardShape.CLUB, CardNumber.TWO));

        List<User> convertUsers = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        users = new Users(convertUsers);
    }

    @DisplayName("Users 생성자 동일 비교 테스트")
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
        Map<Name, ResultType> result = users.getResult(dealer);
        assertThat(result.get(new Name("pobi"))).isEqualTo(ResultType.WIN);
        assertThat(result.get(new Name("jason"))).isEqualTo(ResultType.LOSS);
        assertThat(result.get(new Name("inbi"))).isEqualTo(ResultType.DRAW);
        assertThat(result.get(new Name("mungto"))).isEqualTo(ResultType.LOSS);
    }
}