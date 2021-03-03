package blackjack.domain.player;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
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

        User user2 = new User("json");
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

    @DisplayName("게임 결과 반환 테스트")
    @Test
    void result() {
        Map<Name, ResultType> result = users.getResult(dealer);
        assertThat(result.get(new Name("pobi"))).isEqualTo(ResultType.WIN);
        assertThat(result.get(new Name("json"))).isEqualTo(ResultType.LOSS);
        assertThat(result.get(new Name("inbi"))).isEqualTo(ResultType.DRAW);
        assertThat(result.get(new Name("mungto"))).isEqualTo(ResultType.LOSS);
    }
}