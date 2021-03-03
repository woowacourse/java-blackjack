package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @DisplayName("User 객체를 생성한다.")
    @Test
    public void createUser() {
        User user = new User("amazzi");

        assertThat(user).isInstanceOf(User.class);
    }

    @DisplayName("카드를 두장 분배받는다.")
    @Test
    public void distributeTwoCards() {
        User user = new User("amazzi");
        user.distribute(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.NINE),
                new Card(Shape.DIAMOND, Value.JACK)
        )));
        Cards cards = user.cards;

        assertThat(cards.cards().size()).isEqualTo(2);
    }

    @DisplayName("카드 합계를 구한다.")
    @Test
    public void calculateTotalCards() {
        User user = new User("amazzi");
        user.distribute(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.NINE),
                new Card(Shape.DIAMOND, Value.JACK)
        )));

        assertThat(user.calculateTotalValue()).isEqualTo(19);
    }

    @DisplayName("카드 합계가 21을 넘는지 확인한다. - 넘는 경우")
    @Test
    public void isAboveStandardTrue() {
        User user = new User("amazzi");
        user.distribute(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.TWO),
                new Card(Shape.DIAMOND, Value.JACK),
                new Card(Shape.CLOVER, Value.QUEEN)
        )));

        assertThat(user.isAboveStandard()).isTrue();
    }

    @DisplayName("카드 합계가 21을 넘는지 확인한다. - 안 넘는 경우")
    @Test
    public void isAboveStandardFalse() {
        User user = new User("amazzi");
        user.distribute(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.TWO),
                new Card(Shape.DIAMOND, Value.JACK)
        )));

        assertThat(user.isAboveStandard()).isFalse();
    }
}
