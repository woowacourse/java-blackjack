package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class CardTest {

    @Test
    @DisplayName("카드는 모양과 숫자로 생성할 수 있다.")
    void constructorTest() {
        assertThatNoException().isThrownBy(() -> new Card(Shape.SPADE, Letter.TWO));
    }


    @Test
    @DisplayName("카드가 에이스 인지 확인하는 테스트")
    void isAceTest() {
        Card diamondAce = new Card(Shape.DIAMOND, Letter.ACE);
        Card spadeAce = new Card(Shape.SPADE, Letter.ACE);
        Card cloverAce = new Card(Shape.CLOVER, Letter.ACE);
        Card heartAce = new Card(Shape.HEART, Letter.ACE);

        assertThat(diamondAce.isAce()).isTrue();
        assertThat(spadeAce.isAce()).isTrue();
        assertThat(cloverAce.isAce()).isTrue();
        assertThat(heartAce.isAce()).isTrue();
    }

    @Test
    @DisplayName("카드가 에이스 아닌지 확인하는 테스트")
    void isNotAceTest() {
        for (Shape shape : Shape.values()) {
            checkLetterIstAce(shape);
        }
    }

    private void checkLetterIstAce(Shape shape) {
        Card card;
        for (Letter letter : Letter.values()) {
            card = new Card(shape, letter);

            if (!letter.equals(Letter.ACE)) assertThat(card.isAce()).isFalse();
            if (letter.equals(Letter.ACE)) assertThat(card.isAce()).isTrue();
        }
    }

    @Test
    @DisplayName("모양을 반환하는 테스트")
    void getShapeTest() {
        Card card;
        for (Shape shape : Shape.values()) {
            card = new Card(shape, Letter.EIGHT);

            assertThat(card.getShape()).isEqualTo(shape);
        }
    }

    @Test
    @DisplayName("숫자를 반환하는 테스트")
    void getLetterTest() {
        Card card;
        for (Letter letter : Letter.values()) {
            card = new Card(Shape.CLOVER, letter);

            assertThat(card.getLetter()).isEqualTo(letter);
        }
    }

    @Test
    @DisplayName("카드를 뒤로 뒤집는 테스트")
    void reverseCardTest() {
        Card card = new Card(Shape.CLOVER, Letter.EIGHT);
        card.closeCard();

        assertThat(card.isOpen()).isFalse();
    }

    @Test
    @DisplayName("카드를 앞으로 뒤집는 테스트")
    void openCardTest() {
        Card card = new Card(Shape.CLOVER, Letter.EIGHT);
        card.closeCard();
        card.openCard();

        assertThat(card.isOpen()).isTrue();

    }
}
