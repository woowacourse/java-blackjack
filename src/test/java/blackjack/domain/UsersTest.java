package blackjack.domain;

import blackjack.domain.card.*;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class UsersTest {

    private static final Card CARD_9 = new Card(Shape.HEART, CardNumber.of(9));
    private static final Card CARD_10 = new Card(Shape.HEART, CardNumber.of(10));
    private static final Cards CARDS_20 = new Cards(List.of(new Card(Shape.HEART, CardNumber.of(10)), new Card(Shape.HEART, CardNumber.of(10))));
    private static User 헙크;
    private static User 푸우;
    private static Users HupkAndPooh;

    @BeforeEach
    void setting() {
        헙크 = new User(new Name("헙크"), CARDS_20);
        푸우 = new User(new Name("푸우"), CARDS_20);
        HupkAndPooh = new Users(Arrays.asList(new Name("헙크"), new Name("푸우")), new TestDeck(Arrays.asList(10, 10, 10, 10)));
    }

    @Test
    @DisplayName("Users가 한 명도 없으면 예외가 발생한다.")
    void invalidUsersCountTest() {
        List<Name> data = new ArrayList<>();
        assertThatThrownBy(() -> new Users(data, new RandomDeck()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저는 최소 한 명 이상이여야 합니다.");
    }

    @Test
    @DisplayName("Users는 최소 한 명 이상으로 구성되야 한다.")
    void validUsersCountTest() {
        assertDoesNotThrow(() -> new Users(List.of(new Name("헙크")), new RandomDeck()));
    }

    @Test
    @DisplayName("Users 중 해당 게임 결과 값보다 큰 유저들의 리스트를 얻을 수 있다.")
    void findUsersGreaterThanGamePoint() {

        final GamePoint gamePoint = new GamePoint(List.of(CARD_10, CARD_9));

        final Users users = new Users(
                List.of(new Name("a"), new Name("b"), new Name("c")),
                new TestDeck(Arrays.asList(10, 8, 10, 9, 10, 10))
        );

        assertThat(users.getUsersGreaterThan(gamePoint))
                .allMatch((user) -> user.isNameOf(new Name("c")));
    }

    @Test
    @DisplayName("Users 중 해당 게임 결과 값과 같은 유저들의 리스트를 얻을 수 있다.")
    void findUsersEqualGamePoint() {
        final GamePoint gamePoint = new GamePoint(List.of(CARD_10, CARD_9));

        final Users users = new Users(
                List.of(new Name("a"), new Name("b"), new Name("c")),
                new TestDeck(Arrays.asList(10, 8, 10, 9, 10, 10))
        );

        assertThat(users.getUsersEqualTo(gamePoint))
                .allMatch((user) -> user.isNameOf(new Name("b")));
    }

    @Test
    @DisplayName("Users 중 해당 게임 결과 값보다 작은 유저들의 리스트를 얻을 수 있다.")
    void findUserLowerThanGamePoint() {
        final GamePoint gamePoint = new GamePoint(List.of(CARD_10, CARD_9));

        final Users users = new Users(
                List.of(new Name("a"), new Name("b"), new Name("c")),
                new TestDeck(Arrays.asList(10, 8, 10, 9, 10, 10))
        );

        assertThat(users.getUsersLowerThan(gamePoint))
                .allMatch((user) -> user.isNameOf(new Name("a")));
    }

    @Test
    @DisplayName("유저 이름으로 유저의 카드들을 가져온다.")
    void getCardsOfTest() {
        final List<Card> 푸우카드 = HupkAndPooh.getCardsOf(new Name("푸우"));
        assertThat(푸우카드.containsAll(푸우.getCards().getCards()));
    }

    @Test
    void giveCardByNameTest() {
        final Name 푸우이름 = new Name("푸우");
        HupkAndPooh.giveCardByName(푸우이름, CARD_9);
        final User 푸우 = HupkAndPooh.getUsers().stream()
                .filter(user -> user.isNameOf(푸우이름))
                .findAny()
                .get();
        assertThat(푸우.getCards().getCards().contains(CARD_9)).isTrue();
    }

    @Test
    void checkBustByTest() {
        HupkAndPooh.giveCardByName(new Name("푸우"), CARD_9);
        assertThat(HupkAndPooh.checkBustBy(new Name("푸우"))).isTrue();
    }
}
