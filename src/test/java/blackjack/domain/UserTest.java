package blackjack.domain;

import blackjack.domain.card.*;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;
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
    private User 푸우;

    @BeforeEach
    void setting() {
        푸우 = new User(new Name("푸우"), new Cards(List.of(
                new Card(Shape.HEART, CardNumber.ACE),
                new Card(Shape.HEART, CardNumber.ACE)
        )));
    }

    @Test
    @DisplayName("User는 카드를 한 장 받을 수 있다.")
    void drawTest() {
        //given
        final Cards originCard = 푸우.getCards();
        final List<Card> originCardData = originCard.getCards();

        assertThat(originCardData.size())
                .isEqualTo(2);

        //when
        final Card card3 = new Card(Shape.HEART, CardNumber.THREE);
        푸우.draw(card3);
        final Cards changedCard = 푸우.getCards();
        final List<Card> changeCardData = changedCard.getCards();

        //then
        assertThat(changeCardData.size())
                .isEqualTo(3);
    }

    @Test
    @DisplayName("21 이하의 카드를 가진 유저는 카드를 더 받을 수 있다.")
    void canReceiveTest() {
        //given
        final User user20 = new User(new Name("20가짐"),
                new Cards(List.of(
                        new Card(Shape.HEART, CardNumber.TEN),
                        new Card(Shape.HEART, CardNumber.TEN))
                )
        );

        //when
        final boolean canRecieve = user20.canReceive();

        //then
        assertThat(canRecieve).isTrue();
    }

    @Test
    @DisplayName("21 이상의 카드를 가진 유저는 더 이상 카드를 받을 수 없다.")
    void cantReceiveTest() {
        //given
        final Card card3 = new Card(Shape.HEART, CardNumber.TEN);
        final Card card4 = new Card(Shape.HEART, CardNumber.TEN);
        푸우.draw(card3);
        푸우.draw(card4);

        //when
        final boolean canReceive = 푸우.canReceive();

        //then
        assertThat(canReceive).isFalse();
    }

    @Test
    @DisplayName("유저는 버스트 나기 전까지 카드를 받을 수 있다.")
    void canDrawUntilBustTest() {
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 17; i++) {
                푸우.draw(new Card(Shape.HEART, CardNumber.ACE));
            }
        });
    }

    @Test
    @DisplayName("유저는 버스트 나면 카드를 받을 수 없다.")
    void cantDrawWhenBustTest() {
        assertThatThrownBy(() -> {
            for (int i = 0; i < 21; i++) {
                푸우.draw(new Card(Shape.HEART, CardNumber.ACE));
            }
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("버스트 후에는 카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("유저 게임 포인트 반환 테스트")
    void userGamePointTest() {
        for (int i = 0; i < 12; i++) {
            푸우.draw(new Card(Shape.HEART, CardNumber.ACE));
        }
        assertThat(푸우.getGamePoint())
                .extracting("gamePoint")
                .isEqualTo(14);
    }

    @Test
    @DisplayName("해당 이름을 가진 유저인지 확인한다.")
    void isNameOfTest() {
        assertThat(푸우.isNameOf(new Name("푸우"))).isTrue();
    }

    @Test
    @DisplayName("유저가 버스트 난 유저 인지 확인한다.")
    void isBustedTest() {
        //given
        final User user22 = new User(new Name("유저1"), new Cards(
                Arrays.asList(
                        new Card(Shape.HEART, CardNumber.TWO),
                        new Card(Shape.HEART, CardNumber.TEN)
                )
        ));
        user22.draw(new Card(Shape.HEART, CardNumber.TEN));

        final User user21 = new User(new Name("유저1"), new Cards(
                Arrays.asList(
                        new Card(Shape.HEART, CardNumber.ACE),
                        new Card(Shape.HEART, CardNumber.TEN)
                )
        ));

        //when,then
        assertAll(
                () -> {
                    assertThat(user21.isBusted()).isFalse();
                },
                () -> {
                    assertThat(user22.isBusted()).isTrue();
                }
        );
    }
}
