package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.controller.dto.ResultDTO;
import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.card.type.CardNumberType;
import blackjack.domain.card.type.CardShapeType;
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
        dealer.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.SEVEN));

        User user1 = new User("pobi");
        user1.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.EIGHT));

        User user2 = new User("jason");
        user2.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.SIX));

        User user3 = new User("inbi");
        user3.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.SEVEN));

        User user4 = new User("mungto");
        user4.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.TEN));
        user4.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.TEN));
        user4.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.TWO));

        List<User> convertUsers = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        users = new Users(convertUsers);
    }

    @DisplayName("Users의 다른 생성자들에게 동일한 생성 목적의 매개변수를 넘겨 생성했을 때, "
        + "Users의 이름들이 모두 같고, 동일한 순서인 상태로 생성되는지 테스트")
    @Test
    void constructorsEqual() {
        String usersNames = "pobi,jason,inbi,mungto";
        Users users2 = new Users(usersNames);
        assertThat(users.getUsers()).hasSize(users2.getUsers().size());
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

    @DisplayName("게임 결과 반환 테스트")
    @Test
    void resultNew() {
        ResultDTO resultDTO = new ResultDTO(users.getResult(dealer));
        Map<Name, ResultType> result = resultDTO.getResult();

        assertThat(result.get(new Name("pobi"))).isEqualTo(ResultType.WIN);
        assertThat(result.get(new Name("jason"))).isEqualTo(ResultType.LOSS);
        assertThat(result.get(new Name("inbi"))).isEqualTo(ResultType.DRAW);
        assertThat(result.get(new Name("mungto"))).isEqualTo(ResultType.LOSS);
    }
}
