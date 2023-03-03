package model;

import model.card.Card;
import model.user.Dealer;
import model.user.Hand;
import model.user.Player;
import model.user.User;
import model.user.Users;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static model.card.Shape.CLOVER;
import static model.card.Shape.DIAMOND;
import static model.card.Shape.SPADE;
import static model.card.Value.FIVE;
import static model.card.Value.JACK;
import static model.card.Value.KING;
import static model.card.Value.NINE;
import static model.card.Value.THREE;
import static model.card.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {

    private Dealer dealer;

    @BeforeEach
    void init() {
        dealer = new Dealer("딜러", Hand.create());
    }

    @Test
    @DisplayName("두 명의 이름이 주어지면 딜러를 포함해서 3명의 Players 반환된다.")
    void createDealer() {
        // given, when
        final Users users = Users.of(List.of("bebe", "ethan"), dealer);

        // then
        assertThat(users)
                .extracting("users", InstanceOfAssertFactories.list(User.class))
                .containsExactly(Dealer.getInstance(), new Player("bebe", Hand.create()), new Player("ethan", Hand.create()));
    }

    @Test
    @DisplayName("딜러를 제외하고 게임 결과가 반환된다.")
    void getFinalResultWithoutDealer() {
        // given
        final Users users = Users.of(List.of("bebe", "ethan"), dealer);
        final User dealer = users.getUsers().get(0);
        final User bebe = users.getUsers().get(1);
        final User ethan = users.getUsers().get(2);

        dealer.receiveCard(new Card(SPADE, FIVE));
        bebe.receiveCard(new Card(SPADE, KING));
        ethan.receiveCard(new Card(SPADE, TWO));

        // when, then
        assertThat(users.getFinalResult(dealer)).containsExactly(TRUE, FALSE);
    }
    
    @Test
    @DisplayName("딜러와 플레이어가 모두 21 초과일 경우 플레이어가 이긴 결과가 반환된다.")
    void whenOverBurstNumberFindWinPlayer(){
        // given
        final Users users = Users.of(List.of("bebe"), dealer);
        final User dealer = users.getUsers().get(0);
        final User bebe = users.getUsers().get(1);

        dealer.receiveCard(new Card(SPADE, KING));
        dealer.receiveCard(new Card(DIAMOND, KING));
        dealer.receiveCard(new Card(CLOVER, THREE));

        bebe.receiveCard(new Card(SPADE, JACK));
        bebe.receiveCard(new Card(DIAMOND, JACK));
        bebe.receiveCard(new Card(CLOVER, TWO));

        // when, then
        assertThat(users.getFinalResult(dealer)).containsExactly(TRUE);
    }

    @Test
    @DisplayName("딜러가 21이하이고 플레이어는 21초과이면 플레이어의 패배가 반환된다.")
    void whenPlayerBurstReturnFalse() {
        // given

        final Users users = Users.of(List.of("bebe"), dealer);
        final User dealer = users.getUsers().get(0);
        final User bebe = users.getUsers().get(1);

        dealer.receiveCard(new Card(SPADE, KING));

        bebe.receiveCard(new Card(SPADE, JACK));
        bebe.receiveCard(new Card(DIAMOND, NINE));
        bebe.receiveCard(new Card(CLOVER, THREE));

        // when, then
        assertThat(users.getFinalResult(dealer)).containsExactly(FALSE);
    }

    @Test
    @DisplayName("딜러가 21초과이고 플레이어는 21이하이면 플레이어의 승리가 반환된다.")
    void whenDealerBurstReturnTrue() {
        // given
        final Users users = Users.of(List.of("bebe"), dealer);
        final User dealer = users.getUsers().get(0);
        final User bebe = users.getUsers().get(1);

        dealer.receiveCard(new Card(SPADE, JACK));
        dealer.receiveCard(new Card(DIAMOND, NINE));
        dealer.receiveCard(new Card(CLOVER, THREE));

        bebe.receiveCard(new Card(SPADE, KING));

        // when, then
        assertThat(users.getFinalResult(dealer)).containsExactly(TRUE);
    }
}
