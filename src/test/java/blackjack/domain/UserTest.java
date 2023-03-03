package blackjack.domain;

import blackjack.domain.card.*;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UserTest {
    private static final User USER_16 = new User(new Name("유저1"), new Cards(
            Arrays.asList(new Card(Shape.HEART, CardNumber.of(6)), new Card(Shape.HEART, CardNumber.of(10)))
    ));
    private static final User USER_18 = new User(new Name("유저2"), new Cards(
            Arrays.asList(new Card(Shape.HEART, CardNumber.of(8)), new Card(Shape.HEART, CardNumber.of(10)))
    ));
    private static final User USER_21 = new User(new Name("유저1"), new Cards(
            Arrays.asList(
                    new Card(Shape.HEART, CardNumber.of(1)),
                    new Card(Shape.HEART, CardNumber.of(10))
            )
    ));
    private static final GamePoint GAME_POINT_17 = new GamePoint(Arrays.asList(new Card(Shape.HEART, CardNumber.of(7)), new Card(Shape.HEART, CardNumber.of(10))));
    private User 푸우;

    @BeforeEach
    void setting() {
        푸우 = new User(new Name("푸우"), new Cards(List.of(
                new Card(Shape.HEART, CardNumber.of(1)),
                new Card(Shape.HEART, CardNumber.of(1))
        )));
    }

    @Test
    @DisplayName("User는 카드를 한 장 받을 수 있다.")
    void drawTest() {

        assertThat(푸우)
                .extracting("cards")
                .extracting("cards", InstanceOfAssertFactories.collection(List.class))
                .size()
                .isEqualTo(2);

        final Card card3 = new Card(Shape.HEART, CardNumber.of(3));
        푸우.draw(card3);

        assertThat(푸우)
                .extracting("cards")
                .extracting("cards", InstanceOfAssertFactories.collection(List.class))
                .size()
                .isEqualTo(3);
    }

    @Test
    @DisplayName("21 이하의 카드를 가진 유저는 카드를 더 받을 수 있다.")
    void canReceiveTest() {
        assertThat(푸우.canReceive()).isTrue();
    }

    @Test
    @DisplayName("21 이상의 카드를 가진 유저는 더 이상 카드를 받을 수 없다.")
    void cantReceiveTest() {
        final Card card3 = new Card(Shape.HEART, CardNumber.of(10));
        final Card card4 = new Card(Shape.HEART, CardNumber.of(10));
        푸우.draw(card3);
        푸우.draw(card4);

        assertThat(푸우.canReceive()).isFalse();
    }

    @Test
    @DisplayName("유저는 버스트 나기 전까지 카드를 받을 수 있다.")
    void canDrawUntilBustTest() {
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 17; i++) {
                푸우.draw(new Card(Shape.HEART, CardNumber.of(1)));
            }
        });
    }

    @Test
    @DisplayName("유저는 버스트 나면 카드를 받을 수 없다.")
    void cantDrawWhenBustTest() {
        assertThatThrownBy(() -> {
            for (int i = 0; i < 22; i++) {
                푸우.draw(new Card(Shape.HEART, CardNumber.of(1)));
            }
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("버스트 후에는 카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("유저 게임 포인트 반환 테스트")
    void userGamePointTest() {
        for (int i = 0; i < 12; i++) {
            푸우.draw(new Card(Shape.HEART, CardNumber.of(1)));
        }
        assertThat(푸우.getGamePoint())
                .extracting("gamePoint")
                .isEqualTo(14);
    }

    @Test
    @DisplayName("유저가 해당 GamePoint보다 큰 값을 갖고 있는지 확인한다.")
    void isGreaterThanTest() {
        assertAll(
                () -> {
                    assertThat(USER_18.isGreaterThan(GAME_POINT_17)).isTrue();
                },
                () -> {
                    assertThat(USER_16.isGreaterThan(GAME_POINT_17)).isFalse();
                }
        );
    }

    @Test
    @DisplayName("유저가 해당 GamePoint보다 작은 값을 갖고 있는지 확인한다.")
    void isLowerThanTest() {
        assertAll(
                () -> {
                    assertThat(USER_16.isLowerThan(GAME_POINT_17)).isTrue();
                },
                () -> {
                    assertThat(USER_18.isLowerThan(GAME_POINT_17)).isFalse();
                }
        );
    }

    @Test
    @DisplayName("해당 이름을 가진 유저인지 확인한다.")
    void isNameOfTest() {
        assertThat(푸우.isNameOf(new Name("푸우"))).isTrue();
    }

    @Test
    @DisplayName("유저가 버스트 난 유저 인지 확인한다.")
    void isBustedTest() {
        final User USER_22 = new User(new Name("유저1"), new Cards(
                Arrays.asList(
                        new Card(Shape.HEART, CardNumber.of(2)),
                        new Card(Shape.HEART, CardNumber.of(10))
                )
        ));
        USER_22.draw(new Card(Shape.HEART, CardNumber.of(10)));
        assertAll(
                () -> {
                    assertThat(USER_21.isBusted()).isFalse();
                },
                () -> {
                    assertThat(USER_22.isBusted()).isTrue();
                }
        );
    }
}
