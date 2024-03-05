import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDeckTest {
    @Test
    @DisplayName("유저 카드 덱에 카드를 추가할 수 있다.")
    void pushCardTest() {
        Card card = new Card(Shape.CLOVER, Number.ACE);
        UserDeck userDeck = new UserDeck();

        userDeck.pushCard(card);

        assertThat(userDeck.getUserDeck()).contains(card);
    }

    @Test
    @DisplayName("덱 카드의 숫자의 합을 구할 수 있다.")
    void sumCardTest() {
        UserDeck userDeck = new UserDeck();

        userDeck.pushCard(new Card(Shape.CLOVER, Number.THREE));
        userDeck.pushCard(new Card(Shape.CLOVER, Number.EIGHT));

        assertThat(userDeck.sumCard()).isEqualTo(11);
    }

    @Test
    @DisplayName("ACE 카드 유무 판별")
    void hasAceTest() {
        UserDeck userDeck = new UserDeck();

        userDeck.pushCard(new Card(Shape.CLOVER, Number.ACE));

        assertThat(userDeck.hasAce()).isTrue();
    }

    @Test
    @DisplayName("ACE 카드는 합이 22 이상일 때 숫자가 1로 사용된다.")
    void sumCardContainingAceTest() {
        UserDeck userDeck = new UserDeck();

        userDeck.pushCard(new Card(Shape.CLOVER, Number.ACE));
        userDeck.pushCard(new Card(Shape.CLOVER, Number.TWO));
        userDeck.pushCard(new Card(Shape.CLOVER, Number.KING));

        assertThat(userDeck.sumCard()).isEqualTo(13);
    }
}
