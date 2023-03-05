package domain;

import domain.card.*;
import domain.participant.Name;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class UsersTest {

    @Test
    @DisplayName("Users가 한 명도 없으면 예외가 발생한다.")
    void invalidUsersCountTest() {
        List<Player> data = new ArrayList<>();
        assertThatThrownBy(() -> new Users(data))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저는 최소 한 명 이상이여야 합니다.");
    }

    @Test
    @DisplayName("Users는 최소 한 명 이상으로 구성되야 한다.")
    void validUsersCountTest() {
        final Cards cards = new Cards(
                List.of(
                        Card.create(CardShape.HEART, CardNumber.of(10)),
                        Card.create(CardShape.HEART, CardNumber.of(10))
                )
        );
        final Player 헙크 = Player.create((new Name("헙크")), cards);
        assertDoesNotThrow(() -> new Users(List.of(헙크)));
    }

    @Test
    @DisplayName("Users 중 해당 게임 결과 값보다 큰 유저들의 리스트를 얻을 수 있다.")
    void findUsersGreaterThanGamePoint() {
        final Card card10 = Card.create(CardShape.HEART, CardNumber.of(10));
        final Card card9 = Card.create(CardShape.HEART, CardNumber.of(9));
        final Card card8 = Card.create(CardShape.HEART, CardNumber.of(8));

        final Player player18 = Player.create((new Name("a")), new Cards(List.of(card10, card8)));
        final Player player19 = Player.create((new Name("a")), new Cards(List.of(card10, card9)));
        final Player player20 = Player.create((new Name("a")), new Cards(List.of(card10, card10)));

        final GamePoint gamePoint = new GamePoint(List.of(card10, card9));

        final Users users = new Users(List.of(player18, player19, player20));

        assertThat(users.getUsersGreaterThan(gamePoint)).containsExactly(player20);
    }

    @Test
    @DisplayName("Users 중 해당 게임 결과 값과 같은 유저들의 리스트를 얻을 수 있다.")
    void findUsersEqualGamePoint() {
        final Card card10 = Card.create(CardShape.HEART, CardNumber.of(10));
        final Card card9 = Card.create(CardShape.HEART, CardNumber.of(9));
        final Card card8 = Card.create(CardShape.HEART, CardNumber.of(8));

        final Player player18 = Player.create((new Name("a")), new Cards(List.of(card10, card8)));
        final Player player19 = Player.create((new Name("a")), new Cards(List.of(card10, card9)));
        final Player player20 = Player.create((new Name("a")), new Cards(List.of(card10, card10)));

        final GamePoint gamePoint = new GamePoint(List.of(card10, card9));

        final Users users = new Users(List.of(player18, player19, player20));

        assertThat(users.getUsersEqualTo(gamePoint)).containsExactly(player19);
    }

    @Test
    @DisplayName("Users 중 해당 게임 결과 값보다 작은 유저들의 리스트를 얻을 수 있다.")
    void findUserLowerThanGamePoint() {
        final Card card10 = Card.create(CardShape.HEART, CardNumber.of(10));
        final Card card9 = Card.create(CardShape.HEART, CardNumber.of(9));
        final Card card8 = Card.create(CardShape.HEART, CardNumber.of(8));

        final Player player18 = Player.create((new Name("a")), new Cards(List.of(card10, card8)));
        final Player player19 = Player.create((new Name("a")), new Cards(List.of(card10, card9)));
        final Player player20 = Player.create((new Name("a")), new Cards(List.of(card10, card10)));

        final GamePoint gamePoint = new GamePoint(List.of(card10, card9));

        final Users users = new Users(List.of(player18, player19, player20));

        assertThat(users.getUsersLowerThan(gamePoint)).containsExactly(player18);
    }
}
