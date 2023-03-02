package model;

import model.card.Card;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static model.card.Shape.SPADE;
import static model.card.Value.FIVE;
import static model.card.Value.KING;
import static model.card.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {

    @Test
    @DisplayName("두 명의 이름이 주어지면 딜러를 포함해서 3명의 Players 반환된다.")
    void createDealer() {
        // given, when
        final Users users = new Users(List.of("bebe", "ethan"));

        // then
        assertThat(users)
                .extracting("users", InstanceOfAssertFactories.list(User.class))
                .containsExactly(Dealer.getInstance(), new Player("bebe", Hand.create()), new Player("ethan", Hand.create()));
    }

    @Test
    @DisplayName("딜러를 제외하고 게임 결과가 반환된다.")
    void getFinalResultWithoutDealer() {
        // given
        final Users users = new Users(List.of("bebe", "ethan"));
        final User dealer = users.getUsers().get(0);
        final User bebe = users.getUsers().get(1);
        final User ethan = users.getUsers().get(2);

        dealer.receiveCard(new Card(SPADE, FIVE));
        bebe.receiveCard(new Card(SPADE, KING));
        ethan.receiveCard(new Card(SPADE, TWO));

        // when, then
        assertThat(users.getFinalResultWithoutDealer()).containsExactly(TRUE, FALSE);
    }
}
