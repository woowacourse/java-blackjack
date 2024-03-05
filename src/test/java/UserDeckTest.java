import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.user.UserDeck;
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

        assertThat(userDeck.getCards()).contains(card);
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

    @Test
    @DisplayName("ACE카드가 여러개일 때 최적의 값을 만든다.")
    void reduceSumByAce() {
        //given
        UserDeck userDeck = new UserDeck();
        //when
        userDeck.pushCard(new Card(Shape.CLOVER, Number.ACE));
        userDeck.pushCard(new Card(Shape.SPADE, Number.ACE));
        userDeck.pushCard(new Card(Shape.HEART, Number.SEVEN));
        //then
        int sum = userDeck.sumCard();
        assertThat(sum).isEqualTo(19);
    }
}
